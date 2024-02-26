package cn.cicoding.mybatis.service;

import cn.cicoding.mybatis.bean.UserExcel;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * created with IntelliJ IDEA.
 * author: cicoding
 * date: 2020/03/18
 * version: 1.0
 * description:
 */
public interface ExcelUserService {

    Object listAll(int page, int size);

    int insert(UserExcel user);

    int remove(Integer userId);

    int update(UserExcel user);

    void downloadExcel(ServletOutputStream outputStream);


    void upload(InputStream inputStream) throws IOException;

    void downloadExcels(ServletOutputStream outputStream);
}
