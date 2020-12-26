package com.jp.baking.service.requestParameter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.jp.baking.service.filter.FilterParameter;
import com.jp.baking.service.filter.FilterSpecification;
import com.jp.baking.service.model.BakeTicket;
import com.jp.baking.service.validator.Validator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BakeTicketRequest {

	private Validator validator = new Validator();
	
	private Long idCustomer;
	private Long idActivity;
	private Long idPlaceAttention;
	private Long idBakingStatus;
	private String numberAttention;
	private String sort;
	private String direction;
	private int size;
	private int page;
	
	public BakeTicketRequest() {
		this.idCustomer = 0L;
		this.idActivity = 0L;
		this.idPlaceAttention = 0L;
		this.idBakingStatus = 0L;
		this.numberAttention = "";
		this.sort = "";
		this.direction = "";
		this.size = 0;
		this.page = 0;
	}
	
	public Boolean positiveIdCustomerParameter() {
		return validator.positiveNumber(this.idCustomer);
	}
	
	public Boolean positiveIdActivityParameter() {
		return validator.positiveNumber(this.idActivity);
	}
	
	public Boolean positiveIdPlaceAttentionParameter() {
		return validator.positiveNumber(this.idPlaceAttention);
	}
	
	public Boolean positiveIdBakingStatusParameter() {
		return validator.positiveNumber(this.idBakingStatus);
	}
	
	public Boolean numberAttentionParameterHasText() {
		return validator.hasText(this.numberAttention);
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
		return this.positiveIdCustomerParameter() ||
				this.positiveIdActivityParameter() ||
				this.positiveIdPlaceAttentionParameter() ||
				this.positiveIdBakingStatusParameter() ||
				this.numberAttentionParameterHasText();
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
	
	public FilterSpecification<BakeTicket> getFilters() {
		
		FilterSpecification<BakeTicket> filterSpecification = new FilterSpecification<BakeTicket>();
		
		List<FilterParameter> filterParameters = new ArrayList<FilterParameter>();
		
		if (this.positiveIdCustomerParameter()) 
			filterParameters.add(new FilterParameter(BakeTicket.CUSTOMER_PROPERTY, this.idCustomer));
		
		if (this.positiveIdActivityParameter())
			filterParameters.add(new FilterParameter(BakeTicket.ACTIVITY_PROPERTY, this.idActivity));
		
		if (this.positiveIdPlaceAttentionParameter())
			filterParameters.add(new FilterParameter(BakeTicket.PLACE_ATTENTION_PROPERTY, this.idPlaceAttention));
		
		if (this.numberAttentionParameterHasText())
			filterParameters.add(new FilterParameter(BakeTicket.NUMBER_ATTENTION_PROPERTY, this.numberAttention));
		
		if (this.positiveIdBakingStatusParameter())
			filterParameters.add(new FilterParameter(BakeTicket.BAKING_STATUS_PROPERTY, this.idBakingStatus));
		
		
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
