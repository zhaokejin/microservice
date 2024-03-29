package cn.cicoding;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: cicoding
 * Description: 测试类编写
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    /**
     *  注入一个web应用环境(容器)
     */
    @Resource
    private WebApplicationContext webApplicationContext;

    //mvc 环境对象
    private MockMvc mockMvc;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void findObject() throws Exception {
        String str = mockMvc.perform(MockMvcRequestBuilders.get("/1/2")
                .contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andReturn().getResponse().getContentAsString();

        System.out.println(str);
    }
}
