package com.jp.baking.service.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.jp.baking.service.ApplicationContextProvider;
import com.jp.baking.service.interf.UniqueDataValidator;

public class UniqueToCreateImpl implements ConstraintValidator<UniqueToCreate, Object> {
	
	private UniqueDataValidator uniqueDataValidator;
	private String property;
	
	@Override
    public void initialize(UniqueToCreate annotation) {
		
		Class<? extends UniqueDataValidator> clazz = annotation.manager();
		this.uniqueDataValidator = ApplicationContextProvider.getApplicationContext().getBean(clazz);
		this.property = annotation.property();
	}
	
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
    	
    	return this.uniqueDataValidator.uniqueToCreate(this.property, value);
    }
}
