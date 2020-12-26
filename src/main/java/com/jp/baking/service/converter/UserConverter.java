package com.jp.baking.service.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jp.baking.service.dto.user.UserDto;
import com.jp.baking.service.model.User;

@Component
public class UserConverter {

	public UserDto toUserDto(User user) {
		
		return UserDto.builder()
				.id(user.getIdUser())
				.username(user.getUsername())
				.build();
	}
	
	public List<UserDto> toUserDtoList(List<User> userList) {
		
		List<UserDto> userDtoList = new ArrayList<UserDto>();
		userList.forEach(user -> userDtoList.add(toUserDto(user)) );
		
		return userDtoList;
	}
}
