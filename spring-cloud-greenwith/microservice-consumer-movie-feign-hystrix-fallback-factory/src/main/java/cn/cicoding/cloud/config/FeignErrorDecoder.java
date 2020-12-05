package cn.cicoding.cloud.config;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            // 这里直接拿到我们抛出的异常信息
            String message = Util.toString(response.body().asReader());
            try {
                JSONObject jsonObject = JSONObject.parseObject(message);
//                JSONObject jsonObject = new JSONObject(message);
                return new BaseException(jsonObject.getString("message"), jsonObject.getInteger("status"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (IOException ignored) {
        }
        return decode(methodKey, response);
    }
}
