package com.jp.baking.service.interf;

public interface UniqueDataValidator {

	public boolean uniqueToCreate(String property, Object value);
	public boolean uniqueToUpdate(String property, Object value, Long id);
}
