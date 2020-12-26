package com.jp.baking.service.controller;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jp.baking.service.dto.sms.CreateSmsDto;
import com.jp.baking.service.error.ErrorMessageCreator;
import com.jp.baking.service.interf.Dto;
import com.jp.baking.service.manager.SmsManager;
import com.jp.baking.service.response.ServiceResponse;


@RestController
public class SmsController {
	
	private static final String SMS = "SMS";
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();
	
	@Autowired
	private SmsManager smsManager;

	@PostMapping("/sms/send")
	public ServiceResponse sendSms(@RequestBody CreateSmsDto dto) {
		
		ServiceResponse serviceResponse = new ServiceResponse();

		try {
			serviceResponse.setType(SMS);
			
			Set<ConstraintViolation<Dto>> violations = validator.validate(dto);
			
			if (violations.isEmpty()) {
				smsManager.sendSms(dto);
				serviceResponse.setData("Message sent");
				serviceResponse.addResponseOk();
				return serviceResponse;
			}
			
			serviceResponse.addResponseBadRequest();
			serviceResponse.setErrorsMessage(ErrorMessageCreator.create(violations));
			return serviceResponse;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return serviceResponse;
	}
}