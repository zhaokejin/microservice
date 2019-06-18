package com.example.service;

import com.example.model.User;
import com.example.model.UserItem;

import java.util.List;

public interface UserService {

	List<User> list();
	
	Long add(User user);
	
	User findById(Long id);
	
	User findByName(String name);

	Long clean();

	Long addUserItem(UserItem userItem);
}