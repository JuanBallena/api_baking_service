package com.jp.baking.service.dto.sms;

import java.util.Map;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jp.baking.service.annotation.FixedTextSize;
import com.jp.baking.service.definition.MessageDefinition;
import com.jp.baking.service.definition.RegexDefinition;
import com.jp.baking.service.interf.Dto;
import com.jp.baking.service.interf.FourthValidation;
import com.jp.baking.service.interf.SecondValidation;
import com.jp.baking.service.interf.ThirdValidation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@GroupSequence({CreateSmsDto.class, SecondValidation.class, ThirdValidation.class, FourthValidation.class})
public class CreateSmsDto implements Dto {
	
	@NotNull
	@NotBlank(groups = SecondValidation.class)
	private String countryPrefix;
	
	@NotNull
	@NotBlank(groups = SecondValidation.class)
	@FixedTextSize(
		size = 9, 
		message = MessageDefinition.SIZE_OF_THE_CUSTOMER_PHONE,
		groups = ThirdValidation.class)
	@Pattern(
		regexp  = RegexDefinition.ONLY_NUMBERS, 
		message = MessageDefinition.ONLY_NUMBERS, 
		groups  = FourthValidation.class)
	private String phone;
	
	@NotNull
	@NotBlank(groups = SecondValidation.class)
	private String message;
	
	@SuppressWarnings("unchecked")
	@JsonProperty("sms")
	@JsonIgnoreProperties(ignoreUnknown = true)
	private void getJsonProperties(Map<String, Object> sms) {
		
		Map<String, Object> data = (Map<String, Object>) sms.get("data");
		
		countryPrefix = (String)  data.get("countryPrefix");
		phone         = (String)  data.get("phone");
		message       = (String)  data.get("message");
	}
}
