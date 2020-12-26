package com.jp.baking.service.search;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.jp.baking.service.model.BakeTicket;

public class SearchBakeTicketSpecification<T> implements Specification<T> {

	private static final long serialVersionUID = 1900581010229669687L;

    private List<ClassProperty> classProperties;
    private List<PropertyOfAnotherClass> propertiesOfAnotherClass;
    private Long idActivity;
    private Long idPlaceAttention;

    public SearchBakeTicketSpecification() {
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
    
    public void addIdActivity(Long idCurrentActivity) {
    	this.idActivity = idCurrentActivity;
    }
    
    public void addIdPlaceAttention(Long idPlaceAttention) {
    	this.idPlaceAttention = idPlaceAttention;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<>();
        
        Predicate activityEqual = builder.equal(root.get(BakeTicket.ACTIVITY_PROPERTY), this.idActivity);
        Predicate placeAttentionEqual = builder.equal(root.get(BakeTicket.PLACE_ATTENTION_PROPERTY), this.idPlaceAttention);
        
        if (this.idActivity > 0) {
        	
	        for (ClassProperty classProperty : this.classProperties) {
	        	
	        	Predicate propertyLike = builder.like(root.get(classProperty.getProperty()), "%" + classProperty.getValue() + "%");
	        	Predicate predicateFinal = builder.and(activityEqual, placeAttentionEqual, propertyLike);
	        	predicates.add(predicateFinal);
	        }
	        
	        for (PropertyOfAnotherClass propertyOfAnotherClass : this.propertiesOfAnotherClass) {
	        	
	        	Predicate propertyLike = builder.like(
	        			root.get(propertyOfAnotherClass.getNameClass()).get(propertyOfAnotherClass.getProperty()),
	        			"%" + propertyOfAnotherClass.getValue() + "%");
	
	        	Predicate predicateFinal = builder.and(activityEqual, placeAttentionEqual, propertyLike);
	        	predicates.add(predicateFinal);
	        }
	        
	        return builder.or(predicates.toArray(new Predicate[0]));
        }
        
        for (ClassProperty classProperty : this.classProperties) {
        	
        	Predicate propertyLike = builder.like(root.get(classProperty.getProperty()), "%" + classProperty.getValue() + "%");
        	Predicate predicateFinal = builder.and(placeAttentionEqual, propertyLike);
        	predicates.add(predicateFinal);
        }
        
        for (PropertyOfAnotherClass propertyOfAnotherClass : this.propertiesOfAnotherClass) {
        	
        	Predicate propertyLike = builder.like(
        			root.get(propertyOfAnotherClass.getNameClass()).get(propertyOfAnotherClass.getProperty()),
        			"%" + propertyOfAnotherClass.getValue() + "%");

        	Predicate predicateFinal = builder.and(placeAttentionEqual, propertyLike);
        	predicates.add(predicateFinal);
        }

        return builder.or(predicates.toArray(new Predicate[0]));
    }
}
