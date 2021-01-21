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
@Table(name="customers")
@Entity
public class Customer {
	
	private static final String COLUMN_ID = "Customer_Id";
	private static final String COLUMN_NAME = "Customer_Name";
	
	public static final String ID_CUSTOMER_PROPERTY = "idCustomer";
	public static final String NAME_PROPERTY = "name";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = COLUMN_ID)
	private Long idCustomer;
	
	@Column(name = COLUMN_NAME)
	private String name;
}
