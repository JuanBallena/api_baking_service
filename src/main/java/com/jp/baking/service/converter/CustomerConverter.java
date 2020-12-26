package com.jp.baking.service.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jp.baking.service.dto.customer.CustomerDto;
import com.jp.baking.service.model.Customer;

@Component
public class CustomerConverter {
		
	public CustomerDto toCustomerDto(Customer customer) {
		
		return CustomerDto.builder()
				.id(customer.getIdCustomer())
				.name(customer.getName())
				.document(customer.getDocument())
				.phone(customer.getPhone())
				.build();
	}
	
	public List<CustomerDto> toCustomerDtoList(List<Customer> customerList) {
		
		List<CustomerDto> customerDtoList = new ArrayList<CustomerDto>();
		customerList.forEach(customer -> customerDtoList.add(toCustomerDto(customer)));		
		return customerDtoList;
	}
}
