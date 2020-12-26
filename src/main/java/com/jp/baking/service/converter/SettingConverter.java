package com.jp.baking.service.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jp.baking.service.dto.setting.SettingDto;
import com.jp.baking.service.model.Setting;

@Component
public class SettingConverter {

	public SettingDto toSettingDto(Setting setting) {
				
		return SettingDto.builder()
				.id(setting.getIdSetting())
				.name(setting.getName())
				.description(setting.getDescription())
				.value(setting.getValue())
				.build();
	}
	
	public List<SettingDto> toSettingDtoList(List<Setting> settingList) {
		
		List<SettingDto> settingDTOList = new ArrayList<SettingDto>();
		settingList.forEach(setting -> settingDTOList.add(toSettingDto(setting)));		
		return settingDTOList;
	}
}
