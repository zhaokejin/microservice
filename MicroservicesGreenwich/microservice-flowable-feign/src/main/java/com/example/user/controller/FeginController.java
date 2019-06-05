package com.example.user.controller;

import com.example.user.feign.FlowableFeignClient;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;

@RequestMapping("/fegin")
@RestController
public class FeginController {
  @Autowired
  private FlowableFeignClient userFeignClient;
  @Autowired
  private RestTemplate restTemplate;

  @GetMapping("flowable")
  public long findById() {
    return this.userFeignClient.test();
  }

  @GetMapping("add")
  public String add() {
    String userId = "username";
    Integer money = 200;
    String descption = "test";
    return this.userFeignClient.addExpense(userId,money,descption);
  }

  /**
   * 获取审批管理列表
   */
  @RequestMapping(value = "/list")
  @ResponseBody
  public Object list(String userId) {

    return userFeignClient.list(userId);
  }

  /**
   * 批准
   *
   * @param taskId 任务ID
   */
  @RequestMapping(value = "apply")
  @ResponseBody
  public String apply(String taskId) {
    return userFeignClient.apply(taskId);
  }

  /**
   * 拒绝
   */
  @ResponseBody
  @RequestMapping(value = "reject")
  public String reject(String taskId) {
    userFeignClient.reject(taskId);
    return "reject";
  }

  /**
   * 生成流程图fegin
   *
   * @param processId 任务ID
   */
  @RequestMapping(value = "feginProcessDiagram")
  public void genProcessDiagram(HttpServletResponse httpServletResponse, String processId) throws Exception {
    Response response = userFeignClient.genFeginProcessDiagram(httpServletResponse, processId);
    Response.Body body = response.body();
    InputStream in =  body.asInputStream();
    System.out.println(in);

    OutputStream out = null;
    byte[] buf = new byte[1024];
    int legth = 0;
    try {
      out = httpServletResponse.getOutputStream();
      while ((legth = in.read(buf)) != -1) {
        out.write(buf, 0, legth);
      }
    } finally {
      if (in != null) {
        in.close();
      }
      if (out != null) {
        out.close();
      }
    }
  }

  /**
   * 生成流程图ribbon
   *
   * @param processId 任务ID
   */
  @RequestMapping(value = "ribbonProcessDiagram/{processId}")
  public void genProcessDiagram1(HttpServletResponse httpServletResponse, @PathVariable String processId) throws Exception {
    ResponseEntity<Resource> entity = restTemplate.postForEntity("http://microservice-flowable/expense/ribbonProcessDiagram/{processId}", processId, Resource.class,processId);
    InputStream in = entity.getBody().getInputStream();
    System.out.println(in);

    OutputStream out = null;
    byte[] buf = new byte[1024];
    int legth = 0;
    try {
      out = httpServletResponse.getOutputStream();
      while ((legth = in.read(buf)) != -1) {
        out.write(buf, 0, legth);
      }
    } finally {
      if (in != null) {
        in.close();
      }
      if (out != null) {
        out.close();
      }
    }
  }
}
