package com.task.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.demo.rest.model.User;
import com.task.demo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<User> create(@RequestBody User user) {
		var savedUser = userService.createOrUpdateUser(user);
		return ResponseEntity.ok(savedUser);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<User> update(@RequestBody User user, @PathVariable("userId") Integer userId) {
		user.setUserId(userId);
		var savedUser = userService.createOrUpdateUser(user);
		return ResponseEntity.ok(savedUser);
	}


}
