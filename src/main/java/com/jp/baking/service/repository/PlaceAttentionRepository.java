package com.jp.baking.service.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jp.baking.service.model.PlaceAttention;

public interface PlaceAttentionRepository extends CustomRepository<PlaceAttention, Long>, JpaSpecificationExecutor<PlaceAttention> {

	public PlaceAttention findByIdPlaceAttention(Long idPlaceAttention);
}
