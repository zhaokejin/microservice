package cn.cicoding.controller;

import cn.cicoding.lock.DistributedLockByCurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/curator")
public class CuratorController {

    private static final Logger logger = LoggerFactory.getLogger(CuratorController.class);

    @Autowired
    private DistributedLockByCurator distributedLockByZookeeper;

    private final static String PATH = "test";

    @GetMapping("/lock1")
    public Boolean getLock1() {
        Boolean flag;
        distributedLockByZookeeper.acquireDistributedLock(PATH);
        try {
            logger.info("I am lock1，i am updating resource……！！！");
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            flag = distributedLockByZookeeper.releaseDistributedLock(PATH);
        }
        return flag;
    }

    @GetMapping("/lock2")
    public Boolean getLock2() {
        Boolean flag;
        distributedLockByZookeeper.acquireDistributedLock(PATH);
        try {
            logger.info("I am lock2，i am updating resource……！！！");
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            flag = distributedLockByZookeeper.releaseDistributedLock(PATH);
        }
        return flag;
    }
}
