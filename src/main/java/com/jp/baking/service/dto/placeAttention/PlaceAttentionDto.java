package com.jp.baking.service.dto.placeAttention;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PlaceAttentionDto {

	private Long id;
	private String name;
	private String abbreviation;
}
