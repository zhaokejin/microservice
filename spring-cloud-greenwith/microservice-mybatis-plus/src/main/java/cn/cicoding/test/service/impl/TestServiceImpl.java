package cn.cicoding.test.service.impl;

import cn.cicoding.test.service.TestService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.cicoding.test.mapper.TestMapper;
import cn.cicoding.test.vo.User;
import cn.cicoding.utils.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    TestMapper testMapper;
    @Override
    public int insert(User user) {
        return testMapper.insert(user);
    }

    @Override
    public int add(User user) {
        return testMapper.add(user);
    }

    @Override
    public void deleteUserById(Integer userId) {
        testMapper.deleteById(userId);
    }

    @Override
    public List<User> selectUsers() {
        return testMapper.selectList(null);
    }

    @Override
    public User findById(Integer userId) {
        return testMapper.selectById(userId);
    }

    @Override
    public ResultSet selectUsersPages(User user) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("user_name",user.getUserName());
        ResultSet resultSet = new ResultSet();
        try {
            Page<User> page = new Page<User>(user.getPageInfo().getCurrent(), user.getPageInfo().getSize());
            IPage<User> list = this.testMapper.selectPage(page, queryWrapper);
            resultSet.setData(list);
        } catch (Exception e) {
            resultSet.setResultCode(ResultSet.RESULT_CODE_ERROR);
            resultSet.setResultMsg("系统异常,请联系管理员!");
            e.printStackTrace();
        }
        return resultSet;
    }


}
