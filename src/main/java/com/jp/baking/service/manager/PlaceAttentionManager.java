package com.jp.baking.service.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.baking.service.converter.PlaceAttentionConverter;
import com.jp.baking.service.dto.placeAttention.PlaceAttentionDto;
import com.jp.baking.service.dto.placeAttention.UpdatePlaceAttentionDto;
import com.jp.baking.service.model.PlaceAttention;
import com.jp.baking.service.repository.PlaceAttentionRepository;
import com.jp.baking.service.response.ListResponse;
import com.jp.baking.service.validator.Validator;

@Service
public class PlaceAttentionManager {

	private Validator validator = new Validator();
	
	@Autowired
	private PlaceAttentionRepository placeAttentionRepository;
	
	@Autowired
	private PlaceAttentionConverter placeAttentionConverter;
	
	public ListResponse findAll() {
		
		List<PlaceAttention> placeAttentionList = placeAttentionRepository.findAll();
		return toListResponse(placeAttentionList);
	}
	
	public ListResponse toListResponse(List<PlaceAttention> placeAttentionList) {
		
		List<PlaceAttentionDto> placeAttentionDtoList = placeAttentionConverter.toPlaceAttentionDtoList(placeAttentionList);
		return ListResponse.builder()
				.list(placeAttentionDtoList)
				.totalPages(placeAttentionDtoList.size() == 0 ? 0 : 1)
				.build();
	}
	
	public PlaceAttentionDto update(UpdatePlaceAttentionDto dto) {		
		
		if (validator.notPositiveNumber(dto.getIdPlaceAttention())) return null;
		
		PlaceAttention placeAttention = placeAttentionRepository.findByIdPlaceAttention(dto.getIdPlaceAttention());
		
		if (validator.isNull(placeAttention)) return null;
		
		placeAttention.setName(dto.getName());
		placeAttentionRepository.save(placeAttention);
		placeAttentionRepository.refresh(placeAttention);
		
		return placeAttentionConverter.toPlaceAttentionDto(placeAttention); 
	}
}
