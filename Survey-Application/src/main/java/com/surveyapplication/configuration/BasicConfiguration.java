package com.surveyapplication.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("basic")
@Getter @Setter
public class BasicConfiguration {
	private boolean value;
	private String message;
	private int number;
}
