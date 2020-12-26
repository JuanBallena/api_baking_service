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

import com.jp.baking.service.dto.customer.CreateCustomerDto;
import com.jp.baking.service.dto.customer.CustomerDto;
import com.jp.baking.service.dto.customer.UpdateCustomerDto;
import com.jp.baking.service.error.ErrorMessageCreator;
import com.jp.baking.service.interf.Dto;
import com.jp.baking.service.manager.CustomerManager;
import com.jp.baking.service.requestParameter.CustomerRequest;
import com.jp.baking.service.response.ListResponse;
import com.jp.baking.service.response.ServiceResponse;

@RestController
public class CustomerController {
	
	private static final String CUSTOMERS = "Customers";
	private static final String CUSTOMER = "Customer";
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();
	
	@Autowired
	private CustomerManager customerManager;
	
	@GetMapping("/customers")
	public ServiceResponse getCustomerFilterDocument(CustomerRequest request) {
		
		ServiceResponse serviceResponse = new ServiceResponse();
		
		try {
			serviceResponse.setType(CUSTOMERS);
			
			ListResponse listResponse = customerManager.findAll(request);
			
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
	
	@GetMapping("/customers/{idCustomer}")
	public ServiceResponse getBakeTicket(@PathVariable("idCustomer") Long idCustomer) {
		
		ServiceResponse serviceResponse = new ServiceResponse();
		
		try {
			serviceResponse.setType(CUSTOMER);
			
			CustomerDto customerDto = customerManager.findById(idCustomer);
			
			if (customerDto == null) {
				serviceResponse.addResponseNoContent();
				serviceResponse.setData(customerDto);
				return serviceResponse;
			}
			
			serviceResponse.addResponseOk();
			serviceResponse.setData(customerDto);
			return serviceResponse;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return serviceResponse;
	}
	
	@PostMapping("/customers")
	public ServiceResponse saveCustomer(@RequestBody CreateCustomerDto dto) {
		
		ServiceResponse serviceResponse = new ServiceResponse();
		
		try {
			serviceResponse.setType(CUSTOMER);
			
			Set<ConstraintViolation<Dto>> violations = validator.validate(dto);

			if (violations.isEmpty()) {
				CustomerDto customerDto = customerManager.save(dto);
				serviceResponse.addResponseCreated();
				serviceResponse.setData(customerDto);
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
	
	@PutMapping("/customers/{idCustomer}")
	public ServiceResponse updateCustomer(@PathVariable("idCustomer") Long idCustomer, @RequestBody UpdateCustomerDto dto) {
		
		ServiceResponse serviceResponse = new ServiceResponse();
		
		try {
			serviceResponse.setType(CUSTOMER);
			
			Set<ConstraintViolation<Dto>> violations = validator.validate(dto);

			if (violations.isEmpty()) {
				CustomerDto customerDto = customerManager.update(dto);
				
				if (customerDto == null) {
					serviceResponse.addResponseNoContent();
					serviceResponse.setData(customerDto);
					return serviceResponse;
				}
				
				serviceResponse.addResponseCreated();
				serviceResponse.setData(customerDto);
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
