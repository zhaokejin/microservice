package cn.cicoding;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJsonApplicationTests {

	@Test
	public void contextLoads() {
		Map<String , Object> jsonMap = new HashMap< String , Object>();
		jsonMap.put("a",1);
		jsonMap.put("b","");
		jsonMap.put("c",null);
		jsonMap.put("d","www.cicoding.cn");

		String str = JSONObject.toJSONString(jsonMap, SerializerFeature.WriteMapNullValue);
		System.out.println(str);
	}

}
