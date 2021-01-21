package com.jp.baking.service.dto.customer;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CustomerDto {

	private Long id;
	private String name;
}
