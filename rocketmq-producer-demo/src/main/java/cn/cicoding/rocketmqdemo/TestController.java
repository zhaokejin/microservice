package cn.cicoding.rocketmqdemo;

import cn.cicoding.rocketmqdemo.entity.UserMsg;
import cn.cicoding.rocketmqdemo.producer.MsgProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 〈一句话功能简述〉<br>
 * 〈${DESCRIPTION}〉
 *
 * @author zhaokejin
 * @create 2019/5/5
 */
@Controller
public class TestController {

	@Autowired
	private MsgProducer msgProducer;

	/**
	 * 测试生产者创建消息
	 * @param info
	 * @return
	 * @throws Exception
	 */
	@GetMapping("test")
	@ResponseBody
	public String Ttest(String info) throws Exception {
		int i = 0;
		while (i < 10){
			UserMsg userMsg = UserMsg.builder()
					.userId(10000 + i)
					.name("张三" + i)
					.age(10 + i)
					.address("上海")
					.build();
			i++;
			msgProducer.sendUserMsg(userMsg);
//			TimeUnit.SECONDS.sleep(2);
		}

		return "success";
	}
}