package com.jp.baking.service.requestParameter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.jp.baking.service.filter.FilterParameter;
import com.jp.baking.service.filter.FilterSpecification;
import com.jp.baking.service.model.Customer;
import com.jp.baking.service.validator.Validator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {

	private Validator validator = new Validator();

	private String name;
	private String sort;
	private String direction;
	private int size;
	private int page;
	
	public CustomerRequest()
	{
		this.name = "";
		this.sort = "";
		this.direction = "";
		this.page = 0;
		this.size = 0;
	}
	
	public Boolean nameParameterHasText() {
		return validator.hasText(this.name);
	}
	
	public Boolean sizeParameterPositiveNumber() {
		return validator.positiveNumber(this.size);
	}
	
	public Boolean sortByParameterHasText() {		
		return validator.hasText(this.sort);
	}
	
	public Boolean directionParameterHasText() {		
		return validator.hasText(this.direction);
	}
	
	public Boolean hasFilterParameters() {
		return this.nameParameterHasText();
	}
	
	public Boolean hasSortParameters() {
		return this.sortByParameterHasText() & this.directionParameterHasText();
	}
	
	public Boolean hasPagingParameters() {
		return this.sizeParameterPositiveNumber();
	}
	
	public Boolean hasFilterAndSortParameters() {
		return this.hasFilterParameters() && this.hasSortParameters();
	}
	
	public Boolean hasFilterAndPagingParameters() {
		return this.hasFilterParameters() && this.hasPagingParameters();
	}
	
	public Boolean hasPagingAndSortParameters() {
		return this.hasPagingParameters() && this.hasSortParameters();
	}
	
	public Boolean hasFilterAndPagingAndSortParameters() {
		return this.hasFilterParameters() && this.hasPagingParameters() && this.hasSortParameters();
	}
	
	public FilterSpecification<Customer> getFilters() {
		
		FilterSpecification<Customer> filterSpecification = new FilterSpecification<Customer>();
		
		List<FilterParameter> filterParameters = new ArrayList<FilterParameter>();
		
		if (this.nameParameterHasText()) 
			filterParameters.add(new FilterParameter(Customer.NAME_PROPERTY, this.name));
		
		filterSpecification.addFilters(filterParameters);
		
		return filterSpecification;
	}
	
	public PageRequest getPaging() {
		return PageRequest.of(this.page, this.size);
	}
	
	public Sort getSort() {
		if (this.direction.equals("asc")) 
			return Sort.by(Sort.Direction.ASC, this.sort);
		if (this.direction.equals("desc")) 
			return Sort.by(Sort.Direction.DESC, this.sort);
		
		return null;
	}
	
	public PageRequest getPagingAndSort() {
		return PageRequest.of(this.page, this.size, getSort());
	}
}
