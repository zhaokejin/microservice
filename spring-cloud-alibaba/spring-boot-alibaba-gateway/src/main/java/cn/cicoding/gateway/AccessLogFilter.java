package cn.cicoding.gateway;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyRequestBodyGatewayFilterFactory;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.*;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.multipart.FormFieldPart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AccessLogFilter
 * @Description 全局日志拦截器
 * @Author will
 * @Date 2022/9/22 11:07
 * @Company 北京惠科互联科技有限公司
 */
@Slf4j
@Component
public class AccessLogFilter implements GlobalFilter, Ordered {

    /**
     * default HttpMessageReader.
     */
    /**
     * DEFAULT_MESSAGE_READERS 默认消息处理
     */
    private static final List<HttpMessageReader<?>> DEFAULT_MESSAGE_READERS = HandlerStrategies.withDefaults().messageReaders();
    /**
     * 默认读取信息处理
     */
    private final List<HttpMessageReader<?>> messageReaders = HandlerStrategies.withDefaults().messageReaders();

//    @Autowired
//    private IpTablesChecker ipTablesChecker;
    /**
     * openapi 用户类型
     */
    private static final String OPEN_API_USER_TYPE = "2";


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取请求路径
        ServerHttpRequest request = exchange.getRequest();
        String requestPath = request.getPath().pathWithinApplication().value();
        Route route = getGatewayRoute(exchange);
        String ipAddress = IPHelper.getIpAddress(request);

        String scheme = request.getURI().getScheme();
        String requestMethodValue = request.getMethodValue();
        long requestTime = System.currentTimeMillis();
        String targetServer = route.getId();
        HttpHeaders headers = request.getHeaders();
        MediaType mediaType = headers.getContentType();
        String requestContentType = mediaType.getType() + "/" + mediaType.getSubtype();
        String urlPath = request.getURI().getPath();
//        boolean ipWhite = ipTablesChecker.pass(requestPath, ipAddress);
        boolean ipWhite = true;
        log.info(">>>>>>>>>>>>>>>>>>>> not save access log {}, {}, IP白名单:{}", request, ipAddress, ipWhite);
        // || exclusivity(path, referer, req.getMethodValue())
        if (ipWhite /*|| AuthProvider.getDefaultSkipUrl().contains(urlPath)*/) {
            return chain.filter(exchange);
        }

//        String userId = headers.getFirst(AuthProvider._USERID);
//        String userName = headers.getFirst(AuthProvider._USER);
//        String userType = headers.getFirst(AuthProvider._USERTYPE);

        String userId = "";
        String userName = "";
        String userType = "";
        try {
            if (StrUtil.isNotEmpty(userId)) {
                userId = URLDecoder.decode(userId, "UTF-8");
            }
            if (StrUtil.isNotEmpty(userName)) {
                userName = URLDecoder.decode(userName, "UTF-8");
            }
            if (StrUtil.isNotEmpty(userType)) {
                userType = URLDecoder.decode(userType, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //非OpenAPI用户默认不记录请求响应日志
        if (!OPEN_API_USER_TYPE.equals(userType)) {
            return chain.filter(exchange);
        }
        AccessLog gatewayLog = new AccessLog();
        gatewayLog.setSchema(scheme);
        gatewayLog.setRequestMethod(requestMethodValue);
        gatewayLog.setRequestContentType(requestContentType);
        gatewayLog.setRequestPath(requestPath);
        gatewayLog.setTargetServer(targetServer);
        gatewayLog.setRequestTime(requestTime);
        gatewayLog.setIp(ipAddress);
        gatewayLog.setUserId(userId);

        if (MediaType.APPLICATION_JSON.isCompatibleWith(mediaType)) {
            return writeBodyLog(exchange, chain, gatewayLog);
        } else if (MediaType.MULTIPART_FORM_DATA.isCompatibleWith(mediaType) || MediaType.APPLICATION_FORM_URLENCODED.isCompatibleWith(mediaType)) {
            return readFormData(exchange, chain, gatewayLog);
        } else {
            //其他格式
            return writeBasicLog(exchange, chain, gatewayLog);
        }
    }

    /**
     * 获取网关路由地址
     *
     * @param exchange
     * @return
     */
    private Route getGatewayRoute(ServerWebExchange exchange) {
        return exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
    }


    /**
     * 写入requestBody日志
     * 解决 request body 只能读取一次问题，
     * 参考: {@link ModifyRequestBodyGatewayFilterFactory}
     *
     * @param exchange
     * @param chain
     * @param gatewayLog
     * @return
     */
    private Mono writeBodyLog(ServerWebExchange exchange, GatewayFilterChain chain, AccessLog gatewayLog) {
        ServerRequest serverRequest = ServerRequest.create(exchange, messageReaders);
        Mono<String> modifiedBody = serverRequest.bodyToMono(String.class).flatMap(body -> {
            gatewayLog.setRequestBody(body);
            return Mono.just(body);
        });
        // 通过 BodyInserter 插入 body(支持修改body), 避免 request body 只能获取一次
        BodyInserter bodyInserter = BodyInserters.fromPublisher(modifiedBody, String.class);
        HttpHeaders headers = new HttpHeaders();
        /**
         * 新的内容类型将由 bodyInserter 计算,然后在请求装饰器中设置
         * the new content type will be computed by bodyInserter
         * and then set in the request decorator
         */
        headers.putAll(exchange.getRequest().getHeaders());
        headers.remove(HttpHeaders.CONTENT_LENGTH);
        CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);
        return bodyInserter
                .insert(outputMessage, new BodyInserterContext())
                .then(Mono.defer(() -> {
                    // 重新封装请求,记录响应日志
                    ServerHttpRequest decoratedRequest = requestDecorate(exchange, headers, outputMessage);
                    // 记录普通的
                    ServerHttpResponseDecorator decoratedResponse = recordResponseLog(exchange, gatewayLog);
                    return chain.filter(exchange.mutate().request(decoratedRequest).response(decoratedResponse).build()).then(Mono.fromRunnable(() -> {
                        writeAccessLog(gatewayLog);
                    }));
                }));
    }

    /**
     * 读取form-data数据
     *
     * @param exchange
     * @param chain
     * @param accessLog
     * @return
     */
    private Mono<Void> readFormData(ServerWebExchange exchange, GatewayFilterChain chain, AccessLog accessLog) {
        return DataBufferUtils.join(exchange.getRequest().getBody()).flatMap(dataBuffer -> {
            DataBufferUtils.retain(dataBuffer);
            final Flux<DataBuffer> cachedFlux = Flux.defer(() -> Flux.just(dataBuffer.slice(0, dataBuffer.readableByteCount())));
            final ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(exchange.getRequest()) {
                @Override
                public Flux<DataBuffer> getBody() {
                    return cachedFlux;
                }

                @Override
                public MultiValueMap<String, String> getQueryParams() {
                    return UriComponentsBuilder.fromUri(exchange.getRequest().getURI()).build().getQueryParams();
                }
            };
            final HttpHeaders headers = exchange.getRequest().getHeaders();
            if (headers.getContentLength() == 0) {
                return chain.filter(exchange);
            }
            ResolvableType resolvableType;
            if (MediaType.MULTIPART_FORM_DATA.isCompatibleWith(headers.getContentType())) {
                resolvableType = ResolvableType.forClassWithGenerics(MultiValueMap.class, String.class, Part.class);
            } else {                //解析 application/x-www-form-urlencoded
                resolvableType = ResolvableType.forClass(String.class);
            }
            return DEFAULT_MESSAGE_READERS
                    .stream()
                    .filter(reader -> reader.canRead(resolvableType, mutatedRequest.getHeaders().getContentType()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("no suitable HttpMessageReader."))
                    .readMono(resolvableType, mutatedRequest, Collections.emptyMap())
                    .flatMap(resolvedBody -> {
                        if (resolvedBody instanceof MultiValueMap) {
                            LinkedMultiValueMap map = (LinkedMultiValueMap) resolvedBody;
                            if (CollectionUtil.isNotEmpty(map)) {
                                StringBuilder builder = new StringBuilder();
                                //解析请求体报文
                                final Part bodyPartInfo = (Part) ((MultiValueMap) resolvedBody).getFirst("body");
                                if (bodyPartInfo instanceof FormFieldPart) {
                                    String body = ((FormFieldPart) bodyPartInfo).value();//
                                    log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> body: {}" + body);
                                    builder.append("body=").append(body);
                                }
                                // 解析请求id
                                final Part clientIdPartInfo = (Part) ((MultiValueMap) resolvedBody).getFirst("clientId");
                                if (clientIdPartInfo instanceof FormFieldPart) {
                                    String uid = ((FormFieldPart) clientIdPartInfo).value();
                                    log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> clientIdPartInfo:" + uid);
                                    accessLog.setUserId(uid);
                                    if (builder.length() > 0) {
                                        builder.append("&clientIdPartInfo=").append(uid);
                                    } else {
                                        builder.append("clientIdPartInfo=").append(uid);
                                    }
                                }
                                final Part timeStampPartInfo = (Part) ((MultiValueMap) resolvedBody).getFirst("timeStamp");
                                if (timeStampPartInfo instanceof FormFieldPart) {
                                    String timeStamp = ((FormFieldPart) timeStampPartInfo).value();//
                                    log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> timeStamp:" + timeStamp);
                                    if (builder.length() > 0) {
                                        builder.append("&timeStamp=").append(timeStamp);
                                    } else {
                                        builder.append("timeStamp=").append(timeStamp);
                                    }
                                }
                                final Part tokenPartInfo = (Part) ((MultiValueMap) resolvedBody).getFirst("token");
                                if (tokenPartInfo instanceof FormFieldPart) {
                                    String token = ((FormFieldPart) tokenPartInfo).value();//
                                    log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> token:" + token);
                                    if (builder.length() > 0) {
                                        builder.append("&token=").append(token);
                                    } else {
                                        builder.append("token=").append(token);
                                    }
                                }
                                final Part signPartInfo = (Part) ((MultiValueMap) resolvedBody).getFirst("sign");
                                if (signPartInfo instanceof FormFieldPart) {
                                    String sign = ((FormFieldPart) signPartInfo).value();//
                                    log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> sign:" + sign);
                                    if (builder.length() > 0) {
                                        builder.append("&sign=").append(sign);
                                    } else {
                                        builder.append("sign=").append(sign);
                                    }
                                }
                                accessLog.setRequestBody(builder.toString());
                            }
                        } else {
                            accessLog.setRequestBody((String) resolvedBody);
                        }
                        //获取响应体
                        ServerHttpResponseDecorator decoratedResponse = recordResponseLog(exchange, accessLog);
                        return chain
                                .filter(exchange
                                        .mutate()
                                        .request(mutatedRequest)
                                        .response(decoratedResponse).build()
                                ).then(Mono.fromRunnable(() -> {
                                    writeAccessLog(accessLog);
                                }));
                    });
        });
    }

    /**
     * 读取其他格式日志
     *
     * @param exchange
     * @param chain
     * @param accessLog
     * @return
     */
    private Mono<Void> writeBasicLog(ServerWebExchange exchange, GatewayFilterChain chain, AccessLog accessLog) {
        ServerHttpRequest request = exchange.getRequest();
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> writeBasicLog the request is: {}", JSONObject.toJSONString(request));
        Flux<DataBuffer> requestBody = request.getBody();
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        HttpMethod httpMethod = request.getMethod();
        URI uri = request.getURI();
        //get pathValue 形式默认保存全路径
        if (HttpMethod.GET.matches(httpMethod.name()) && queryParams.isEmpty()) {
            accessLog.setRequestBody(uri.getPath());
            //获取响应体
            ServerHttpResponseDecorator decoratedResponse = recordResponseLog(exchange, accessLog);
            return chain.filter(exchange.mutate().response(decoratedResponse).build()).then(Mono.fromRunnable(() -> {
                writeAccessLog(accessLog);
            }));
        } else if (HttpMethod.GET.matches(httpMethod.name()) && !queryParams.isEmpty()) {
            accessLog.setRequestBody(JSONObject.toJSONString(queryParams));
            //获取响应体
            ServerHttpResponseDecorator decoratedResponse = recordResponseLog(exchange, accessLog);
            return chain.filter(exchange.mutate().response(decoratedResponse).build()).then(Mono.fromRunnable(() -> {
                writeAccessLog(accessLog);
            }));
        } else {
            return DataBufferUtils.join(exchange.getRequest().getBody()).flatMap(dataBuffer -> {
                DataBufferUtils.retain(dataBuffer);
                final Flux<DataBuffer> cachedFlux = Flux.defer(() -> Flux.just(dataBuffer.slice(0, dataBuffer.readableByteCount())));
                final ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(exchange.getRequest()) {
                    @Override
                    public Flux<DataBuffer> getBody() {
                        return cachedFlux;
                    }

                    @Override
                    public MultiValueMap<String, String> getQueryParams() {
                        return UriComponentsBuilder.fromUri(exchange.getRequest().getURI()).build().getQueryParams();
                    }
                };
                StringBuilder reqSb = new StringBuilder();
                if (CollectionUtil.isNotEmpty(queryParams)) {
                    for (Map.Entry<String, List<String>> entry : queryParams.entrySet()) {
                        reqSb.append(entry.getKey()).append("=").append(StringUtils.join(entry.getValue(), ","));
                    }
                }
                String reqBodyStr = reqSb.toString();
                accessLog.setRequestBody(reqBodyStr);
                //获取响应体
                ServerHttpResponseDecorator decoratedResponse = recordResponseLog(exchange, accessLog);
                return chain.filter(exchange.mutate().request(mutatedRequest).response(decoratedResponse).build()).then(Mono.fromRunnable(() -> {
                    writeAccessLog(accessLog);
                }));
            });
        }
    }

    /**
     * 请求装饰器，重新计算 headers
     *
     * @param exchange
     * @param headers
     * @param outputMessage
     * @return
     */
    private ServerHttpRequestDecorator requestDecorate(ServerWebExchange exchange, HttpHeaders headers, CachedBodyOutputMessage outputMessage) {
        return new ServerHttpRequestDecorator(exchange.getRequest()) {
            @Override
            public HttpHeaders getHeaders() {
                long contentLength = headers.getContentLength();
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.putAll(super.getHeaders());
                if (contentLength > 0) {
                    httpHeaders.setContentLength(contentLength);
                } else {
                    httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
                }
                return httpHeaders;
            }

            @Override
            public Flux<DataBuffer> getBody() {
                return outputMessage.getBody();
            }
        };
    }

    /**
     * 记录响应日志
     * 通过 DataBufferFactory 解决响应体分段传输问题。
     *
     * @param exchange
     * @param gatewayLog
     * @return
     */
    private ServerHttpResponseDecorator recordResponseLog(ServerWebExchange exchange, AccessLog gatewayLog) {
        ServerHttpResponse response = exchange.getResponse();
        log.info(">>>>>>>>>>>>>>> accessLogFilter response is:{}", JSONObject.toJSONString(response));
        DataBufferFactory bufferFactory = response.bufferFactory();
        return new ServerHttpResponseDecorator(response) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                if (body instanceof Flux) {
                    Long responseTime = System.currentTimeMillis();
                    gatewayLog.setResponseTime(responseTime);
                    // 获取响应类型，如果是 json 就打印
                    String originalResponseContentType = exchange.getAttribute(ServerWebExchangeUtils.ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR);
                    log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>> originalResponseContentType =========== " + originalResponseContentType);
                    if (ObjectUtils.equals(this.getStatusCode(), HttpStatus.OK)//
                            && !StringUtil.isNullOrEmpty(originalResponseContentType)//
                            && originalResponseContentType.contains("application/json")) {
                        Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                        return super.writeWith(fluxBody.buffer().map(dataBuffers -> {
                            // 合并多个流集合，解决返回体分段传输
                            DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
                            DataBuffer join = dataBufferFactory.join(dataBuffers);
                            byte[] content = new byte[join.readableByteCount()];
                            join.read(content);
                            // 释放掉内存
                            DataBufferUtils.release(join);
                            String responseResult = new String(content, StandardCharsets.UTF_8);
                            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>> responseResult =========== " + responseResult);
                            gatewayLog.setResponseData(responseResult);
                            return bufferFactory.wrap(content);
                        }));
                    } else {
                        return super.writeWith(body);
                    }
                }
                // if body is not a flux. never got there.
                return super.writeWith(body);
            }
        };
    }

    /**
     * 默认在网关权限过滤之后进行处理，且优先与gateway默认拦截器，即必须大于-1
     *
     * @return
     */
    @Override
    public int getOrder() {
        return -90;
    }

    /**
     * 保存访问日志
     *
     * @param gatewayLog
     */
    private void writeAccessLog(AccessLog gatewayLog) {
//        accessLogService.saveAccessLog(gatewayLog);
    }
}
