package com.jp.baking.service.dto.user;

import java.util.Map;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jp.baking.service.interf.Dto;
import com.jp.baking.service.interf.SecondValidation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@GroupSequence({CredentialsDto.class, SecondValidation.class})
public class CredentialsDto implements Dto {

	@NotNull
	@NotBlank(groups = SecondValidation.class)
	private String username;
	
	@NotNull
	@NotBlank(groups = SecondValidation.class)
	private String password;
	
	@SuppressWarnings("unchecked")
	@JsonProperty("credentials")
	@JsonIgnoreProperties(ignoreUnknown = true)
	private void getJsonProperties(Map<String, Object> credentials) {
		
		Map<String, Object> data = (Map<String, Object>) credentials.get("data");
		
		username = (String) data.get("username");
		password = (String) data.get("password");
	}
}
