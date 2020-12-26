package com.jp.baking.service.dto.setting;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SettingDto {

	private Long id;
	private String name;
	private String description;
	private Long value;
}
