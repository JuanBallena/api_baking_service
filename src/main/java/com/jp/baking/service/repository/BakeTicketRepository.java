package com.jp.baking.service.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jp.baking.service.model.Activity;
import com.jp.baking.service.model.BakeTicket;
import com.jp.baking.service.model.PlaceAttention;

public interface BakeTicketRepository extends CustomRepository<BakeTicket, Long>, JpaSpecificationExecutor<BakeTicket> {

	public BakeTicket findByIdBakeTicket(Long idBakeTicket);
	public BakeTicket findByNumberAttentionAndActivityAndPlaceAttention(String numberAttention, Activity activity, PlaceAttention placeAttention);
}
