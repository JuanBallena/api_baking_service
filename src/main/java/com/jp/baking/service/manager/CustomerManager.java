package com.jp.baking.service.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.jp.baking.service.converter.CustomerConverter;
import com.jp.baking.service.dto.customer.CreateCustomerDto;
import com.jp.baking.service.dto.customer.CustomerDto;
import com.jp.baking.service.dto.customer.UpdateCustomerDto;
import com.jp.baking.service.interf.UniqueDataValidator;
import com.jp.baking.service.model.Customer;
import com.jp.baking.service.repository.CustomerRepository;
import com.jp.baking.service.requestParameter.CustomerRequest;
import com.jp.baking.service.response.ListResponse;
import com.jp.baking.service.validator.Validator;

@Service
public class CustomerManager implements UniqueDataValidator {
	
	private Validator validator = new Validator();
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerConverter customerConverter;
	
	public ListResponse findAll(CustomerRequest request) {
		
		if (request.hasFilterAndPagingAndSortParameters()) {
			Page<Customer> customerPage = customerRepository.findAll(request.getFilters(), request.getPagingAndSort());
			return this.toListResponse(customerPage);
		}
		
		if (request.hasFilterAndPagingParameters()) {
			Page<Customer> customerPage = customerRepository.findAll(request.getFilters(), request.getPaging());
			return this.toListResponse(customerPage);
		}
		
		if (request.hasFilterAndSortParameters()) {
			List<Customer> customerList = customerRepository.findAll(request.getFilters(), request.getSort());
			return this.toListResponse(customerList);
		}
		
		if (request.hasPagingAndSortParameters()) {
			Page<Customer> customerPage = customerRepository.findAll(request.getPagingAndSort());
			return this.toListResponse(customerPage);
		}
		
		if (request.hasFilterParameters()) {
			List<Customer> customerList = customerRepository.findAll(request.getFilters());
			return this.toListResponse(customerList);
		}

		if (request.hasPagingParameters()) {
			Page<Customer> customerPage = customerRepository.findAll(request.getPaging());
			return this.toListResponse(customerPage);
		}
		
		if (request.hasSortParameters()) {
			List<Customer> customerList = customerRepository.findAll(request.getSort());
			return this.toListResponse(customerList);
		}
		
		List<Customer> customerList = customerRepository.findAll();
		return this.toListResponse(customerList);
	}
	
	private ListResponse toListResponse(Page<Customer> page) {
		
		List<CustomerDto> customerList = customerConverter.toCustomerDtoList(page.getContent());
		return ListResponse.builder()
				.list(customerList)
				.totalPages(page.getTotalPages())
				.build();
	}
	
	private ListResponse toListResponse(List<Customer> list) {
		
		List<CustomerDto> customerList = customerConverter.toCustomerDtoList(list);
		return ListResponse.builder()
				.list(customerList)
				.totalPages(customerList.size() == 0 ? 0 : 1)
				.build();
	}
	
	public CustomerDto findById(Long idCustomer) {
		
		Customer customer = customerRepository.findByIdCustomer(idCustomer);
		
		return customerConverter.toCustomerDto(customer);
	}
	
	public CustomerDto save(CreateCustomerDto dto) {
		
		Customer customer = new Customer();
		customer.setName(dto.getName());
		customer.setDocument(dto.getDocument());
		customer.setPhone(dto.getPhone());
		customerRepository.save(customer);
		customerRepository.refresh(customer);
		
		return customerConverter.toCustomerDto(customer);
	}
	
	public CustomerDto update(UpdateCustomerDto dto) {		
		
		if (validator.notPositiveNumber(dto.getIdCustomer())) return null;
		
		Customer customer = customerRepository.findByIdCustomer(dto.getIdCustomer());
		
		if (validator.isNull(customer)) return null;
		
		customer.setName(dto.getName());
		customer.setDocument(dto.getDocument());
		customer.setPhone(dto.getPhone());
		customerRepository.save(customer);
		customerRepository.refresh(customer);
		
		return customerConverter.toCustomerDto(customer); 
	}
	
	private Customer findCustomer(String property, Object value) {
		
		switch (property) {
			case Customer.DOCUMENT_PROPERTY: return customerRepository.findByDocument(value.toString());
			default: 						 return null;
		}
	}

	@Override
	public boolean uniqueToCreate(String property, Object value) {
		
		Customer customer = this.findCustomer(property, value);
		return validator.isNull(customer);
	}

	@Override
	public boolean uniqueToUpdate(String property, Object value, Long id) {
		
		Customer customer = this.findCustomer(property, value);
		
		if (validator.notNull(customer)) return customer.getIdCustomer().equals(id);
		
		return true;
	}
}
