package com.jp.baking.service.dto.setting;

import java.util.Map;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jp.baking.service.interf.Dto;
import com.jp.baking.service.interf.SecondValidation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@GroupSequence({UpdateSettingValueDto.class, SecondValidation.class})
public class UpdateSettingValueDto implements Dto {
	
	@NotNull
	@Positive(groups = SecondValidation.class)
	private Integer idSetting;
	
	@NotNull
	@PositiveOrZero(groups = SecondValidation.class)
	private Integer value;
	
	@SuppressWarnings("unchecked")
	@JsonProperty("setting")
	@JsonIgnoreProperties(ignoreUnknown = true)
	private void getJsonProperties(Map<String, Object> setting) {
		
		Map<String, Object> data = (Map<String, Object>) setting.get("data");
		
		idSetting = (Integer) data.get("idSetting");
		value     = (Integer) data.get("value");
	}
	
	public Long getIdSetting() {
		return Long.valueOf(this.idSetting);
	}
	
	public Long getValue() {
		return Long.valueOf(this.value);
	}
}
