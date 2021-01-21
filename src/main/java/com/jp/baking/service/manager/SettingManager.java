package com.jp.baking.service.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.baking.service.converter.SettingConverter;
import com.jp.baking.service.definition.SettingDefinition;
import com.jp.baking.service.dto.setting.SettingDto;
import com.jp.baking.service.dto.setting.UpdateSettingValueDto;
import com.jp.baking.service.model.Setting;
import com.jp.baking.service.repository.SettingRepository;
import com.jp.baking.service.response.ListResponse;
import com.jp.baking.service.validator.Validator;

@Service
public class SettingManager {
	
	@Autowired
	private SettingRepository settingRepository;
	
	@Autowired
	private SettingConverter settingConverter;
	
	private Validator validator = new Validator();
	
	public ListResponse findAll() {
		
		List<Setting> settingList = settingRepository.findAll();
		return toListResponse(settingList);
	}
	
	public ListResponse toListResponse(List<Setting> settingList) {
		
		List<SettingDto> list = settingConverter.toSettingDtoList(settingList);
		return ListResponse.builder()
				.list(list)
				.totalPages(list.size() == 0 ? 0 : 1)
				.build();
	}
	
	public SettingDto update(UpdateSettingValueDto dto) {
		
		if (validator.notPositiveNumber(dto.getIdSetting())) return null;
		
		Setting setting = settingRepository.findByIdSetting(dto.getIdSetting());
		
		if (validator.isNull(setting)) return null;
			
		setting.setValue(dto.getValue());
		settingRepository.save(setting);
		settingRepository.refresh(setting);
		
		return settingConverter.toSettingDto(setting);
	}
	
	public Long currentActivityValue() {
		
		Setting setting = settingRepository.findByIdSetting(SettingDefinition.ID_SEETING_CURRENT_ACTIVITY);
		return setting.getValue();
	}
}
