package net.polarisdigitech.book_store.book_store.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import net.polarisdigitech.book_store.book_store.dto.SignInDto;
import net.polarisdigitech.book_store.book_store.dto.SignUpDto;
import net.polarisdigitech.book_store.book_store.payload.ApiResponse;
import net.polarisdigitech.book_store.book_store.services.AuthService;




@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
		
	@Autowired
	private AuthService authService;
	
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/signin")
	public ApiResponse authenticatUser(@RequestBody SignInDto signInDto){
	
		return authService.authenticate(signInDto);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/signup")
	public ApiResponse createUserAccount(@Valid @RequestBody SignUpDto signUpDto){
		
		return authService.registerUser(signUpDto);
	}
	
	
}
