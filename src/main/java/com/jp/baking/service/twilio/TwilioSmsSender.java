package com.jp.baking.service.twilio;

import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

@Service
public class TwilioSmsSender {
	
	public void send(SmsRequest smsRequest) {
		
		Twilio.init(TwilioConfiguration.ACCOUNT_SID, TwilioConfiguration.AUTH_TOKEN);
		
		PhoneNumber to = new PhoneNumber(smsRequest.getPrefixCountry() + " " + smsRequest.getNumber());
		PhoneNumber from = new PhoneNumber(TwilioConfiguration.TRIAL_NUMBER);
		String message = smsRequest.getMessage();
				
		MessageCreator messageCreator = Message.creator(to, from, message);
		messageCreator.create();
	}

}
