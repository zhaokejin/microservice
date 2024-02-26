package cn.cicoding.mybatis.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grate implements Serializable {

    @ExcelProperty("班级")
    private String grate;

    @ExcelProperty("班主任老师")
    private String teacher;

    @ExcelProperty("任课老师")
    private String instructor;

    @ExcelProperty("学科")
    private String subject;
}
