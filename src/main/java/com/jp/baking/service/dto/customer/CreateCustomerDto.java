package com.jp.baking.service.dto.customer;

import java.util.Map;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jp.baking.service.annotation.FixedTextSize;
import com.jp.baking.service.annotation.UniqueToCreate;
import com.jp.baking.service.definition.MessageDefinition;
import com.jp.baking.service.definition.RegexDefinition;
import com.jp.baking.service.interf.Dto;
import com.jp.baking.service.interf.FifthValidation;
import com.jp.baking.service.interf.FourthValidation;
import com.jp.baking.service.interf.SecondValidation;
import com.jp.baking.service.interf.ThirdValidation;
import com.jp.baking.service.manager.CustomerManager;
import com.jp.baking.service.model.Customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@GroupSequence({CreateCustomerDto.class, SecondValidation.class, ThirdValidation.class, FourthValidation.class, FifthValidation.class})
public class CreateCustomerDto implements Dto {

	@NotNull
	@NotBlank(groups = SecondValidation.class)
	@Size(
		min = 2, max = 100,
		message = MessageDefinition.SIZE_OF_THE_CUSTOMER_NAME,
		groups = ThirdValidation.class)
	private String name;
	
	@NotNull
	@NotBlank(groups = SecondValidation.class)
	@FixedTextSize(
		size = 8,
		message = MessageDefinition.SIZE_OF_THE_CUSTOMER_DOCUMENT, 
		groups = ThirdValidation.class)
	@Pattern(
		regexp  = RegexDefinition.ONLY_NUMBERS, 
		message = MessageDefinition.ONLY_NUMBERS, 
		groups  = FourthValidation.class)
	@UniqueToCreate(
		property = Customer.DOCUMENT_PROPERTY, 
		manager  = CustomerManager.class, 
		message  = MessageDefinition.EXISTING_DOCUMENT, 
		groups   = FifthValidation.class)
	private String document;
	
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
	
	@SuppressWarnings("unchecked")
	@JsonProperty("customer")
	@JsonIgnoreProperties(ignoreUnknown = true)
	private void getJsonProperties(Map<String, Object> customer) {
		
		Map<String, Object> data = (Map<String, Object>) customer.get("data");
		
		name     = (String) data.get("name");
		document = (String) data.get("document");
		phone    = (String) data.get("phone");
	}
}
