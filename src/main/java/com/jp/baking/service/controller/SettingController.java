package com.jp.baking.service.controller;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jp.baking.service.dto.setting.SettingDto;
import com.jp.baking.service.dto.setting.UpdateSettingValueDto;
import com.jp.baking.service.error.ErrorMessageCreator;
import com.jp.baking.service.interf.Dto;
import com.jp.baking.service.manager.SettingManager;
import com.jp.baking.service.response.ListResponse;
import com.jp.baking.service.response.ServiceResponse;

@RestController
public class SettingController {
	
	private static final String SETTINGS = "Settings";
	private static final String SETTING = "Setting";
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();

	@Autowired
	private SettingManager settingManager;
	
	@GetMapping("/settings")
	public ServiceResponse getSettingList() {
		ServiceResponse serviceResponse = new ServiceResponse();

		try {
			serviceResponse.setType(SETTINGS);
			
			ListResponse listResponse = settingManager.findAll();
			
			if (listResponse.hasData()) {
				serviceResponse.addResponseOk();
				serviceResponse.setData(listResponse.getList());
				serviceResponse.setPages(listResponse.getTotalPages());
				return serviceResponse;
			}
			
			serviceResponse.setData(listResponse.getList());
			serviceResponse.addResponseNoContent();
			return serviceResponse;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return serviceResponse;
	}
	
	@PutMapping("/settings/{idSetting}/value")
	public ServiceResponse updateSetting(@RequestBody UpdateSettingValueDto dto,
		@PathVariable("idSetting") Long idSetting) {
		
		ServiceResponse serviceResponse = new ServiceResponse();

		try {
			serviceResponse.setType(SETTING);
			
			Set<ConstraintViolation<Dto>> violations = validator.validate(dto);

			if (violations.isEmpty()) {
				SettingDto settingDto = settingManager.update(dto);

				if (settingDto == null) {
					serviceResponse.addResponseNoContent();
					serviceResponse.setData(settingDto);
					return serviceResponse;
				}
				
				serviceResponse.addResponseCreated();
				serviceResponse.setData(settingDto);
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
