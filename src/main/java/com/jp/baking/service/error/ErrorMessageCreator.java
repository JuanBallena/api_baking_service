package com.jp.baking.service.error;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.jp.baking.service.interf.Dto;

public class ErrorMessageCreator {

	public static final List<Object> create(Set<ConstraintViolation<Dto>> violations) {
		
		List<Object> errorsMessage = new ArrayList<Object>();
		
		violations.forEach(violation -> {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(violation.getPropertyPath().toString(), violation.getMessage().toString());
			errorsMessage.add(map);
		});
		
		return errorsMessage;
	}
}
