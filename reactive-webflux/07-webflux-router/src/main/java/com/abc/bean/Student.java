package com.abc.bean;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
// 指定在MongoDB中生成的表名
@Document(collection = "t_student")
public class Student {

    @Id
    // MonogoDB表中的id一般为字符串类型
    private String id;
    private String name;
    private int age;
}
