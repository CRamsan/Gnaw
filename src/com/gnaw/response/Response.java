package com.gnaw.response;

public class Response {

	public static final int REQUEST_IDENTIFIER_NOT_FOUND = 5123;
	public static final int PROFILE_FOUND = 6342;
	public static final int PROFILE_NOT_FOUND = 4342;
	public static final int MESSAGE_DELIVERED = 5923;
	public static final int MESSAGE_NOT_DELIVERED = 1241;

	private int code;
	private Object content;

	public Response() {

	}

	public Response(int code, String content) {
		this.code = code;
		this.content = content;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
}
