package cn.cicoding.controller;

import cn.cicoding.entity.DocBean;
import cn.cicoding.service.IElasticService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/elastic")
public class ElasticController {

    @Autowired
    private IElasticService elasticService;

    @RequestMapping("/init")
    public String init(Model model){
        elasticService.createIndex();
        List<DocBean> list =new ArrayList<>();
        list.add(new DocBean(1L,"XX0193","XX8064","Sharding-JDBC 垂直拆分（不同的表在不同的库中）",1));
        list.add(new DocBean(2L,"XX0210","XX7475","SpringCloud如何使用Feign构造多参数的请求",1));
        list.add(new DocBean(3L,"XX0257","XX8097","SpringBoot2.X使用Redis实现分布式锁机制",1));
        elasticService.saveAll(list);
        model.addAttribute("content", "content");
        return "index";

    }

    @RequestMapping("/all")
    @ResponseBody
    public Iterator<DocBean> all(){
        return elasticService.findAll();
    }

    /**
     * elasticsearchTemplateQuery
     * @param key
     * @param index
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/elasticsearchTemplateQuery")
//    @ResponseBody
    public String elasticsearchTemplateQuery(String key, String index, Model model) throws Exception {
        System.out.println("(query.do)搜索："+key);
//        ModelAndView mv = new ModelAndView();
//        if (key == null || key.trim().length() == 0) {
//            System.out.println("输入内容为空！");
//            return "index";
//        }
        //注意：jquery.pagination.js页码从0开始
        int pageNum = 1;
        int pageSize=20;
        if (index!=null&&Integer.parseInt(index)>1){
            pageNum=Integer.parseInt(index);
        }
        System.out.println("----------------------");
        List<DocBean> list = elasticService.highlight(key, pageNum, pageSize);
        model.addAttribute("list", list);
        model.addAttribute("key", key);
        return "index";
    }

    @RequestMapping(value = "/elasticRepositoryQuery")
    @ResponseBody
    public List<DocBean> elasticRepositoryQuery(String key, String index) throws Exception {
        System.out.println("(query.do)搜索："+key);
//        ModelAndView mv = new ModelAndView();
//        if (key == null || key.trim().length() == 0) {
//            System.out.println("输入内容为空！");
//            return null;
//        }
        //注意：jquery.pagination.js页码从0开始
        int pageNum = 1;
        int pageSize=20;
        if (index!=null&&Integer.parseInt(index)>1){
            pageNum=Integer.parseInt(index);
        }
        System.out.println("----------------------");
        List<DocBean> docBeans = elasticService.highlightQuery(key, pageNum, pageSize);
        System.out.println(docBeans);
        docBeans.forEach(x->System.out.println(x));


        return docBeans;
    }

}

