package com.jp.baking.service.dto.report;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ReportDto {

	private String description;
	private int quantity;
}
