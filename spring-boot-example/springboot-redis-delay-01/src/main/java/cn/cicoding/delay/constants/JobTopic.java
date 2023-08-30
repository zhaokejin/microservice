package cn.cicoding.delay.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务类别
 * @author zhaokejin
 * @date 2021-07-29 11:25
 **/
@AllArgsConstructor
@Getter
public enum  JobTopic {

    TOPIC_ONE("one"),
    TOPIC_TWO("two");

    private String topic;
}
