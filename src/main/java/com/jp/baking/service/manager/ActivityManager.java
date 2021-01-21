package com.jp.baking.service.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.jp.baking.service.converter.ActivityConverter;
import com.jp.baking.service.dto.activity.ActivityDto;
import com.jp.baking.service.dto.activity.CreateActivityDto;
import com.jp.baking.service.dto.activity.UpdateActivityDto;
import com.jp.baking.service.interf.UniqueDataValidator;
import com.jp.baking.service.model.Activity;
import com.jp.baking.service.repository.ActivityRepository;
import com.jp.baking.service.requestParameter.ActivityRequest;
import com.jp.baking.service.response.ListResponse;
import com.jp.baking.service.validator.Validator;

@Service
public class ActivityManager implements UniqueDataValidator {

	private Validator validator = new Validator();
	
	@Autowired
	private ActivityRepository activityRepository;
	
	@Autowired
	private ActivityConverter activityConverter;
	
	public ListResponse findAll(ActivityRequest request) {
		
		if (request.hasFilterAndPagingAndSortParameters()) {
			Page<Activity> activityPage = activityRepository.findAll(request.getFilters(), request.getPagingAndSort());
			return this.toListResponse(activityPage);
		}
		
		if (request.hasFilterAndPagingParameters()) {
			Page<Activity> activityPage = activityRepository.findAll(request.getFilters(), request.getPaging());
			return this.toListResponse(activityPage);
		}
		
		if (request.hasFilterAndSortParameters()) {
			List<Activity> activityList = activityRepository.findAll(request.getFilters(), request.getSort());
			return this.toListResponse(activityList);
		}
		
		if (request.hasPagingAndSortParameters()) {
			Page<Activity> activityPage = activityRepository.findAll(request.getPagingAndSort());
			return this.toListResponse(activityPage);
		}
		
		if (request.hasFilterParameters()) {
			List<Activity> activityList = activityRepository.findAll(request.getFilters());
			return this.toListResponse(activityList);
		}

		if (request.hasPagingParameters()) {
			Page<Activity> activityPage = activityRepository.findAll(request.getPaging());
			return this.toListResponse(activityPage);
		}
		
		if (request.hasSortParameters()) {
			List<Activity> activityList = activityRepository.findAll(request.getSort());
			return this.toListResponse(activityList);
		}
		
		List<Activity> activityList = activityRepository.findAll();
		return this.toListResponse(activityList);
				
	}
		
	public ListResponse toListResponse(Page<Activity> page) {
		
		List<ActivityDto> activityDtoList = activityConverter.toActivityDtoList(page.getContent());
		return ListResponse.builder()
				.list(activityDtoList)
				.totalPages(page.getTotalPages())
				.build();
	}
	
	public ListResponse toListResponse(List<Activity> list) {
		
		List<ActivityDto> activityDtoList = activityConverter.toActivityDtoList(list);
		return ListResponse.builder()
				.list(activityDtoList)
				.totalPages(activityDtoList.size() == 0 ? 0 : 1)
				.build();
	}
	
	public ActivityDto findById(Long idActivity) {
		
		if (validator.notPositiveNumber(idActivity)) return null;
		
		Activity activity = activityRepository.findByIdActivity(idActivity);
		
		if (validator.isNull(activity)) return null;
		
		return activityConverter.toActivityDto(activity);
	}

	public ActivityDto save(CreateActivityDto dto) {
		
		Activity activity = new Activity();
		activity.setDescription(dto.getDescription());
		activity.setDate(dto.getDate());
		activityRepository.save(activity);
		activityRepository.refresh(activity);
		return activityConverter.toActivityDto(activity);
	}
	
	public ActivityDto update(UpdateActivityDto dto) {
		
		if (validator.notPositiveNumber(dto.getIdActivity())) return null;
		
		Activity activity = activityRepository.findByIdActivity(dto.getIdActivity());
		
		if (validator.isNull(activity)) return null;
		
		activity.setDescription(dto.getDescription());
		activity.setDate(dto.getDate());
		activityRepository.save(activity);
		activityRepository.refresh(activity);
		return activityConverter.toActivityDto(activity);
	}
	
	private Activity findActivity(String property, Object value) {
		
		switch (property) {
			case Activity.DESCRIPTION_PROPERTY:
				return activityRepository.findByDescription(value.toString());
			default:
				return null;
		}
	}

	@Override
	public boolean uniqueToCreate(String property, Object value) {
		
		Activity activity = this.findActivity(property, value);
		return validator.isNull(activity);
	}

	@Override
	public boolean uniqueToUpdate(String property, Object value, Long id) {
	
		Activity activity = this.findActivity(property, value);
		
		if (validator.notNull(activity)) return activity.getIdActivity().equals(id);
		
		return true;	
	}
}
