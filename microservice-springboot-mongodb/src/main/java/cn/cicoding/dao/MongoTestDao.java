package cn.cicoding.dao;
 
import cn.cicoding.entity.User;
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
    public void saveTest(User test) {
        mongoTemplate.save(test);
    }
 
    /**
     * 根据用户名查询对象
     * @return
     */
    public User findTestByName(String name) {
        Query query=new Query(Criteria.where("name").is(name));
        User mgt =  mongoTemplate.findOne(query , User.class);
        return mgt;
    }
 
    /**
     * 更新对象
     */
    public void updateTest(User user) {
        Query query=new Query(Criteria.where("userId").is(user.getUserId()));
        Update update= new Update().set("password", user.getPassword()).set("userName", user.getUserName());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query,update,User.class);
        //更新查询返回结果集的所有
//         mongoTemplate.updateMulti(query,update,Mongo.class);
    }
 
    /**
     * 删除对象
     * @param id
     */
    public void deleteTestById(Integer id) {
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,User.class);
    }
}