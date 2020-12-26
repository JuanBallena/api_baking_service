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
@Table(name="activities")
@Entity
public class Activity {

	private static final String COLUMN_ID = "Activity_Id";
	private static final String COLUMN_DESCRIPTION = "Activity_Description";
	private static final String COLUMN_DATE = "Activity_Date";
	private static final String COLUMN_FINISHED = "Activity_Finished";
	
	public static final String ID_ACTIVITY_PROPERTY = "idActivity";
	public static final String DESCRIPTION_PROPERTY = "description";
	public static final String DATE_PROPERTY = "date";
	public static final String FINISHED_PROPERTY = "finished";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = COLUMN_ID)
	private Long idActivity;
	
	@Column(name = COLUMN_DESCRIPTION)
	private String description;
	
	@Column(name = COLUMN_DATE)
	private String date;
	
	@Column(name = COLUMN_FINISHED, insertable = false)
	private Boolean finished;
	
}
