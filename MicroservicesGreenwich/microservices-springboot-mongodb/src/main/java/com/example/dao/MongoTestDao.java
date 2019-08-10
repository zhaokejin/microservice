package com.example.dao;
 
import com.example.entity.Mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MongoTestDao {
 
 
    @Autowired
    private MongoTemplate mongoTemplate;
 
    /**
     * 创建对象
     */
    @Transactional(rollbackFor = Throwable.class)
    public void saveTest(Mongo test) {
        mongoTemplate.save(test);
        int i = 1/0;
    }
 
    /**
     * 根据用户名查询对象
     * @return
     */
    public Mongo findTestByName(String name) {
        Query query=new Query(Criteria.where("name").is(name));
        Mongo mgt =  mongoTemplate.findOne(query , Mongo.class);
        return mgt;
    }
 
    /**
     * 更新对象
     */
    public void updateTest(Mongo test) {
        Query query=new Query(Criteria.where("id").is(test.getId()));
        Update update= new Update().set("age", test.getAge()).set("name", test.getName());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query,update,Mongo.class);
        //更新查询返回结果集的所有
//         mongoTemplate.updateMulti(query,update,Mongo.class);
    }
 
    /**
     * 删除对象
     * @param id
     */
    public void deleteTestById(Integer id) {
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,Mongo.class);
    }
}