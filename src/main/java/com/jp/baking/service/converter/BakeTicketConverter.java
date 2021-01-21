package com.jp.baking.service.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jp.baking.service.dto.activity.ActivityDto;
import com.jp.baking.service.dto.bakeTicket.BakeTicketDto;
import com.jp.baking.service.dto.customer.CustomerDto;
import com.jp.baking.service.dto.parameter.ParameterDto;
import com.jp.baking.service.dto.placeAttention.PlaceAttentionDto;
import com.jp.baking.service.model.BakeTicket;

@Component
public class BakeTicketConverter {

	public BakeTicketDto toBakeTicketDto(BakeTicket bakeTicket) {
		
		return BakeTicketDto.builder()
				.id(bakeTicket.getIdBakeTicket())
				.customer(CustomerDto.builder()
						.id(bakeTicket.getCustomer().getIdCustomer())
						.name(bakeTicket.getCustomer().getName())
						.build())
				.activity(ActivityDto.builder()
						.id(bakeTicket.getActivity().getIdActivity())
						.description(bakeTicket.getActivity().getDescription())
						.date(bakeTicket.getActivity().getDate())
						.build())
				.numberAttention(bakeTicket.getNumberAttention())
				.numberBaked(bakeTicket.getNumberBaked())
				.placeAttention(PlaceAttentionDto.builder()
						.id(bakeTicket.getPlaceAttention().getIdPlaceAttention())
						.name(bakeTicket.getPlaceAttention().getName())
						.build())
				.bakingStatus(ParameterDto.builder()
						.id(bakeTicket.getBakingStatus().getIdParameter())
						.description(bakeTicket.getBakingStatus().getDescription())
						.build())
				.build();
	}
	
	public List<BakeTicketDto> toBakeTicketDtoList(List<BakeTicket> bakeTicketList) {
		
		List<BakeTicketDto> bakeTicketDtoList = new ArrayList<BakeTicketDto>();
		bakeTicketList.forEach(bakeTicket -> bakeTicketDtoList.add(toBakeTicketDto(bakeTicket)));		
		return bakeTicketDtoList;
	}
}
