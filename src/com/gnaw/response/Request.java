package com.gnaw.response;

import java.util.HashMap;

public class Request {
	private String request;
	private HashMap<String, String> parameters;
	private HashMap<String, String> payload;

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public HashMap<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(HashMap<String, String> parameters) {
		this.parameters = parameters;
	}

	public HashMap<String, String> getPayload() {
		return payload;
	}

	public void setPayload(HashMap<String, String> payload) {
		this.payload = payload;
	}

}
