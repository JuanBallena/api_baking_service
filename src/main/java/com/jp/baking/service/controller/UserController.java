package com.jp.baking.service.controller;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jp.baking.service.definition.ResponseDefinition;
import com.jp.baking.service.dto.user.AuthenticatedUserDto;
import com.jp.baking.service.dto.user.CreateUserDto;
import com.jp.baking.service.dto.user.CredentialsDto;
import com.jp.baking.service.dto.user.UserDto;
import com.jp.baking.service.error.ErrorMessageCreator;
import com.jp.baking.service.interf.Dto;
import com.jp.baking.service.manager.UserManager;
import com.jp.baking.service.response.ServiceResponse;

@RestController
public class UserController {
	
	private static final String LOGIN = "Login";
	private static final String INVALID_CREDENTIALS = "Los datos de autenticación introducidos son incorrectos. Inténtelo de nuevo.";
	private static final String USER = "User";
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();
	
	@Autowired
	private UserManager userManager;
	
	@PostMapping("/users")
	public ServiceResponse saveUser(@RequestBody CreateUserDto dto) {
		
		ServiceResponse serviceResponse = new ServiceResponse();
		
		try {
			serviceResponse.setType(USER);
			
			Set<ConstraintViolation<Dto>> violations = validator.validate(dto);

			if (violations.isEmpty()) {
				
				UserDto userDto = userManager.save(dto);
				serviceResponse.addResponseCreated();
				serviceResponse.setData(userDto);
				return serviceResponse;
			}
			
			serviceResponse.addResponseBadRequest();
			serviceResponse.setErrorsMessage(ErrorMessageCreator.create(violations));
			return serviceResponse;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return serviceResponse;
	}

	@PostMapping("/login")
	public ServiceResponse login(@RequestBody CredentialsDto dto) {
		
		ServiceResponse serviceResponse = new ServiceResponse();
		
		try {
			serviceResponse.setType(LOGIN);
			
			Set<ConstraintViolation<Dto>> violations = validator.validate(dto);

			if (violations.isEmpty()) {
				AuthenticatedUserDto authenticatedUserDto = userManager.login(dto);
				
				if (authenticatedUserDto == null) {
					serviceResponse.setData(authenticatedUserDto);
					serviceResponse.setResponseCode(ResponseDefinition.RESPONSE_CODE_BAD_REQUEST);
					serviceResponse.setResponseMessage(INVALID_CREDENTIALS);
					return serviceResponse;
				}
				
				serviceResponse.setToken(userManager.getToken(authenticatedUserDto.getUsername()));
				serviceResponse.addResponseOk();
				serviceResponse.setData(authenticatedUserDto);
				return serviceResponse;
			}
			
			serviceResponse.addResponseBadRequest();
			serviceResponse.setErrorsMessage(ErrorMessageCreator.create(violations));
			return serviceResponse;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return serviceResponse;
	}
}
