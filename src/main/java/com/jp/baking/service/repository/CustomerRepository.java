package com.jp.baking.service.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jp.baking.service.model.Customer;

public interface CustomerRepository extends CustomRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

	public Customer findByIdCustomer(Long idCustomer);
	public Customer findByDocument(String document);
}
