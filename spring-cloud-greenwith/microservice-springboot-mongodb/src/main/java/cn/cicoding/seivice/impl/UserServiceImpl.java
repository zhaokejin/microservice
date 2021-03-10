package cn.cicoding.seivice.impl;

import cn.cicoding.dao.MongoTestDao;
import cn.cicoding.dao.UserDao;
import cn.cicoding.entity.User;
import cn.cicoding.seivice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MongoTestDao mtdao;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveMysqlMongoDb(User user) {

        userDao.insert(user);
        mtdao.saveTest(user);
        if(null!=user){
            throw new RuntimeException("异常失败");
        }

        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveMysql(User user) {
        userDao.insert(user);
        int i = 1/0;
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveMongoDb(User user) {
        mtdao.saveTest(user);
        int i = 1/0;
        return 0;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(User record) {
        mtdao.saveTest(record);
        userDao.insert(record);

        int i = 1/0;

        return 0;
    }

    @Override
    public void deleteUserById(Integer userId) {
        userDao.deleteUserById(userId);
    }

    @Override
    public void updateUser(User userDomain) {
        userDao.updateUser(userDomain);
    }

    @Override
    public List<User> selectUsers() {
        return userDao.selectUsers();
    }

    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }

}
