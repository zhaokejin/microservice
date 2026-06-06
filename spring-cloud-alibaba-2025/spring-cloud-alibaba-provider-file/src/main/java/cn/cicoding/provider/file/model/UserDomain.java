package cn.cicoding.provider.file.model;

import lombok.Data;

@Data
public class UserDomain {
    private Integer id;
    private String userName;
    private String password;
    private String phone;
    private String createTime;
}
