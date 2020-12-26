package com.jp.baking.service.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.baking.service.definition.SettingDefinition;
import com.jp.baking.service.model.Preference;
import com.jp.baking.service.model.Setting;
import com.jp.baking.service.repository.SettingRepository;

@Service
public class PreferenceManager {

	@Autowired
	private SettingRepository settingRepository;
	
	public List<Preference> getPreferences() {
		
		List<Preference> preferences = new ArrayList<Preference>();
		preferences.add(this.currentActivity());
		
		return preferences;
	}
	
	public Preference currentActivity() {
		
		Setting setting = settingRepository.findByIdSetting(SettingDefinition.ID_SEETING_CURRENT_ACTIVITY);
		return Preference.builder()
				.name(SettingDefinition.SETTING_CURRENT_ACTIVITY)
				.data(setting.getValue())
				.build();
	}
}
