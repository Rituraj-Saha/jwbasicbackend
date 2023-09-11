package com.jewelleryBasic.jwBasic.twilio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsService{
//	private final SmsSender smsSender;
//    @Autowired
//    public SmsService(@Qualifier("twilio") TwilioSmsSender smsSender) {
//        this.smsSender = (SmsSender) smsSender;
//    }
//    public void sendSms(SmsRequest smsRequest) {
//    	smsSender.sendSms(smsRequest);
//    }
	@Value("${twilio.accountSid}")
    private String accountSid;

//    @Value("${twilio.authToken}")
//    private String authToken;
    

    @Value("${twilio.trialNumber}")
    private String trialNumber;
    
    @Value("${twilio.apiKeySid}")
    private String apiKeySid;

    @Value("${twilio.apiKeySecret}")
    private String apiKeySecret;
    
    public void sendSms(String to, String body) {
        // Initialize Twilio with your account SID and auth token
//        Twilio.init(accountSid, authToken);
    	 Twilio.init(apiKeySid, apiKeySecret,accountSid);;
        // Send SMS
        Message message = Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(trialNumber),
                body
        ).create();

        // Handle the response or errors as needed
    }
}
