package com.jp.baking.service.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FilterParameter {

	private String property;
	private Object value;
}
