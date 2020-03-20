package cn.cicoding.service;

import cn.cicoding.model.User;
import cn.cicoding.model.UserItem;

import java.util.List;

public interface UserService {

	List<User> list();
	
	Long add(User user);
	
	User findById(Long id);
	
	User findByName(String name);

	Long clean();

	Long addUserItem(UserItem userItem);
}