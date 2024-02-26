package cn.cicoding.mybatis.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import java.util.Date;

/**
 * created with IntelliJ IDEA.
 * author: cicoding
 * date: 2020/03/18
 * version: 1.0
 * description:
 */
@Data
public class UserExcel {

    /**
     * 主键ID
     */
    @Excel(name = "ID", width = 30, orderNum = "0")
    @ExcelProperty("ID")
    private Integer id;

    /**
     * 用户名
     */
    @Excel(name = "姓名", width = 30, orderNum = "1")
    @ExcelProperty("姓名")
    private String username;

    /**
     * 密码
     */
    @Excel(name = "密码", width = 30, orderNum = "2")
    @ExcelProperty("password")
    private String password;

    /**
     * 创建时间
     * 列宽为50  @ColumnWidth(50)
     */
    @Excel(name = "创建时间", width = 30, format = "yyyy年MM月dd日 HH时mm分ss秒", orderNum = "3")
    @ColumnWidth(50)
    @ExcelProperty("gmtCreate")
    @DateTimeFormat(value = "yyyy年MM月dd日 HH时mm分ss秒")
    private Date createTime;

}
