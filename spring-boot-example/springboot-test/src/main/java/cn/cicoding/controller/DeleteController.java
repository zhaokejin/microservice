package cn.cicoding.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: cicoding
 */
@RestController
public class DeleteController {

    private final Map<String, Object> params = new HashMap<String, Object>();

    @DeleteMapping("/delete")
    public Object del(String id){
        params.clear();
        params.put("id", id);
        return params;
    }


}
