package com.jp.baking.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@AllArgsConstructor(access=AccessLevel.PUBLIC)
@ToString(callSuper=true, includeFieldNames=true)
@Table(name="settings")
@Entity
public class Setting {

	private static final String COLUMN_ID = "Setting_Id";
	private static final String COLUMN_NAME = "Setting_Name";
	private static final String COLUMN_DESCRIPTION = "Setting_Description";
	private static final String COLUMN_VALUE = "Setting_Value";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = COLUMN_ID)
	private Long idSetting;
	
	@Column(name = COLUMN_NAME)
	private String name;
	
	@Column(name = COLUMN_DESCRIPTION)
	private String description;
	
	@Column(name = COLUMN_VALUE)
	private Long value;
}
