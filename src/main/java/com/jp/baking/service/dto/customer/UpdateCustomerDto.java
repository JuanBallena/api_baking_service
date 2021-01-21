package com.jp.baking.service.dto.customer;

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
import com.jp.baking.service.interf.FifthValidation;
import com.jp.baking.service.interf.FourthValidation;
import com.jp.baking.service.interf.SecondValidation;
import com.jp.baking.service.interf.ThirdValidation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@GroupSequence({UpdateCustomerDto.class, SecondValidation.class, ThirdValidation.class, FourthValidation.class, FifthValidation.class})
public class UpdateCustomerDto implements Dto {

	@NotNull
	@Positive(groups = SecondValidation.class)
	private Integer idCustomer;
	
	@NotNull
	@NotBlank(groups = SecondValidation.class)
	@Size(
		min = 2, max = 100,
		message = MessageDefinition.SIZE_OF_THE_CUSTOMER_NAME,
		groups = ThirdValidation.class)
	private String name;
	
	@SuppressWarnings("unchecked")
	@JsonProperty("customer")
	@JsonIgnoreProperties(ignoreUnknown = true)
	private void getJsonProperties(Map<String, Object> customer) {
		
		Map<String, Object> data = (Map<String, Object>) customer.get("data");
		
		idCustomer = (Integer) data.get("idCustomer");
		name       = (String)  data.get("name");
	}
	
	public Long getIdCustomer() {
		return Long.valueOf(this.idCustomer);
	}
}
