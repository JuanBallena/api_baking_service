package com.jp.baking.service.validator;

import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.base.Strings;

@Component
public class Validator {
	
	public Boolean hasText(String text) {
		return !Strings.isNullOrEmpty(text);
//		return !text.isEmpty() && !text.isBlank();
	}
	
	public Boolean hasData(List<?> list) {
		return list.size() > 0;
	}
	
	public Boolean positiveNumber(Long number) {
		return number > 0;
	}
	
	public Boolean positiveNumber(Integer number) {
		return number > 0;
	}
	
	public Boolean positiveNumber(int number) {
		return number > 0;
	}
	
	public Boolean numberGreatherThanOne(Long number) {
		return number > 1;
	}
	
	public Boolean notPositiveNumber(Long number) {
		return number < 1;
	}
	
	public Boolean positiveNumberOrCero(int number) {
		return number >= 0;
	}
	
	public Boolean notNull(Object obj) {
		return obj != null;
	}
	
	public Boolean isNull(Object obj) {
		return obj == null;
	}
}
