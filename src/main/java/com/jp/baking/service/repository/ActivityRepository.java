package com.jp.baking.service.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jp.baking.service.model.Activity;

public interface ActivityRepository extends CustomRepository<Activity, Long>, JpaSpecificationExecutor<Activity> {

	public Activity findByIdActivity(Long idActivity);
	public Activity findByDescription(String description);
}
