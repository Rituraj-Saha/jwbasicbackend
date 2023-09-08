package com.jewelleryBasic.jwBasic.twilio;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Configuration
@ConfigurationProperties("twilio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TwilioCofiguration {
	private String accountSid;
    private String authToken;
    private String trialNumber;
}
