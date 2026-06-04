package cn.cicoding.provider.service.impl;

import cn.cicoding.demo.serivce.SayService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author Neo.Zzj
 * @date 2020/12/18
 */
@DubboService
public class SayServiceImpl implements SayService {

    @Override
    public String sayHelloByName(String name) {
        return name + ",hello!";
    }
}
