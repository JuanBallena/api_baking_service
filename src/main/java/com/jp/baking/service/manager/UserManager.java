package com.jp.baking.service.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.baking.service.auth.Security;
import com.jp.baking.service.converter.UserConverter;
import com.jp.baking.service.dto.user.AuthenticatedUserDto;
import com.jp.baking.service.dto.user.CreateUserDto;
import com.jp.baking.service.dto.user.CredentialsDto;
import com.jp.baking.service.dto.user.UserDto;
import com.jp.baking.service.interf.UniqueDataValidator;
import com.jp.baking.service.model.User;
import com.jp.baking.service.repository.UserRepository;
import com.jp.baking.service.validator.Validator;

@Service
public class UserManager implements UniqueDataValidator {
	
	private Validator validator = new Validator();

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserConverter userConverter;
	
	@Autowired
	private SettingManager settingManager;
	
	@Autowired
	private ActivityManager activityManager;
	
	
	public UserDto save(CreateUserDto dto) {
				
		User user = new User();
		user.setUsername(dto.getUsername());
		user.setPassword(Security.toMd5(dto.getPassword()));
		userRepository.save(user);
		userRepository.refresh(user);
		
		return userConverter.toUserDto(user); 
	}
	
	
	public AuthenticatedUserDto login(CredentialsDto dto) {
		
		String password = Security.toMd5(dto.getPassword());
		User user = userRepository.findByUsernameAndPassword(dto.getUsername(), password);
		
		if (validator.isNull(user)) return null;
		
		return AuthenticatedUserDto.builder()
				.username(user.getUsername())
				.currentActivity(activityManager.findById(settingManager.currentActivityValue()))
				.build();
	}
	
	public String getToken(String username) {
		
		return Security.getJWTToken(username);
	}
	
	private User findUser(String property, Object value) {
		
		switch (property) {
			case User.USERNAME_PROPERTY: return userRepository.findByUsername(value.toString());
			default:                     return null;
		}
	}

	@Override
	public boolean uniqueToCreate(String property, Object value) {
		
		User user = findUser(property, value);
		return validator.isNull(user);
	}

	@Override
	public boolean uniqueToUpdate(String property, Object value, Long id) {
		
		User user = findUser(property, value);
		
		if (validator.notNull(user)) return user.getIdUser().equals(id);
		
		return true;
	}
}
