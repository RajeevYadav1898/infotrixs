package com.task.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.demo.rest.model.LoginModel;
import com.task.demo.rest.model.User;
import com.task.demo.service.UserService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<String> login(@RequestBody LoginModel model) {
		User user = userService.login(model);
		if(user == null) {
			return ResponseEntity.status(404).body("User not found, login failed.");
		}
		return ResponseEntity.ok("Successfully logged in.");
	}

}
