package com.jp.baking.service.annotation;

import java.util.HashMap;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.jp.baking.service.ApplicationContextProvider;
import com.jp.baking.service.manager.BakeTicketManager;

public class UniqueNumberAttentionToCreateImpl implements ConstraintValidator<UniqueNumberAttentionToCreate, HashMap<String, Object>> {
	
	private BakeTicketManager bakeTicketManager;
	
	@Override
    public void initialize(UniqueNumberAttentionToCreate annotation) {

		this.bakeTicketManager = ApplicationContextProvider.getApplicationContext().getBean(BakeTicketManager.class);
	}
	
	@Override
	public boolean isValid(HashMap<String, Object> map, ConstraintValidatorContext context) {

		return this.bakeTicketManager.attentionNumberNotExist(map.get("numberAttention").toString(), 
				Long.valueOf(map.get("idActivity").toString()), 
				Long.valueOf(map.get("idPlaceAttention").toString()));
	}

}
