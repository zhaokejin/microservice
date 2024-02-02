package com.cicoding.utils.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;

/***
 * json工具类
 * 
 * @author cicoding.cn
 *
 */
public class JsonUtils {
	
	/***
	 * 对象转json字符串
	 *
	 * @param o
	 * @return
	 */
	public static String beanToString(Object o) {
		return JSON.toJSONString(o, SerializerFeature.BrowserCompatible, SerializerFeature.IgnoreErrorGetter);
	}
	
	/***
	 * 对象转json字符串
	 * 
	 * @param o
	 * @param featureList
	 * @return
	 */
	public static String beanToString(Object o, SerializerFeature... featureList) {
		return JSON.toJSONString(o, featureList);
	}
	
	/***
	 * 字符串转对象
	 * 
	 * @param json
	 * @param c
	 * @return
	 */
	public static <T> T stringToBean(String json, Class<T> c) {
		return JSON.parseObject(json, c);
	}
	
	/***
	 * 字符串转对象数组
	 * 
	 * @param json
	 * @param c
	 * @return
	 */
	public static <T> List<T> stringToBeanList(String json, Class<T> c) {
		return JSON.parseArray(json, c);
	}
	
}
