package com.jp.baking.service.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Preference {

	private String name;
	private Object data;
}
