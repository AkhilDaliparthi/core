package com.core.controllers;

import com.core.dao.UserDetailsDAO;
import com.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
	
	@Autowired
	public UserDetailsDAO userDetailsDAO;

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/hello")
	public String hello() {
		String hello= "Hello Spring Boot";
		return hello;
	}
	
	@GetMapping("/getUsersInfo")
    public List<User> getUserDetails(
    		@RequestParam(value = "user_name") String userName, @RequestParam (value = "first_name") String firstName) {
		List<User> users = new ArrayList<>();
        User user = User.builder().username(userName).firstName(firstName).build();
        users.add(user);
        return users;
    }
}
