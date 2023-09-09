package com.task.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.task.demo.entity.UserEntity;
import com.task.demo.repository.UserRepository;
import com.task.demo.rest.model.LoginModel;
import com.task.demo.rest.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public User createOrUpdateUser(User user) {
		
		if(user.getUsername() == null) {
			throw new RuntimeException("Invalid username.");
		}
		
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
	
	public User getUser(Integer userId) {
		Optional<UserEntity> optUser = userRepository.findById(userId);
		if(optUser.isEmpty()) {
			return null;
		}
		return optUser.map(entity -> {
			User user = new User();
			user.setUsername(entity.getUsername());
			user.setPassword(entity.getPassword());
			user.setEmail(entity.getEmail());
			user.setPhoneNo(entity.getPhoneNo());
			user.setAddress(entity.getAddress());
			user.setZipcode(entity.getZipcode());
			user.setUserId(entity.getUserId());
			return user;
		}).get();
		
		
	} 
	public void deleteUser(Integer userId) {
		userRepository.deleteById(userId);
		
	}
	
	public User login(LoginModel model) {
		UserEntity entity = userRepository.findByUsernameAndPassword(model.getUsername(), model.getPassword());
		if(entity == null) {
			return null;
		}
		User user = new User();
		user.setUsername(entity.getUsername());
		user.setPassword(entity.getPassword());
		user.setEmail(entity.getEmail());
		user.setPhoneNo(entity.getPhoneNo());
		user.setAddress(entity.getAddress());
		user.setZipcode(entity.getZipcode());
		user.setUserId(entity.getUserId());
		return user;
	}
}
