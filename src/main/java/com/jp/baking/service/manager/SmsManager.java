package com.jp.baking.service.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.jp.baking.service.dto.sms.CreateSmsDto;
import com.jp.baking.service.twilio.SmsRequest;
import com.jp.baking.service.twilio.TwilioSmsSender;

@org.springframework.stereotype.Service
public class SmsManager {

	private final TwilioSmsSender twilioSmsSender;
	
	@Autowired
	public SmsManager(TwilioSmsSender twilioSmsSender) {
		this.twilioSmsSender = twilioSmsSender;
	}
	
	public void sendSms(CreateSmsDto dto) {
		
		SmsRequest sms = new SmsRequest(dto.getCountryPrefix(), dto.getPhone(), dto.getMessage());		
		twilioSmsSender.send(sms);
	}
}
