package com.jp.baking.service.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Report {

	private String description;
	private int quantity;
}
