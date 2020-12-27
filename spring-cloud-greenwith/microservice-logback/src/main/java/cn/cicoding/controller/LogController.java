package cn.cicoding.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * 测试日志等级
     * @return
     */
    @RequestMapping("/loglevel")
    public String logLevel(){
        log.debug("debug log");
        log.info("info log");
        log.error("error log");
        return "success";
    }

}
