package cn.cicoding;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConfigClientController {

    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private boolean useLocalCache;

    @GetMapping(value = "/get")
    @ResponseBody
    public boolean get() {
        return useLocalCache;
    }

    @NacosInjected
    private NamingService namingService;

    @GetMapping(value = "/get1")
    @ResponseBody
    public List<Instance> get(@RequestParam String serviceName) throws NacosException {
        return namingService.getAllInstances(serviceName);
    }
}