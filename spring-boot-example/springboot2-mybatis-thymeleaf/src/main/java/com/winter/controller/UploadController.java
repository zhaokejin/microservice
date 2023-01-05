package com.winter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/***
 * UploadController
 * 
 * @author zhaokejin
 *
 */
@Controller
public class UploadController {

	/**
	 * 处理文件上传
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/uploadimg")
	public @ResponseBody
	Map<String,Object> demo(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file, HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String fileName = file.getOriginalFilename();
		//保存
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get("D:\\images\\" + file.getOriginalFilename());
			Files.write(path, bytes);
			resultMap.put("success", 1);
			resultMap.put("message", "上传成功！");
			resultMap.put("url","../images/"+fileName);
		} catch (Exception e) {
			resultMap.put("success", 0);
			resultMap.put("message", "上传失败！");
			e.printStackTrace();
		}
		System.out.println(resultMap.get("success"));
		return resultMap;


	}

}
