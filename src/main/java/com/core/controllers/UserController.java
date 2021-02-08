package com.core.controllers;

import com.core.request.UserAccount;
import com.core.response.ServiceResponse;
import com.core.service.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@PostMapping
	public ServiceResponse createAccount(@NonNull @RequestBody UserAccount userAccount) {
		return new ServiceResponse(userService.createAccount(userAccount), HttpStatus.OK);
	}
	
	@GetMapping("/login")
    public ServiceResponse getUserDetails(
    		@RequestParam(value = "username") String userName, @RequestParam (value = "password") String password) {
		return new ServiceResponse(userService.validateLogin(userName, password), HttpStatus.OK);
    }

    @GetMapping("validateToken")
	public ServiceResponse validateToken(
			@RequestParam ("validateToken") String accessToken) {
		return new ServiceResponse(userService.getUserDetailsByAccessToken(accessToken), HttpStatus.OK);
	}
}
