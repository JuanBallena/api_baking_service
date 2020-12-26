
package com.jp.baking.service.repository;

import com.jp.baking.service.model.Setting;

public interface SettingRepository extends CustomRepository<Setting, Long> {

	public Setting findByIdSetting(Long IdSetting);
}
