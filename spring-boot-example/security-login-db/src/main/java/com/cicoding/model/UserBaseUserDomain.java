package com.cicoding.model;

import java.util.Date;

/**
 * 用户基本信息bean
 * @author  weicong
 * @version  1.0 createTime 2018-12-14 21:24:03
 */
public class UserBaseUserDomain {

    /**
     * 用户基本信息id
     */
    private String userBaseId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 性别
     * 1是男 0是女
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 说明
     */
    private String remark;

    /**
     * 微信
     */
    private String weixin;

    /**
     * qq号码
     */
    private String qq;

    /**
     * 手机号码
     */
    private String modbilePhone;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer isDel;

    /**
     * 住址
     */
    private String address;

    /**
     * 住址 县级编码
     */
    private String areaCode;

    /**
     * 住址 市级编码
     */
    private String cityCode;

    /**
     * 住址 省级编码
     */
    private String provCode;

    /**
     * 身份证号码
     */
    private String idCard;

    public String getUserBaseId() {
        return userBaseId;
    }

    public void setUserBaseId(String userBaseId) {
        this.userBaseId = userBaseId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getModbilePhone() {
        return modbilePhone;
    }

    public void setModbilePhone(String modbilePhone) {
        this.modbilePhone = modbilePhone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getProvCode() {
        return provCode;
    }

    public void setProvCode(String provCode) {
        this.provCode = provCode;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
