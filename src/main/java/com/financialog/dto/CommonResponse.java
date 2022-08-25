package com.financialog.dto;

public class CommonResponse<T> {
	
	private String responseCode;
	
	private String responseMessage;
	
	private T responseData;
	
	public CommonResponse() {
		
	}
	
	public CommonResponse(String code, String message, T data) {
		this.responseCode = code;
		this.responseMessage = message;
		this.responseData = data;
	}
	
	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public T getResponseData() {
		return responseData;
	}

	public void setResponseData(T responseData) {
		this.responseData = responseData;
	}

	public  enum ResponseCodeEnum {
		
		SUCCESS("2001"), FAILURE("4001"),
		VALIDATION("4002"), UNAUTHORIZED("4003");
		
		private String statusCode;
		
		ResponseCodeEnum(String statusCode) {
			this.statusCode = statusCode;
		}
		
		public String getCode() {
			return this.statusCode;
		}
		
	}

}
