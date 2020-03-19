package cn.cicoding.user.feign;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@FeignClient(name = "microservice-flowable")
public interface FlowableFeignClient {
  @GetMapping("/test/test")
  long test();

  /**
   * 添加报销
   * @param userId      用户
   * @param money       金额
   * @param descption   描述
   * @return
   */
  @GetMapping("/expense/add")
  String addExpense(@RequestParam("userId") String userId, @RequestParam("money") Integer money, @RequestParam("descption") String descption);

  /**
   * 获取审批管理列表
   * @param userId    用户
   * @return
   */
  @GetMapping(value = "/expense/list")
  Object list( @RequestParam("userId") String userId);

  /**
   * 批准
   * @param taskId    任务ID
   * @return
   */
  @GetMapping(value = "/expense/apply")
  String apply(@RequestParam("taskId") String taskId);

  /**
   * 拒绝
   * @param taskId    任务ID
   * @return
   */
  @GetMapping(value = "/expense/reject")
  String reject(@RequestParam("taskId") String taskId);

  /**
   * 生成流程图
   * @param httpServletResponse
   * @param processId   任务ID
   * @throws Exception
   */
  @RequestMapping(value = "/expense/feginProcessDiagram",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  Response genFeginProcessDiagram(@RequestParam("httpServletResponse") HttpServletResponse httpServletResponse, @RequestParam("processId") String processId) throws Exception;

}
