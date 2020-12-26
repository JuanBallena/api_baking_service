package com.jp.baking.service.dto.activity;

import java.util.HashMap;
import java.util.Map;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jp.baking.service.annotation.TrueOrFalse;
import com.jp.baking.service.annotation.UniqueToUpdate;
import com.jp.baking.service.definition.MessageDefinition;
import com.jp.baking.service.interf.Dto;
import com.jp.baking.service.interf.SecondValidation;
import com.jp.baking.service.interf.ThirdValidation;
import com.jp.baking.service.manager.ActivityManager;
import com.jp.baking.service.model.Activity;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@GroupSequence({UpdateActivityDto.class, SecondValidation.class, ThirdValidation.class})
public class UpdateActivityDto implements Dto {

	@NotNull
	@Positive(groups = SecondValidation.class)
	private Integer idActivity;
	
	@NotNull
	@NotEmpty(groups = SecondValidation.class)
	@Size(
		min = 5, max = 50, 
		message = MessageDefinition.SIZE_OF_THE_ACTIVITY_DESCRIPTION,
		groups = ThirdValidation.class)
	private String description;
	
	@NotNull
	@NotEmpty(groups = SecondValidation.class)
	private String date;
	
	@NotNull
	@TrueOrFalse(groups = SecondValidation.class)
	private Boolean finished;
	
	@UniqueToUpdate(
		property = Activity.DESCRIPTION_PROPERTY, 
		manager = ActivityManager.class, 
		message = MessageDefinition.EXISTING_DESCRIPTION, 
		groups = ThirdValidation.class)
	private HashMap<String, Object> getDescripcion() {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", this.idActivity);
		map.put("value", this.description);
		return map;
	}
	
	@SuppressWarnings("unchecked")
	@JsonProperty("activity")
	@JsonIgnoreProperties(ignoreUnknown = true)
	private void getJsonProperties(Map<String, Object> activity) {
		
		Map<String, Object> data = (Map<String, Object>) activity.get("data");
		
		idActivity  = (Integer) data.get("idActivity");
		description = (String)  data.get("description");
		date        = (String)  data.get("date");
		finished    = (Boolean) data.get("finished");
	}
	
	public Long getIdActivity() {
		return Long.valueOf(this.idActivity);
	}
}
