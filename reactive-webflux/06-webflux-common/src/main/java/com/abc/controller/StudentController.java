package com.abc.controller;

import com.abc.bean.Student;
import com.abc.repository.StudentRepository;
import com.abc.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository repository;

    // 一次性返回所有数据
    @GetMapping("/all")
    public Flux<Student> getAll() {
        return repository.findAll();
}

    // 使用SSE形式返回所有数据
    @GetMapping(value = "/sse/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> getAllSSE() {
        return repository.findAll();
    }

    // 添加数据
    @PostMapping("/save")
    public Mono<Student> saveStudent(@Valid @RequestBody Student student) {
        // 验证姓名的合法性
        ValidatorUtil.validateName(student.getName());
        return repository.save(student);
    }

    // 根据id删除
    @DeleteMapping("/delcomm/{id}")
    public Mono<Void> deleteStudent(@PathVariable("id") String id) {
        return repository.deleteById(id);
    }

    // 修改数据
    @PutMapping("/update")
    public Mono<Student> updateStudent(@Valid @RequestBody Student student) {
        // 验证姓名的合法性
        ValidatorUtil.validateName(student.getName());
        return repository.save(student);
    }

    // 根据id查询，其查询结果为：若指定id存在，其会返回该对象数据及200，
    // 若指定id不存在，其仍会返回200，只不过没有对象数据
    @GetMapping("/find/{id}")
    public Mono<Student> findById(@PathVariable("id") String id) {
        return repository.findById(id);
    }

    // 有状态删除：若指定id的对象不存在，则返回状态404
    // 若指定id对象存在，删除成功，则返回状态200
    // ResponseEntity用于封装两类信息：响应数据对象与响应码，其泛型用于指定响应数据对象类型
    @DeleteMapping("/delstat/{id}")
    public Mono<ResponseEntity<Void>> delStudent(@PathVariable("id") String id) {
        return repository.findById(id)   // 根据id查询，若找到了指定id的对象，则返回一个Mono序列，其元素为查找到的对象
                // flatMap()对前面findById()的结果做映射，即对Mono序列中的元素做映射，其参数类型为Function，即一个输入
                // 一个输出。该映射是将一个stu对象映射一个删除操作，而delete()方法没有返回值，所以通过then()方法为其添加
                // 一个返回值
                // flatMap()：一般情况下，若要将元素映射为一个操作，则使用flatMap()，可以使操作异步执行
                // map()：若仅是将元素映射为另一种类型，则使用map()，同步执行
                .flatMap(stu -> repository.delete(stu)
                                          .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                // 若findById()返回的Mono序列中没有元素，则执行defaultIfEmpty()方法
                // defaultIfEmpty()方法的参数是ResponseEntity类型，返回值为Mono<ResponseEntity>
                .defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }


    // 有状态修改：若修改成功，则返回要修改的对象及200；若指定的id不存在，则返回404
    @PutMapping("/updatestat/{id}")
    public Mono<ResponseEntity<Student>> updateStudent(@PathVariable("id") String id, @Valid @RequestBody Student student) {
        // 验证姓名的合法性
        ValidatorUtil.validateName(student.getName());
        return repository.findById(id)
                .flatMap(stu -> {
                    stu.setName(student.getName());
                    stu.setAge(student.getAge());
                    return repository.save(stu);
                })    // flatMap()的返回值类型为Mono<Student>，即序列中的元素为Student类型，而该处理器方法要求的返回值
                      // 类型为Mono<ResponseEntity<Student>>，即序列中的元素为ResponseEntity<Student>，所以需要将序列
                      // 中的元素类型由Student类型映射为ResponseEntity<Student>类型，所以需要调用map()方法进行映射
                .map(stu -> new ResponseEntity<Student>(stu, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<Student>(HttpStatus.NOT_FOUND));
    }

    // 有状态删除：若指定id存在，则返回查询到的对象及状态200，否则返回404
    @GetMapping("/findstat/{id}")
    public Mono<ResponseEntity<Student>> findByIdStat(@PathVariable("id") String id) {
        return repository.findById(id)
                .map(stu -> new ResponseEntity<Student>(stu, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<Student>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/age/{below}/{top}")
    public Flux<Student> findByAge(@PathVariable("below") int below, @PathVariable("top") int top) {
        return repository.findByAgeBetween(below, top);
    }

    @GetMapping(value = "/sse/age/{below}/{top}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> findByAgeSSE(@PathVariable("below") int below, @PathVariable("top") int top) {
        return repository.findByAgeBetween(below, top);
    }

    @GetMapping("/query/age/{below}/{top}")
    public Flux<Student> queryByAge(@PathVariable("below") int below, @PathVariable("top") int top) {
        return repository.queryByAge(below, top);
    }

    @GetMapping(value = "/sse/query/age/{below}/{top}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> queryByAgeSSE(@PathVariable("below") int below, @PathVariable("top") int top) {
        return repository.queryByAge(below, top);
    }
}
