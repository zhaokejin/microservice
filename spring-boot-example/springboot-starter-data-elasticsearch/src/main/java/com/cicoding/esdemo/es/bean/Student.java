package com.cicoding.esdemo.es.bean;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @author cidoing
 * @version 1.0
 */
@Document(indexName = "idx_student")
@Getter
@Setter
@Builder
@ToString
public class Student {

    @Id
    private Long id;

    @Field(name = "name",analyzer = "")
    private String name;

    private int age;

    private String address;

    private String sex;

    private String phone;
}
