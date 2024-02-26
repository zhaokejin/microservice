package cn.cicoding.mybatis.controller;

import cn.cicoding.mybatis.service.ExcelUserService;
import cn.cicoding.mybatis.bean.UserExcel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * created with IntelliJ IDEA.
 * author: cicoding
 * date: 2020/03/18
 * version: 1.0
 * description:
 */
@Controller
public class ExcelUserController {

    @Resource
    private ExcelUserService excelUserService;


    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("content", "Hello Thymeleaf");
        return "index";
    }

    /**
     * 查询全部
     *
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/listAll")
    @ResponseBody
    public Object listAll(@RequestParam(value = "page", defaultValue = "1") int page,
                          @RequestParam(value = "size", defaultValue = "10") int size) {
        return excelUserService.listAll(page, size);
    }

    /**
     * 添加数据
     *
     * @param user
     * @return
     */
    @RequestMapping("/insert")
    @ResponseBody
    public int insert(UserExcel user) {
        return excelUserService.insert(user);
    }

    /**
     * 删除
     *
     * @param userId
     * @return
     */
    @RequestMapping("/remove")
    @ResponseBody
    public int remove(Integer userId) {
        return excelUserService.remove(userId);
    }

    /**
     * 修改
     *
     * @param user
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public int update(UserExcel user) {
        return excelUserService.update(user);
    }

    /**
     * 导出excel
     * @param response
     */
    @GetMapping("/excel/download")
    public void download(HttpServletResponse response) {
        try {
            response.reset();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition",
                    "attachment;filename=user_excel_" + System.currentTimeMillis() + ".xlsx");
            excelUserService.downloadExcel(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导入excel
     * @param file
     * @param model
     * @return
     */
    @PostMapping("/excel/upload")
    public String upload(@RequestParam(value = "file", required = true) MultipartFile file, Model model) {
        try {
            excelUserService.upload(file.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            return "success";
        }
        model.addAttribute("content", "上传成功！");
        return "success";
    }

    /**
     * 多sheel导出excel
     * @param response
     */
    @GetMapping("/excel/downloads")
    public void downloads(HttpServletResponse response) {
        try {
            response.reset();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition",
                    "attachment;filename=user_excel_" + System.currentTimeMillis() + ".xlsx");
            excelUserService.downloadExcels(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
