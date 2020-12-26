package com.jp.baking.service.response;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter 
@Setter
@NoArgsConstructor 
@ToString(callSuper=true, includeFieldNames=true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Component
public class ListResponse {

	@SuppressWarnings("rawtypes")
	private List list;
	private int totalPages;
	
	public Boolean hasData() {
		return this.getList().size() != 0;
	}
	
	public Boolean hasNoData() {
		return this.getList().size() == 0;
	}
}
