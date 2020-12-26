package com.jp.baking.service.twilio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SmsRequest {

	private String prefixCountry;
	private String number;
	private String message;
}
