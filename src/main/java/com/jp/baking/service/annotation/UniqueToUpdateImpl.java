package com.jp.baking.service.annotation;

import java.util.HashMap;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.jp.baking.service.ApplicationContextProvider;
import com.jp.baking.service.interf.UniqueDataValidator;

public class UniqueToUpdateImpl implements ConstraintValidator<UniqueToUpdate, HashMap<String, Object>> {

	private UniqueDataValidator uniqueDataValidator;
	private String property;
	
	@Override
    public void initialize(UniqueToUpdate annotation) {

		Class<? extends UniqueDataValidator> clazz = annotation.manager();
		this.uniqueDataValidator = ApplicationContextProvider.getApplicationContext().getBean(clazz);
		this.property = annotation.property();
	}
	
    @Override
    public boolean isValid(HashMap<String, Object> map, ConstraintValidatorContext context) {
    	
    	return this.uniqueDataValidator.uniqueToUpdate(this.property, map.get("value"), Long.valueOf(map.get("id").toString()));
    }
}
