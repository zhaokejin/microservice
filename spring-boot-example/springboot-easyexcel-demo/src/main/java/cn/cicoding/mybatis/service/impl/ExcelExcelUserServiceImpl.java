package cn.cicoding.mybatis.service.impl;

import cn.cicoding.mybatis.bean.Grate;
import cn.cicoding.mybatis.mapper.ExcelUserMapper;
import cn.cicoding.mybatis.service.ExcelUserService;
import cn.cicoding.mybatis.bean.UserExcel;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * created with IntelliJ IDEA.
 * author: cicoding
 * date: 2020/03/18
 * version: 1.0
 * description:
 */
@Slf4j
@Service
public class ExcelExcelUserServiceImpl implements ExcelUserService {

    @Resource
    private ExcelUserMapper excelUserMapper;

    @Override
    public Object listAll(int page, int size) {
        PageHelper.startPage(page, size);
        List<UserExcel> userList = excelUserMapper.listAll();
        PageInfo<UserExcel> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    @Override
    public int insert(UserExcel user) {


        return excelUserMapper.insert(user);
    }

    @Override
    public int remove(Integer userId) {
        return excelUserMapper.remove(userId);
    }

    @Override
    public int update(UserExcel user) {
        return excelUserMapper.update(user);
    }

    @Override
    public void downloadExcel(ServletOutputStream outputStream) {
        List<UserExcel> userList = excelUserMapper.listAll();
        EasyExcelFactory.write(outputStream, UserExcel.class).registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).sheet("User").doWrite(userList);
    }

    /**
     * https://github.com/alibaba/easyexcel/blob/master/easyexcel-test/src/test/java/com/alibaba/easyexcel/test/demo/write/WriteTest.java#L640C39-L640C75
     * @param outputStream
     */
    @Override
    public void downloadExcels(ServletOutputStream outputStream) {
        List<UserExcel> userList = excelUserMapper.listAll();
        List<Grate> grateList = new ArrayList<>();
        grateList.add(new Grate("一年级","班主任张三","李雷","英语"));
        grateList.add(new Grate("二年级","班主任张三","李雷","英语"));
        grateList.add(new Grate("三年级","班主任李四","王五","数学"));
        grateList.add(new Grate("","","",""));
        ExcelWriter excelWriter = EasyExcel.write(outputStream).build();
        WriteSheet studentSheet = EasyExcel.writerSheet(0, "学生信息").head(UserExcel.class).build();
        WriteSheet grateSheet = EasyExcel.writerSheet(1, "班级信息").head(Grate.class).build();
        excelWriter.write(userList, studentSheet).write(grateList, grateSheet);
        excelWriter.finish();
    }

    @Override
    public void upload(InputStream inputStream) throws IOException {

        List<UserExcel> cachedDataList = new ArrayList<>();

        // ReadListener不是必须的，它主要的设计是读取excel数据的后置处理(并考虑一次性读取到内存潜在的内存泄漏问题)
        EasyExcelFactory.read(inputStream, UserExcel.class, new ReadListener<UserExcel>() {

            @Override
            public void invoke(UserExcel user, AnalysisContext analysisContext) {
                cachedDataList.add(user);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                cachedDataList.forEach(user -> log.info(user.toString()));
            }
        }).sheet().doRead();
    }


}
