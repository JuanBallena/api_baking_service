package com.jp.baking.service.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserDto {

	private Long id;
	private String username;
	
}
