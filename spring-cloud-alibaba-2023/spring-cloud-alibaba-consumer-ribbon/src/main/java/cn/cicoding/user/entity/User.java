package cn.cicoding.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
  private Integer userId;

  private String userName;

  private String password;

  private String phone;
}
