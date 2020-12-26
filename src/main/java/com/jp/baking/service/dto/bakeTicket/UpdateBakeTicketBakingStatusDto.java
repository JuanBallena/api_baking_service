package com.jp.baking.service.dto.bakeTicket;

import java.util.Map;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jp.baking.service.interf.Dto;
import com.jp.baking.service.interf.SecondValidation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@GroupSequence({UpdateBakeTicketBakingStatusDto.class, SecondValidation.class})
public class UpdateBakeTicketBakingStatusDto implements Dto {

	@NotNull
	@Positive(groups = SecondValidation.class)
	private Integer idBakeTicket;
	
	@NotNull
	@Positive(groups = SecondValidation.class)
	private Integer idBakingStatus;
	
	@SuppressWarnings("unchecked")
	@JsonProperty("bakeTicket")
	@JsonIgnoreProperties(ignoreUnknown = true)
	private void getJsonProperties(Map<String, Object> bakeTicket) {
		
		Map<String, Object> data = (Map<String, Object>) bakeTicket.get("data");
		
		idBakeTicket   = (Integer) data.get("idBakeTicket");
		idBakingStatus = (Integer) data.get("idBakingStatus");
	}
	
	public Long getIdBakeTicket() {
		return Long.valueOf(this.idBakeTicket);
	}
	
	public Long getIdBakingStatus() {
		return Long.valueOf(this.idBakingStatus);
	}
}
