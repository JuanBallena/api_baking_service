package com.jp.baking.service.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jp.baking.service.dto.parameter.ParameterDto;
import com.jp.baking.service.model.Parameter;


@Component
public class ParameterConverter {

	public ParameterDto toParameterDto(Parameter parameter) {

		return ParameterDto.builder()
				.id(parameter.getIdParameter())
				.description(parameter.getDescription())
				.build();
	}
	
	public List<ParameterDto> toParameterDtoList(List<Parameter> parameterList) {
		
		List<ParameterDto> parameterDtoList = new ArrayList<ParameterDto>();
		parameterList.forEach(parameter -> parameterDtoList.add(toParameterDto(parameter)));		
		return parameterDtoList;
	}
}
