package com.cicoding.esdemo;

import com.cicoding.esdemo.es.bean.Student;
import com.cicoding.esdemo.es.repository.StudentRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ElasticsearchRestTemplateTest {
    private final static String IDX_STUDENT = "idx_student";

    @Autowired
    private ElasticsearchRestTemplate template;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void deleteIndex(){

        indexStudent().delete();
    }
    @Test
    public void createIndex(){
        indexStudent().create();
    }
    @Test
    public void createMapping(){
        Document mapping = indexStudent()
                .createMapping(Student.class);
        System.out.println(mapping.toJson());

    }

    private IndexOperations indexStudent(){
        return  template.indexOps(IndexCoordinates.of(IDX_STUDENT));
    }
    private IndexOperations indexNba(){
        return  template.indexOps(IndexCoordinates.of("nba_player"));
    }
    @Test
    public void testInsert(){
        Student student = Student.builder()
                .address("GuangDong Province")
                .age(22)
                .name("zhangvb22")
                .id(System.currentTimeMillis())
                .sex("0")
                .phone("13500001111")
                .build();
        studentRepository.save(student);
    }

    @Test
    public  void testUpdate(){

        System.out.println(indexNba().getMapping());
    }
    @Test
    public void testQuery(){

        Iterable<Student> students = studentRepository.findAll();
        students.forEach(System.out::println);
    }

    @Test
    public void testNativeSearch(){

        NativeSearchQueryBuilder queryBuilder =
                new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.termQuery("id", 1))
                .withQuery(QueryBuilders.matchQuery("name","3"))
                .withFilter(QueryBuilders.rangeQuery("age").gte(20));
        SearchHits<Student> search = template.search(queryBuilder.build(), Student.class);
        System.out.println(search.getSearchHits());
    }

    @Test
    public void testGetMappings() throws IOException {

    }
}
