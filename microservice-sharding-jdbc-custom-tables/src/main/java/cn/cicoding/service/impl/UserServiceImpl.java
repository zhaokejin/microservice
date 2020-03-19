package cn.cicoding.service.impl;

import cn.cicoding.dao.UserDaoMapper;
import cn.cicoding.model.User;
import cn.cicoding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDaoMapper userDao;
	
	public List<User> list() {
		List<User> list = userDao.list();
		return list;
	}

	public Long add(User user) {
		return userDao.addUser(user);
	}

	@Override
	public User findById(Long id) {
		return userDao.findById(id);
	}

	@Override
	public User findByName(String name) {
		return userDao.findByName(name);
	}

}