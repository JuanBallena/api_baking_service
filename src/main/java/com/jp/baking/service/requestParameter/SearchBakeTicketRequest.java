package com.jp.baking.service.requestParameter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.jp.baking.service.model.BakeTicket;
import com.jp.baking.service.model.Customer;
import com.jp.baking.service.search.ClassProperty;
import com.jp.baking.service.search.PropertyOfAnotherClass;
import com.jp.baking.service.search.SearchBakeTicketSpecification;
import com.jp.baking.service.validator.Validator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchBakeTicketRequest {

	private Validator validator = new Validator();

	private String q;
	private Long idActivity;
	private Long idPlaceAttention;
	private String sort;
	private String direction;
	private int size;
	private int page;
	
	public SearchBakeTicketRequest() {
		this.q = "";
		this.idActivity = 0L;
		this.idPlaceAttention = 0L;
		this.sort = "";
		this.direction = "";
		this.size = 0;
		this.page = 0;
	}
	
	public Boolean queryParameterHasText() {		
		return validator.hasText(this.q);
	}
	
	public Boolean positiveIdActivityParameter() {
		return validator.positiveNumber(this.idActivity);
	}
	
	public Boolean positiveIdPlaceAttentionParameter() {
		return validator.positiveNumber(this.idPlaceAttention);
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
		return this.queryParameterHasText() ||
				this.positiveIdActivityParameter() ||
				this.positiveIdPlaceAttentionParameter();
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
	
	public SearchBakeTicketSpecification<BakeTicket> getFilters() {
		
		SearchBakeTicketSpecification<BakeTicket> specification = new SearchBakeTicketSpecification<BakeTicket>();
		
		List<ClassProperty> classProperties = new ArrayList<ClassProperty>();
		classProperties.add(new ClassProperty(BakeTicket.NUMBER_ATTENTION_PROPERTY, this.q));
		specification.addClassProperties(classProperties);
		
		List<PropertyOfAnotherClass> propertiesOfAnotherClass = new ArrayList<PropertyOfAnotherClass>();
		propertiesOfAnotherClass.add(new PropertyOfAnotherClass(BakeTicket.CUSTOMER_PROPERTY, Customer.NAME_PROPERTY, this.q));
		specification.addPropertiesOfAnotherClass(propertiesOfAnotherClass);
		
		specification.addIdActivity(this.idActivity);
		specification.addIdPlaceAttention(this.idPlaceAttention);
		
		return specification;
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
