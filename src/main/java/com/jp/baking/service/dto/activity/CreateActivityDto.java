package com.jp.baking.service.dto.activity;

import java.util.Map;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotEmpty;
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
import com.jp.baking.service.manager.ActivityManager;
import com.jp.baking.service.model.Activity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@GroupSequence({CreateActivityDto.class, SecondValidation.class, ThirdValidation.class, FourthValidation.class})
public class CreateActivityDto implements Dto {

	@NotNull
	@NotEmpty(groups = SecondValidation.class)
	@Size(
		min = 10, max = 50,
		message = MessageDefinition.SIZE_OF_THE_ACTIVITY_DESCRIPTION,
		groups = ThirdValidation.class)
	@UniqueToCreate(
		property = Activity.DESCRIPTION_PROPERTY, 
		manager = ActivityManager.class, 
		message = MessageDefinition.EXISTING_DESCRIPTION, 
		groups = FourthValidation.class
	)
	private String description;
	
	@NotNull
	@NotEmpty(groups = SecondValidation.class)
	private String date;
	
	@SuppressWarnings("unchecked")
	@JsonProperty("activity")
	@JsonIgnoreProperties(ignoreUnknown = true)
	private void getJsonProperties(Map<String, Object> activity) {
		
		Map<String, Object> data = (Map<String, Object>) activity.get("data");
		
		description = (String) data.get("description");
		date        = (String) data.get("date");
	}
}
