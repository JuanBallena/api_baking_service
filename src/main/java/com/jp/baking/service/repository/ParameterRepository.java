package com.jp.baking.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jp.baking.service.model.Parameter;
import com.jp.baking.service.model.ParameterType;

public interface ParameterRepository extends CustomRepository<Parameter, Long>, JpaSpecificationExecutor<Parameter> {

	public List<Parameter> findByParameterType(ParameterType parameterType);
}
