package com.abc.repository;

import com.abc.bean.Student;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

// 第一个泛型：要操作对象的类型
// 第二个泛型：要操作对象的Id类型
public interface StudentRepository extends ReactiveMongoRepository<Student, String> {

    /**
     *  根据年龄上下限查询
     * @param below  年龄下限(不包含)
     * @param top  年龄上限(不包含)
     * @return
     */
    Flux<Student> findByAgeBetween(int below, int top);

}
