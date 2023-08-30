package com.cicoding.esdemo.es.repository;


import com.cicoding.esdemo.es.bean.Student;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author cidoing
 * @version 1.0
 * @date 2020/9/21
 * @since 2020/9/21
 */
@Repository
public interface StudentRepository extends ElasticsearchRepository<Student, Long> {

}
