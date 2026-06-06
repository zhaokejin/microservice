package cn.cicoding.dubbo.provider.impl;

import cn.cicoding.dubbo.api.SayService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class SayServiceImpl implements SayService {

    @Override
    public String sayHelloByName(String name) {
        return "Hello, " + name;
    }
}
