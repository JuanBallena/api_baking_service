package com.jp.baking.service.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jp.baking.service.dto.placeAttention.PlaceAttentionDto;
import com.jp.baking.service.model.PlaceAttention;

@Component
public class PlaceAttentionConverter {

	public PlaceAttentionDto toPlaceAttentionDto(PlaceAttention placeAttention) {
		
		return PlaceAttentionDto.builder()
				.id(placeAttention.getIdPlaceAttention())
				.name(placeAttention.getName())
				.build();
	}
	
	public List<PlaceAttentionDto> toPlaceAttentionDtoList(List<PlaceAttention> placeAttentionList) {
		
		List<PlaceAttentionDto> placeAttentionDTOList = new ArrayList<PlaceAttentionDto>();
		placeAttentionList.forEach(placeAttention -> placeAttentionDTOList.add(toPlaceAttentionDto(placeAttention)));		
		return placeAttentionDTOList;
	}
}
