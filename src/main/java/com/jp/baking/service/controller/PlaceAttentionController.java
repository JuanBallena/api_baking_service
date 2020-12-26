
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

import com.jp.baking.service.dto.placeAttention.PlaceAttentionDto;
import com.jp.baking.service.dto.placeAttention.UpdatePlaceAttentionDto;
import com.jp.baking.service.error.ErrorMessageCreator;
import com.jp.baking.service.interf.Dto;
import com.jp.baking.service.manager.PlaceAttentionManager;
import com.jp.baking.service.response.ListResponse;
import com.jp.baking.service.response.ServiceResponse;

@RestController
public class PlaceAttentionController {

	private static final String PLACES_ATTENTION = "Places attention";
	private static final String PLACE_ATTENTION = "Place attention";
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();
	
	@Autowired
	private PlaceAttentionManager placeAttentionManager;
	
	@GetMapping("/places-attention")
	public ServiceResponse getPlaceAttentionList() {
		ServiceResponse serviceResponse = new ServiceResponse();

		try {
			serviceResponse.setType(PLACES_ATTENTION);
			
			ListResponse listResponse = placeAttentionManager.findAll();
			
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
	
	@PutMapping("/places-attention/{idPlaceAttention}")
	public ServiceResponse updatePlaceAttention(@RequestBody UpdatePlaceAttentionDto dto,
		@PathVariable("idPlaceAttention") Long idPlaceAttention) {
		
		ServiceResponse serviceResponse = new ServiceResponse();

		try {
			serviceResponse.setType(PLACE_ATTENTION);
			
			Set<ConstraintViolation<Dto>> violations = validator.validate(dto);

			if (violations.isEmpty()) {
				PlaceAttentionDto placeAttentionDto = placeAttentionManager.update(dto);

				if (placeAttentionDto == null) {
					serviceResponse.addResponseNoContent();
					serviceResponse.setData(placeAttentionDto);
					return serviceResponse;
				}
				
				serviceResponse.addResponseCreated();
				serviceResponse.setData(placeAttentionDto);
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
