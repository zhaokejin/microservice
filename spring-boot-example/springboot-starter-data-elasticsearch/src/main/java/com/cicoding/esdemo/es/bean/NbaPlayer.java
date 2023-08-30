package com.cicoding.esdemo.es.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @author cidoing
 */
@Data
@Document(indexName = "nba_latest")
public class NbaPlayer {
    @Id
    private Integer id;


    private String countryEn;
    private String country;
    private String code;
    private String displayAffiliation;
    @Field(name = "displayName",analyzer = "ik_max_word")
    private String displayName;

    private Integer draft;
    private String schoolType;
    private String weight;
    private Integer playYear;
    private String jerseyNo;
    private Long birthDay;
    private String birthDayStr;
    private String displayNameEn;
    private String position;
    private Double heightValue;
    private String playerId;
    private String teamCity;
    private String teamCityEn;
    private String teamName;
    private String teamNameEn;
    private String teamConference;
    private String teamConferenceEn;
    private Integer age;

}
