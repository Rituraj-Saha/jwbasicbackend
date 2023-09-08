package com.jewelleryBasic.jwBasic.twilio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;
@Configuration
public class TwilioInitializer {
	private final static Logger LOGGER = LoggerFactory.getLogger(TwilioInitializer.class);
    private final TwilioCofiguration twilioConfiguration;
    @Autowired
    public TwilioInitializer(TwilioCofiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
        Twilio.init(
                twilioConfiguration.getAccountSid(),
                twilioConfiguration.getAuthToken()
        );
        LOGGER.info("Twilio initialized ... with account sid {} ", twilioConfiguration.getAccountSid());
    }
}
