package com.immanent.exceptions;

public enum ErrorMessages {
	
	//Generic errors
	Exception(001,"Application Exception"),
	ConnectionRefuse(002,"Unable to connect to diaspora pod"),
	
	//Basic HTTP Errors
	BadRequest(050,"Bad Request"), 
	Unauthorized(051,"Unauthorized"), 
	Forbidden(052,"Forbidden"), 
	NotFound(053,"Not Found"),
	InternalServerError(060,"Internal Server Error"),
	
	//API Errors
	IllegalAccessToken(300,"Access token is illegal"),
	AccessTokenExpire(301,"Access token is expired"),
	NoPermissionGetProfileDetails(310,"Do not have permission to read profile data from the Diaspora Pod");

	private int code;
	private String errorMessage;

	private ErrorMessages(int c, String msg) {
		code = c;
		errorMessage = msg;
	}

	public int getStatusCode() {
		return code;
	}
	
	public String getErrorMessage(){
		return errorMessage;
	}
	
	public static String getErrorMessageForCode(int code){

		for (ErrorMessages type : ErrorMessages.values()) {
			if (type.code == code)
				return type.getErrorMessage();
		}
		return ErrorMessages.Exception.getErrorMessage();
	}

}
