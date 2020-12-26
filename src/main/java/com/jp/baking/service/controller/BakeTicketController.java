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

import com.jp.baking.service.dto.bakeTicket.BakeTicketDto;
import com.jp.baking.service.dto.bakeTicket.CreateBakeTicketAndCustomerDto;
import com.jp.baking.service.dto.bakeTicket.CreateBakeTicketDto;
import com.jp.baking.service.dto.bakeTicket.UpdateBakeTicketBakingStatusDto;
import com.jp.baking.service.dto.bakeTicket.UpdateBakeTicketCustomerDto;
import com.jp.baking.service.dto.bakeTicket.UpdateBakeTicketDto;
import com.jp.baking.service.error.ErrorMessageCreator;
import com.jp.baking.service.interf.Dto;
import com.jp.baking.service.manager.BakeTicketManager;
import com.jp.baking.service.requestParameter.BakeTicketRequest;
import com.jp.baking.service.requestParameter.SearchBakeTicketRequest;
import com.jp.baking.service.response.ListResponse;
import com.jp.baking.service.response.ServiceResponse;

@RestController
public class BakeTicketController {
	
	private static final String BAKE_TICKETS = "Bake Tickets";
	private static final String BAKE_TICKET = "Bake Ticket";
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();
	
	@Autowired
	private BakeTicketManager bakeTicketManager;
	
	@GetMapping("/bake-tickets")
	public ServiceResponse getBakeTicketList(BakeTicketRequest request) {
		
		ServiceResponse serviceResponse = new ServiceResponse();
	
		try {
			serviceResponse.setType(BAKE_TICKETS);
			
			ListResponse listResponse = bakeTicketManager.findAll(request);
			
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
	
	@GetMapping("/bake-tickets/search")
	public ServiceResponse searchBakeTicket(SearchBakeTicketRequest request) {
		
		ServiceResponse serviceResponse = new ServiceResponse();
	
		try {
			serviceResponse.setType(BAKE_TICKETS);
			
			ListResponse listResponse = bakeTicketManager.search(request);
			
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

	@GetMapping("/bake-tickets/{idBakeTicket}")
	public ServiceResponse getBakeTicket(@PathVariable("idBakeTicket") Long idBakeTicket) {
		
		ServiceResponse serviceResponse = new ServiceResponse();
		
		try {
			serviceResponse.setType(BAKE_TICKET);
			
			BakeTicketDto bakeTicketDto = bakeTicketManager.findById(idBakeTicket);
			
			if (bakeTicketDto == null) {
				serviceResponse.addResponseNoContent();
				serviceResponse.setData(bakeTicketDto);
				return serviceResponse;
			}
			
			serviceResponse.addResponseOk();
			serviceResponse.setData(bakeTicketDto);
			return serviceResponse;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return serviceResponse;
	}
	
	@PostMapping("/bake-tickets")
	public ServiceResponse saveBakeTicket(@RequestBody CreateBakeTicketDto dto) {
		
		ServiceResponse serviceResponse = new ServiceResponse();

		try {
			serviceResponse.setType(BAKE_TICKET);
			
			Set<ConstraintViolation<Dto>> violations = validator.validate(dto);

			if (violations.isEmpty()) {
				BakeTicketDto bakeTicketDto = bakeTicketManager.save(dto);
				serviceResponse.addResponseCreated();
				serviceResponse.setData(bakeTicketDto);
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
	
	@PostMapping("/bake-tickets/customer")
	public ServiceResponse saveBakeTicketAndCustomer(@RequestBody CreateBakeTicketAndCustomerDto dto) {
		
		ServiceResponse serviceResponse = new ServiceResponse();

		try {
			serviceResponse.setType(BAKE_TICKET);
			
			Set<ConstraintViolation<Dto>> violations = validator.validate(dto);

			if (violations.isEmpty()) {
				BakeTicketDto bakeTicketDto = bakeTicketManager.save(dto);
				serviceResponse.addResponseCreated();
				serviceResponse.setData(bakeTicketDto);
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
	
	@PutMapping("/bake-tickets/{idBakeTicket}")
	public ServiceResponse updateBakeTicket(@PathVariable("idBakeTicket") Long idBakeTicket, @RequestBody UpdateBakeTicketDto dto) {
		
		ServiceResponse serviceResponse = new ServiceResponse();
		
		try {
			serviceResponse.setType(BAKE_TICKET);
			
			Set<ConstraintViolation<Dto>> violations = validator.validate(dto);

			if (violations.isEmpty()) {
				
				BakeTicketDto bakeTicketDto = bakeTicketManager.update(dto);
				
				if (bakeTicketDto == null) {
					serviceResponse.addResponseNoContent();
					serviceResponse.setData(bakeTicketDto);
					return serviceResponse;
				}
				
				serviceResponse.addResponseCreated();
				serviceResponse.setData(bakeTicketDto);
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
	
	@PutMapping("/bake-tickets/{idBakeTicket}/customer")
	public ServiceResponse updateBakeTicketCustomer(@PathVariable("idBakeTicket") Long idBakeTicket, @RequestBody UpdateBakeTicketCustomerDto dto) {
		
		ServiceResponse serviceResponse = new ServiceResponse();
		
		try {
			serviceResponse.setType(BAKE_TICKET);
			
			Set<ConstraintViolation<Dto>> violations = validator.validate(dto);

			if (violations.isEmpty()) {
				
				BakeTicketDto bakeTicketDto = bakeTicketManager.update(dto);
				
				if (bakeTicketDto == null) {
					serviceResponse.addResponseNoContent();
					serviceResponse.setData(bakeTicketDto);
					return serviceResponse;
				}
				
				serviceResponse.addResponseCreated();
				serviceResponse.setData(bakeTicketDto);
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
	
	@PutMapping("/bake-tickets/{idBakeTicket}/baking-status")
	public ServiceResponse updateBakeTicketBakingStatus(@PathVariable("idBakeTicket") Long idBakeTicket, @RequestBody UpdateBakeTicketBakingStatusDto dto) {
		
		ServiceResponse serviceResponse = new ServiceResponse();
		
		try {
			serviceResponse.setType(BAKE_TICKET);
			
			Set<ConstraintViolation<Dto>> violations = validator.validate(dto);

			if (violations.isEmpty()) {
				
				BakeTicketDto bakeTicketDto = bakeTicketManager.update(dto);
				
				if (bakeTicketDto == null) {
					serviceResponse.addResponseNoContent();
					serviceResponse.setData(bakeTicketDto);
					return serviceResponse;
				}
				
				serviceResponse.addResponseCreated();
				serviceResponse.setData(bakeTicketDto);
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
