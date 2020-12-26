package com.jp.baking.service.dto.user;

import java.util.Map;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jp.baking.service.annotation.UniqueToCreate;
import com.jp.baking.service.definition.MessageDefinition;
import com.jp.baking.service.interf.Dto;
import com.jp.baking.service.interf.FourthValidation;
import com.jp.baking.service.interf.SecondValidation;
import com.jp.baking.service.interf.ThirdValidation;
import com.jp.baking.service.manager.UserManager;
import com.jp.baking.service.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@GroupSequence({CreateUserDto.class, SecondValidation.class, ThirdValidation.class, FourthValidation.class})
public class CreateUserDto implements Dto {

	@NotNull
	@NotBlank(groups = SecondValidation.class)
	@Size(
		min = 3, max = 20,
		message = MessageDefinition.SIZE_OF_THE_USER_USERNAME,
		groups = ThirdValidation.class)
	@UniqueToCreate(
		property = User.USERNAME_PROPERTY, 
		manager  = UserManager.class, 
		message  = MessageDefinition.EXISTING_USERNAME, 
		groups   = FourthValidation.class)
	private String username;
	
	@NotNull
	@NotBlank(groups = SecondValidation.class)
	private String password;
	
	@SuppressWarnings("unchecked")
	@JsonProperty("user")
	@JsonIgnoreProperties(ignoreUnknown = true)
	private void getJsonProperties(Map<String, Object> user) {
		
		Map<String, Object> data = (Map<String, Object>) user.get("data");
		
		username = (String) data.get("username");
		password = (String) data.get("password");
	}
}