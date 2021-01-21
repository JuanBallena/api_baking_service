package com.jp.baking.service.dto.bakeTicket;

import java.util.HashMap;
import java.util.Map;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jp.baking.service.annotation.UniqueNumberAttentionToCreate;
import com.jp.baking.service.definition.MessageDefinition;
import com.jp.baking.service.definition.RegexDefinition;
import com.jp.baking.service.interf.Dto;
import com.jp.baking.service.interf.FifthValidation;
import com.jp.baking.service.interf.FourthValidation;
import com.jp.baking.service.interf.SecondValidation;
import com.jp.baking.service.interf.ThirdValidation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@GroupSequence({CreateBakeTicketAndCustomerDto.class, SecondValidation.class, ThirdValidation.class, FourthValidation.class, FifthValidation.class})
public class CreateBakeTicketAndCustomerDto implements Dto {
	
	@NotNull
	@NotBlank(groups = SecondValidation.class)
	@Size(
		min = 2, max = 100,
		message = MessageDefinition.SIZE_OF_THE_CUSTOMER_NAME,
		groups = ThirdValidation.class)
	private String name;
	
	@NotNull
	@Positive(groups = SecondValidation.class)
	private Integer idActivity;
	
	@NotNull
	@NotBlank(groups = SecondValidation.class)
	@Size(
		min = 1, max = 3,
		message = MessageDefinition.SIZE_OF_THE_BAKE_TICKET_NUMBER_ATTENTION,
		groups = ThirdValidation.class)
	@Pattern(
		regexp = RegexDefinition.ONLY_NUMBERS, 
		message = MessageDefinition.ONLY_NUMBERS, 
		groups = FourthValidation.class)
	private String numberAttention;
	
	@NotNull
	@NotBlank(groups = SecondValidation.class)
	@Pattern(
		regexp = RegexDefinition.ONLY_NUMBERS, 
		message = MessageDefinition.ONLY_NUMBERS, 
		groups = FourthValidation.class)
	private String numberBaked;
	
	@NotNull
	@Positive(groups = SecondValidation.class)
	private Integer idPlaceAttention;
	
	@UniqueNumberAttentionToCreate(groups = FifthValidation.class)
	private HashMap<String, Object> getNumeroAtencion() {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("numberAttention", this.numberAttention);
		map.put("idActivity", this.idActivity);
		map.put("idPlaceAttention", this.idPlaceAttention);
		return map;
	}
	
	@SuppressWarnings("unchecked")
	@JsonProperty("bakeTicket")
	@JsonIgnoreProperties(ignoreUnknown = true)
	private void getJsonProperties(Map<String, Object> bakeTicket) {
		
		Map<String, Object> data = (Map<String, Object>) bakeTicket.get("data");

		name             = (String)  data.get("name");
		idActivity       = (Integer) data.get("idActivity");
		numberAttention  = (String)  data.get("numberAttention");
		numberBaked      = (String) data.get("numberBaked");
		idPlaceAttention = (Integer) data.get("idPlaceAttention");
	}
	
	public Long getIdActivity() {
		return Long.valueOf(this.idActivity);
	}
	
	public Long getIdPlaceAttention() {
		return Long.valueOf(this.idPlaceAttention);
	}
	
	public Integer getNumberBaked() {
		return Integer.parseInt(this.numberBaked);
	}
}
