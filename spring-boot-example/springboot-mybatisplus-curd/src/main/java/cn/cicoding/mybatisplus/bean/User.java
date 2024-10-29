package cn.cicoding.mybatisplus.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * User
 *
 * @author cicoding
 * @version v1.0
 * @since 2020/3/10 1:30
 */
@Data
@TableName(value = "t_user")
public class User implements Serializable {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 实体类日期字段返回格式化显示
     * 第一，返回时间格式
     * 1.按照年月日日期格式返回
     * @JsonFormat(pattern=“yyyy-MM-dd”,timezone=“GMT+8”)
     * 2.按照年月日时分秒格式返回
     * @JsonFormat(pattern=“yyyy-MM-dd HH:mm:ss”,timezone=“GMT+8”)
     *
     * 例如：
     * 实体类修改
     * @JsonFormat(pattern=“yyyy-MM-dd”,timezone=“GMT+8”)
     * private Date startDate; //活动开始时间
     * 返回结果
     * 数据库返回结果为：“2022-09-09 08:08:08”
     * controller接口返回结果 startDate = ‘2022-09-09’
     *
     * 注意点：
     * (1)pattern参数可以根据不同时间戳修改，返回格式按照时间戳格式返回
     * (2)使用@JsonFormat引起的时间比正常时间慢8小时，默认情况下timeZone为GMT(即标准时区)所以改为背景时间需要加上timezone=“GMT+8”
     *
     * 第二，传入时间格式
     * 参考格式如上，示例如下：
     * @DateTimeFormat(pattern =“yyyy-MM-dd”)
     * 结果：
     * 前端参数(“2022-09-09 08:08:08”)传到后台接收参数结果为：“2022-09-09”
     *
     * 创建时间
     */
    @TableField("createTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

}
