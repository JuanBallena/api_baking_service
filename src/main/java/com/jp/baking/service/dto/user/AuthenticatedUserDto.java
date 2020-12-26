package com.jp.baking.service.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AuthenticatedUserDto {

	private String username;
	private Long currentActivity; 
}
