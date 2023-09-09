package com.task.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.demo.entity.UserEntity;
import com.task.demo.repository.UserRepository;
import com.task.demo.rest.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public User createOrUpdateUser(User user) {
		UserEntity entity = new UserEntity();
		entity.setUsername(user.getUsername());
		entity.setPassword(user.getPassword());
		entity.setEmail(user.getEmail());
		entity.setPhoneNo(user.getPhoneNo());
		entity.setAddress(user.getAddress());
		entity.setZipcode(user.getZipcode());
		entity.setUserId(user.getUserId());
		
		UserEntity savedEntity = userRepository.save(entity);
		user.setUserId(savedEntity.getUserId());
		
		return user;
	}
}
