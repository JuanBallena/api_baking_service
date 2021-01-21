package com.jp.baking.service.dto.user;

import com.jp.baking.service.dto.activity.ActivityDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AuthenticatedUserDto {

	private String username;
	private ActivityDto currentActivity;
}
