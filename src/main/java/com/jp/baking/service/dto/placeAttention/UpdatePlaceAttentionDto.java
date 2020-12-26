package com.jp.baking.service.dto.placeAttention;

import java.util.Map;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jp.baking.service.definition.MessageDefinition;
import com.jp.baking.service.interf.Dto;
import com.jp.baking.service.interf.SecondValidation;
import com.jp.baking.service.interf.ThirdValidation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@GroupSequence({UpdatePlaceAttentionDto.class, SecondValidation.class, ThirdValidation.class})
public class UpdatePlaceAttentionDto implements Dto {
	
	@NotNull
	@Positive(groups = SecondValidation.class)
	private Integer idPlaceAttention;
	
	@NotNull
	@NotBlank(groups = SecondValidation.class)
	@Size(
		min = 1, max = 30,
		message = MessageDefinition.SIZE_OF_THE_PLACE_ATTENTION_NAME,
		groups = ThirdValidation.class)
	private String name;
	
	@NotNull
	@Size(
		max = 10,
		message = MessageDefinition.SIZE_OF_THE_PLACE_ATTENTION_ABBREVIATION,
		groups = SecondValidation.class)
	private String abbreviation;
	
	@SuppressWarnings("unchecked")
	@JsonProperty("placeAttention")
	@JsonIgnoreProperties(ignoreUnknown = true)
	private void getJsonProperties(Map<String, Object> placeAttention) {
		
		Map<String, Object> data = (Map<String, Object>) placeAttention.get("data");

		idPlaceAttention = (Integer) data.get("idPlaceAttention");
		name             = (String)  data.get("name");
		abbreviation     = (String)  data.get("abbreviation");
	}
	
	public Long getIdPlaceAttention() {
		return Long.valueOf(this.idPlaceAttention);
	}
}
