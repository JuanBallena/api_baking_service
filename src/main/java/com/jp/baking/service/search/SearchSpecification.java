package com.jp.baking.service.search;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class SearchSpecification<T> implements Specification<T> {

	private static final long serialVersionUID = 1900581010229669687L;

    private List<ClassProperty> classProperties;
    private List<PropertyOfAnotherClass> propertiesOfAnotherClass;

    public SearchSpecification() {
        this.classProperties = new ArrayList<ClassProperty>();
        this.propertiesOfAnotherClass = new ArrayList<PropertyOfAnotherClass>();
    }

    public void addClassProperties(List<ClassProperty> classProperties) {
    	
    	for (ClassProperty classProperty : classProperties) {
    		this.classProperties.add(classProperty);
		}
    }
    
    public void addPropertiesOfAnotherClass(List<PropertyOfAnotherClass> propertiesOfAnotherClass) {
    	
    	for (PropertyOfAnotherClass propertyOfAnotherClass : propertiesOfAnotherClass) {
    		this.propertiesOfAnotherClass.add(propertyOfAnotherClass);
		}
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<>();

        for (ClassProperty classProperty : this.classProperties) {
        	
        	predicates.add(builder.like(root.get(classProperty.getProperty()), "%" + classProperty.getValue().toString() + "%"));
        }
        
        for (PropertyOfAnotherClass propertyOfAnotherClass : this.propertiesOfAnotherClass) {
        	
        	predicates.add(builder.like(root.get(propertyOfAnotherClass.getNameClass()).get(propertyOfAnotherClass.getProperty()), 
        			"%" + propertyOfAnotherClass.getValue().toString() + "%"));
        }

        return builder.or(predicates.toArray(new Predicate[0]));
    }

}
