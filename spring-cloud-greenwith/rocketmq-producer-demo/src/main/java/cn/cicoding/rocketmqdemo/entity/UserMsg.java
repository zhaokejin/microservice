package cn.cicoding.rocketmqdemo.entity;
 
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
 
import java.io.Serializable;
 
/**
 * Created by yanshao on 2019/05/04
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserMsg implements Serializable {
    private long userId;
    private String name;
    private int age;
    private String address;
}