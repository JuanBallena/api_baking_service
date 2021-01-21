package com.jp.baking.service.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jp.baking.service.dto.activity.ActivityDto;
import com.jp.baking.service.model.Activity;

@Component
public class ActivityConverter {
	
	public ActivityDto toActivityDto(Activity activity) {
		
		return ActivityDto.builder()
				.id(activity.getIdActivity())
				.description(activity.getDescription())
				.date(activity.getDate())
				.build();
	}
	
	public List<ActivityDto> toActivityDtoList(List<Activity> activityList) {
		
		List<ActivityDto> activityDtoList = new ArrayList<ActivityDto>();
		activityList.forEach( activity -> activityDtoList.add(toActivityDto(activity)));
		return activityDtoList;
	}
}
