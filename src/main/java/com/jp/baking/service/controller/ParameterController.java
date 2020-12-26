package com.jp.baking.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jp.baking.service.manager.ParameterManager;
import com.jp.baking.service.response.ListResponse;
import com.jp.baking.service.response.ServiceResponse;

@RestController
@Validated
public class ParameterController {

	private static final String PARAMETERS = "Parameters";
	
	@Autowired
	private ParameterManager parameterManager;
	
	@GetMapping("/parameters")
	public ServiceResponse getParameterList(@RequestParam(value="idParameterType") Long idParameterType) {
		
		ServiceResponse serviceResponse = new ServiceResponse();
		
		try {
			serviceResponse.setType(PARAMETERS);
			
			ListResponse listResponse = parameterManager.findAll(idParameterType);
			
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
}
