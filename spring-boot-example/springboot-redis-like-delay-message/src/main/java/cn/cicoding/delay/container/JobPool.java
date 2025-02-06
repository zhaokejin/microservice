package cn.cicoding.delay.container;

import cn.cicoding.delay.bean.Job;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


/**
 * 任务池
 * @author cicoding.cn
 * @date 2021-07-26 14:38
 **/
@Component
@Slf4j
public class JobPool {
    
    @Autowired
    private RedisTemplate redisTemplate;

    private String NAME = "job.pool";
    
    private BoundHashOperations getPool () {
        BoundHashOperations ops = redisTemplate.boundHashOps(NAME);
        return ops;
    }

    /**
     * 添加任务
     * @param job
     */
    public void addJob (Job job) {
        log.info("任务池添加任务：{}", JSON.toJSONString(job));
        getPool().put(job.getId(),job);
        return ;
    }

    /**
     * 获得任务
     * @param jobId
     * @return
     */
    public Job getJob(Long jobId) {
        Object o = getPool().get(jobId);
        if (o instanceof Job) {
            return (Job) o;
        }
        return null;
    }

    /**
     * 移除任务
     * @param jobId
     */
    public void removeDelayJob (Long jobId) {
        log.info("任务池移除任务：{}",jobId);
        // 移除任务
        getPool().delete(jobId);
    }
}
