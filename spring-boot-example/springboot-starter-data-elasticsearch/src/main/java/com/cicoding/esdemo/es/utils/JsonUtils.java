package com.cicoding.esdemo.es.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;


/**
 * Json工具类封装
 *
 */
@Slf4j
public class JsonUtils {
    //    private static ObjectMapper objectMapper = new ObjectMapper();

    private static ObjectMapper objectMapper;
    static {
        Jackson2ObjectMapperBuilder objectMapperBuilder = new Jackson2ObjectMapperBuilder();
        objectMapperBuilder.simpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .createXmlMapper(Boolean.FALSE)
                .failOnUnknownProperties(Boolean.FALSE)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .featuresToDisable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES)
                .featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .indentOutput(true);
        objectMapper=objectMapperBuilder.build();
    }

    /**
     * This method deserializes the specified Json into an object of the specified class.
     *
     * @param paramJson        the string from which the object is to be deserialized
     * @param parametrized     actual full type
     * @param parameterClasses type parameters to parameterClasses
     * @return an object of type T from the string.
     * @author sunguangtao
     */
    public static <T> T fromJson(String paramJson, Class<?> parametrized, Class<?>... parameterClasses) {
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(parametrized, parameterClasses);
            return objectMapper.readValue(paramJson, javaType);
        } catch (IOException ignore) {
            log.error("transfer json[{}] string to object exception:{}", paramJson,
                    ExceptionUtils.getStackTrace(ignore));
        }

        return null;
    }

    /**
     * This method deserializes the specified Json into an object of the specified class.
     * <p>
     * QSSecResponse<List<Item>>
     *
     * @param paramJson      {
     *                       "code":"",
     *                       "message":"",
     *                       "data": [
     *                       "code":"ecs",
     *                       "name":"云服务器"
     *                       ]
     *                       }
     * @param pParametrized  QSSecResponse.class
     * @param cParametrized  List.class
     * @param parameterClass Item.class
     * @author sunguangtao
     * @date 2019/1/2
     */
    public static <T> T fromJson(String paramJson, Class<?> pParametrized, Class<?> cParametrized,
                                 Class<?> parameterClass) {
        JavaType cType = objectMapper.getTypeFactory().constructParametricType(cParametrized, parameterClass);
        JavaType pType = objectMapper.getTypeFactory().constructParametricType(pParametrized, cType);
        try {
            return objectMapper.readValue(paramJson, pType);
        } catch (IOException ignore) {
            log.error(ExceptionUtils.getStackTrace(ignore));
        }
        return null;
    }

    public static <T> T fromJsonByClass(String paramJson, Class<?>... parametrizeds) {
        int length = parametrizeds.length;
        JavaType resultJavaType = null;
        if (length < 2) {
            resultJavaType = objectMapper.getTypeFactory().constructType(parametrizeds[0]);
        } else {
            resultJavaType = Arrays.stream(parametrizeds)
                    .sorted(Collections.reverseOrder())
                    .skip(2)
                    .reduce(
                            objectMapper.getTypeFactory().constructParametricType(parametrizeds[length - 2], parametrizeds[length - 1]),
                            (parameterJavaType, parametrized) -> {
                                objectMapper.getTypeFactory().constructParametricType(parametrized, parameterJavaType);
                                return parameterJavaType;
                            },
                            (a, b) -> a
                    );
        }
        try {
            return objectMapper.readValue(paramJson, resultJavaType);
        } catch (IOException ignore) {
            log.error(ExceptionUtils.getStackTrace(ignore));
        }
        return null;
    }


    /**
     * This method deserializes the specified Json into an object of the specified class.
     * Note that the desired object must have a default constructor, otherwise jackson can
     * not instantiate the desired object
     *
     * @param paramJson the string from which the object is to be deserialized
     * @param toClass   the type of the desired object
     * @return an object of type T from the string.
     * @author sunguangtao
     */



    /**
     * This method serializes the specified object into its equivalent Json representation.
     *
     * @param object the object to be serialized
     * @return Json representation of object.
     * @author sunguangtao
     */
    public static <T> String toJson(T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception ignore) {
            log.error("transfer object to json exception:", ExceptionUtils.getStackTrace(ignore));
        }
        return null;
    }

    public static <T> T fromJson(String paramJson, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(paramJson, typeReference);
        } catch (IOException ignore) {
            log.error("transfer json[{}] string to object exception:{}", paramJson,
                    ExceptionUtils.getStackTrace(ignore));
        }
        return null;
    }

    public static <T> T fromJson(InputStream inputStream, Class<T> tClass) {
        try {
            return objectMapper.readValue(inputStream, tClass);
        } catch (IOException ignore) {
            log.error("transfer inputStream to object exception:{}",
                    ExceptionUtils.getStackTrace(ignore));
        }
        return null;
    }
}
