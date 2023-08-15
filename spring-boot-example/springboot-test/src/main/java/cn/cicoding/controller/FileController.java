package cn.cicoding.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: cicoding
 */
@RestController
@RequestMapping("/file")
public class FileController {

    private final static String filePath = "D://data/";

    @RequestMapping("/upload")
    public JSONObject upload(@RequestParam("file") MultipartFile file, HttpServletRequest request){

        JSONObject result = new JSONObject();

        // 姓名
        String name = request.getParameter("name");
        System.out.println("姓名：" + name);

        System.out.println(file.getSize());


        // 文件名
        String fileName = file.getOriginalFilename();
        System.out.println("文件名： " + fileName);

        // 文件后缀
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        System.out.println("文件后缀名： " + suffixName);

        // 重新生成唯一文件名，用于存储数据库
        String newFileName = UUID.randomUUID().toString()+suffixName;
        System.out.println("新的文件名： " + newFileName);

        //创建文件
        File dest = new File(filePath + newFileName);

        Map map = new HashMap();
        map.put("filePath", dest.getAbsolutePath());
        map.put("name", name);


        try {
            file.transferTo(dest);
            result.put("success", true);
            result.put("data", map);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (JSONObject) result.put("success", false);

    }

}
