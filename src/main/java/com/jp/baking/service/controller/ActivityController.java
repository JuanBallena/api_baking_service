package com.jp.baking.service.controller;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jp.baking.service.dto.activity.ActivityDto;
import com.jp.baking.service.dto.activity.CreateActivityDto;
import com.jp.baking.service.dto.activity.UpdateActivityDto;
import com.jp.baking.service.error.ErrorMessageCreator;
import com.jp.baking.service.interf.Dto;
import com.jp.baking.service.manager.ActivityManager;
import com.jp.baking.service.requestParameter.ActivityRequest;
import com.jp.baking.service.response.ListResponse;
import com.jp.baking.service.response.ServiceResponse;


@RestController
public class ActivityController {
	
	private static final String ACTIVITIES = "Activities";
	private static final String ACTIVITY = "Activity";
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();
	
	@Autowired
	private ActivityManager activityManager;

	@GetMapping("/activities")
	public ServiceResponse getActivitytList(ActivityRequest request) {
		
		ServiceResponse serviceResponse = new ServiceResponse();
		
		try {
			serviceResponse.setType(ACTIVITIES);
			
			ListResponse listResponse = activityManager.findAll(request);
			
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
	
	@PostMapping("/activities")
	public ServiceResponse saveActivity(@RequestBody CreateActivityDto dto) {
		
		ServiceResponse serviceResponse = new ServiceResponse();
		
		try {
			serviceResponse.setType(ACTIVITY);
			
			Set<ConstraintViolation<Dto>> violations = validator.validate(dto);

			if (violations.isEmpty()) {
				ActivityDto activityDto = activityManager.save(dto);
				serviceResponse.addResponseCreated();
				serviceResponse.setData(activityDto);
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
	
	@PutMapping("/activities/{idActivity}")
	public ServiceResponse updateBakeTicket(@PathVariable("idActivity") Long idActivity, @RequestBody UpdateActivityDto dto) {
		
		ServiceResponse serviceResponse = new ServiceResponse();
		
		try {
			serviceResponse.setType(ACTIVITY);
			
			Set<ConstraintViolation<Dto>> violations = validator.validate(dto);

			if (violations.isEmpty()) {
				
				ActivityDto activityDto = activityManager.update(dto);
				
				if (activityDto == null) {
					serviceResponse.addResponseNoContent();
					serviceResponse.setData(activityDto);
					return serviceResponse;
				}
				
				serviceResponse.addResponseCreated();
				serviceResponse.setData(activityDto);
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
