package cn.cicoding;

import cn.cicoding.service.FastDFSService;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootFastdfsApplicationTests {

    @Autowired
    private FastDFSService fastDFSService;

    @Test
    public void contextLoads() throws IOException {
        String local_filename = "C:\\Users\\Public\\Pictures\\Sample Pictures\\123.jpg";
        File f=new File(local_filename);
        String groupName= fastDFSService.upload(fastDFSService.File2byte(f),f.getName());
        System.out.println(groupName);
        IOUtils.write(fastDFSService.download(groupName), new FileOutputStream("D:/app/fastdfs/"+fastDFSService.getFileName(groupName)));
        System.out.println(fastDFSService.getFileInfo(groupName));
        System.out.println(fastDFSService.getFileMate(groupName));
        System.out.println(fastDFSService.delete(groupName)==0 ? "删除成功" : "删除失败");

    }

}
