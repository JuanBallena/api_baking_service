package com.jp.baking.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jp.baking.service.model.Activity;
import com.jp.baking.service.model.BakeTicket;
import com.jp.baking.service.model.Parameter;
import com.jp.baking.service.model.PlaceAttention;

public interface BakeTicketRepository extends CustomRepository<BakeTicket, Long>, JpaSpecificationExecutor<BakeTicket> {

	public BakeTicket findByIdBakeTicket(Long idBakeTicket);
	public BakeTicket findByNumberAttentionAndActivityAndPlaceAttention(String numberAttention, Activity activity, PlaceAttention placeAttention);
	public List<BakeTicket> findByActivityAndBakingStatus(Activity activity, Parameter parameter);
	public List<BakeTicket> findByActivity(Activity activity);
}
