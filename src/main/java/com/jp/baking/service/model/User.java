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
@Table(name="users")
@Entity
public class User {

	private static final String COLUMN_ID = "User_Id";
	private static final String COLUMN_USERNAME = "User_Username";
	private static final String COLUMN_PASSWORD = "User_Password";
	
	public static final String ID_PROPERTY = "idUser";
	public static final String USERNAME_PROPERTY = "username";
	public static final String PASSWORD_PROPERTY = "password";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = COLUMN_ID)
	private Long idUser;
	
	@Column(name = COLUMN_USERNAME)
	private String username;
	
	@Column(name = COLUMN_PASSWORD)
	private String password;
}
