package com.jp.baking.service.dto.bakeTicket;

import com.jp.baking.service.dto.activity.ActivityDto;
import com.jp.baking.service.dto.customer.CustomerDto;
import com.jp.baking.service.dto.parameter.ParameterDto;
import com.jp.baking.service.dto.placeAttention.PlaceAttentionDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BakeTicketDto {

	private Long id;
	private CustomerDto customer;
	private ActivityDto activity;
	private String numberAttention;
	private PlaceAttentionDto placeAttention;
	private ParameterDto bakingStatus;
}
