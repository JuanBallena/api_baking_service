package com.jp.baking.service.dto.activity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ActivityDto {

	private Long id;
	private String description;
	private String date;
	private Boolean finished;
}
