package com.jp.baking.service.filter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class FilterSpecification<T> implements Specification<T> {

    private static final long serialVersionUID = 1900581010229669687L;

    private List<FilterParameter> filterParameters;

    public FilterSpecification() {
        this.filterParameters = new ArrayList<FilterParameter>();
    }

    public void addFilters(List<FilterParameter> filterParameters) {
    	
    	for (FilterParameter requestParameter : filterParameters) {
    		this.filterParameters.add(requestParameter);
		}
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<>();

        for (FilterParameter requestParameter : this.filterParameters) {   
        	predicates.add(builder.equal(root.get(requestParameter.getProperty()), requestParameter.getValue()));
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}


