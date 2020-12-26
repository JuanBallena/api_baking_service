package com.jp.baking.service.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.baking.service.converter.ParameterConverter;
import com.jp.baking.service.dto.parameter.ParameterDto;
import com.jp.baking.service.model.Parameter;
import com.jp.baking.service.model.ParameterType;
import com.jp.baking.service.repository.ParameterRepository;
import com.jp.baking.service.response.ListResponse;

@Service
public class ParameterManager {

	@Autowired
	private ParameterRepository parameterRepository;
	
	@Autowired
	private ParameterConverter parameterConverter;
	
	public ListResponse findAll(Long idParameterType) {
		
		ParameterType parameterType = ParameterType.builder()
				.idParameterType(idParameterType)
				.build();
		
		List<Parameter> parameterList = parameterRepository.findByParameterType(parameterType);
		return this.toListResponse(parameterList);
	}
	
	private ListResponse toListResponse(List<Parameter> list) {
		
		List<ParameterDto> customerList = parameterConverter.toParameterDtoList(list);
		return ListResponse.builder()
				.list(customerList)
				.totalPages(customerList.size() == 0 ? 0 : 1)
				.build();
	}
}
