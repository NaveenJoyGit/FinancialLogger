package com.financialog.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientService {

	public static final String ALPHA_VANTAGE_BASE_URL = "https://www.alphavantage.co/query?";

	WebClient stockClient = 
			WebClient.builder().baseUrl(ALPHA_VANTAGE_BASE_URL)
			.defaultHeader(HttpHeaders.CONTENT_TYPE , MediaType.APPLICATION_JSON_VALUE)
			.build();
}