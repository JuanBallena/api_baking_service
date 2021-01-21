package com.jp.baking.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="bake_tickets")
@Entity
public class BakeTicket {

	private static final String COLUMN_ID = "BakeTicket_Id";
	private static final String COLUMN_ID_CUSTOMER = "BakeTicket_IdCustomer";
	private static final String COLUMN_ID_ACTIVITY = "BakeTicket_IdActivity";
	private static final String COLUMN_NUMBER_ATTENTION = "BakeTicket_NumberAttention";
	private static final String COLUMN_NUMBER_BAKED = "BakeTicket_NumberBaked";
	private static final String COLUMN_ID_PLACE_ATTENTION = "BakeTicket_IdPlaceAttention";
	private static final String COLUMN_BAKING_STATUS = "BakeTicket_IdBakingStatus";
	
	public static final String ID_BAKE_TICKET_PROPERTY = "idBakeTicket";
	public static final String CUSTOMER_PROPERTY = "customer";
	public static final String ACTIVITY_PROPERTY = "activity";
	public static final String NUMBER_ATTENTION_PROPERTY = "numberAttention";
	public static final String NUMBER_BAKED_PROPERTY = "numberBaked";
	public static final String PLACE_ATTENTION_PROPERTY = "placeAttention";
	public static final String BAKING_STATUS_PROPERTY = "bakingStatus";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = COLUMN_ID)
	private Long idBakeTicket;
	
	@ManyToOne
	@JoinColumn(name = COLUMN_ID_CUSTOMER)
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = COLUMN_ID_ACTIVITY)
	private Activity activity;
	
	@Column(name = COLUMN_NUMBER_ATTENTION)
	private String numberAttention;
	
	@Column(name = COLUMN_NUMBER_BAKED)
	private Integer numberBaked;
	
	@ManyToOne
	@JoinColumn(name = COLUMN_ID_PLACE_ATTENTION)
	private PlaceAttention placeAttention;
	
	@ManyToOne
	@JoinColumn(name = COLUMN_BAKING_STATUS, insertable = false)
	private Parameter bakingStatus;
	
}
