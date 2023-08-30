package cn.cicoding.delay.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 延迟任务
 * @author zhaokejin
 * @date 2021-07-25 15:24
 **/
@Data
@AllArgsConstructor
public class DelayJob implements Serializable {


    /**
     * 延迟任务的唯一标识
     */
    private long jodId;
    
    /**
     * 任务的执行时间
     */
    private long delayDate;

    /**
     * 任务类型（具体业务类型）
     */
    private String topic;


    public DelayJob(Job job) {
        this.jodId = job.getId();
        this.delayDate = System.currentTimeMillis() + job.getDelayTime();
        this.topic = job.getTopic();
    }

    public DelayJob(Object value, Double score) {
        this.jodId = Long.parseLong(String.valueOf(value));
        this.delayDate = System.currentTimeMillis() + score.longValue();
    }
}
