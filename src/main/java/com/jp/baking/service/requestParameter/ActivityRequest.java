package com.jp.baking.service.requestParameter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.jp.baking.service.filter.FilterParameter;
import com.jp.baking.service.filter.FilterSpecification;
import com.jp.baking.service.model.Activity;
import com.jp.baking.service.validator.Validator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityRequest {
	
	private Validator validator = new Validator();

	private String description;
	private String date;
	private Boolean finished;
	private String sort;
	private String direction;
	private int size;
	private int page;
	
	public ActivityRequest()
	{
		this.description = "";
		this.date = "";
		this.finished = null;
		this.sort = "";
		this.direction = "";
		this.page = 0;
		this.size = 0;
	}
	
	public Boolean descriptionParameterHasText() {
		return validator.hasText(this.description);
	}
	
	public Boolean dateParameterHasText() {
		return validator.hasText(this.date);
	}
	
	public Boolean finishedParameterNotNull() {
		return validator.notNull(this.finished);
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
		return this.descriptionParameterHasText() ||
				this.dateParameterHasText() ||
				this.finishedParameterNotNull();
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
	
	public FilterSpecification<Activity> getFilters() {
		
		FilterSpecification<Activity> filterSpecification = new FilterSpecification<Activity>();
		
		List<FilterParameter> filterParameters = new ArrayList<FilterParameter>();
		
		if (this.descriptionParameterHasText()) 
			filterParameters.add(new FilterParameter(Activity.DESCRIPTION_PROPERTY, this.description));
		
		if (this.dateParameterHasText())
			filterParameters.add(new FilterParameter(Activity.DATE_PROPERTY, this.date));
		
		if (this.finishedParameterNotNull())
			filterParameters.add(new FilterParameter(Activity.FINISHED_PROPERTY, this.finished));
		
		filterSpecification.addFilters(filterParameters);
		
		return filterSpecification;
	}
	
	public PageRequest getPaging() {
		return PageRequest.of(this.page, this.size);
	}
	
	public Sort getSort() {
		if (this.direction.equals("asc")) return Sort.by(Sort.Direction.ASC, this.sort);
		if (this.direction.equals("desc")) return Sort.by(Sort.Direction.DESC, this.sort);
		
		return null;
	}
	
	public PageRequest getPagingAndSort() {
		return PageRequest.of(this.page, this.size, getSort());
	}
}
