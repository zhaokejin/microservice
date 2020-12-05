package cn.cicoding.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

@Component
//@ConditionalOnClass(name = "com.netflix.zuul.ZuulFilter")
public class ServiceErrorConvertFilter extends ZuulFilter {

	Logger logger = LogManager.getLogger();

	@Override
	public Object run() {
		RequestContext requestContext = RequestContext.getCurrentContext();
		String jwtHeader = "bearer eyJhbGciOiJSUzI1NiIsInR5cxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxassasdasfgdagdfgdfgdfgadf";
		requestContext.addZuulRequestHeader("Authorization", jwtHeader);
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return FilterConstants.PRE_DECORATION_FILTER_ORDER - 4;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

}
