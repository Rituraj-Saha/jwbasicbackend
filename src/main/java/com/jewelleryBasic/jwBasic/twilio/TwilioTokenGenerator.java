package com.jewelleryBasic.jwBasic.twilio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jewelleryBasic.jwBasic.controller.UserController;
import com.twilio.jwt.accesstoken.AccessToken;
import com.twilio.jwt.accesstoken.ChatGrant;

@Service
public class TwilioTokenGenerator {
	@Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.apiKeySid}")
    private String apiKeySid;

    @Value("${twilio.apiKeySecret}")
    private String apiKeySecret;
    Logger logger = LoggerFactory.getLogger(TwilioTokenGenerator.class);
    public AccessToken  generateToken() {
        // Initialize and build the access token with necessary grants
    	logger.info("ACCOUNT_SID: "+ accountSid+" APIKEY_SID: "+apiKeySid+" API_SECRET: "+apiKeySecret);
    	AccessToken token = new AccessToken.Builder(accountSid, apiKeySid, apiKeySecret)
                .grant(new ChatGrant())
                // Add other necessary grants as needed (e.g., VideoGrant, VoiceGrant)
                .build();
    	 return token;
    }

    public String  generateToken2() {
        // Initialize and build the access token with necessary grants
    	AccessToken token = new AccessToken.Builder(accountSid, apiKeySid, apiKeySecret)
                .grant(new ChatGrant())
                // Add other necessary grants as needed (e.g., VideoGrant, VoiceGrant)
                .build();
    	 return token.toJwt();
    }
}
