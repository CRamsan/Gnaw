package com.gnaw.request;

import java.util.HashMap;

public class Request {

	public enum RequestIdentifier {
		GET_PROFILE, GET_SHARED_FILES, MESSAGE, OFFER, RESPONSE, SEARCH, RESULT, PUSH
	};

	public enum ViewMode {
		SLIM, FULL
	}

	public enum ContentType {
		FILE
	}

	public enum Action {
		ACCEPT, REJECT
	}

	private String request;
	private String profile;
	private String address;
	private HashMap<String, String> parameters;
	private HashMap<String, String> payload;

	public Request() {
	}

	public Request(RequestIdentifier request, String profile) {
		this.request = request.toString();
		this.profile = profile;
	}

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

	public void setViewMode(ViewMode mode) {
		if (this.parameters == null) {
			this.parameters = new HashMap<String, String>();
		}
		this.parameters.put("view", mode.toString());
	}

	public void setContentType(ContentType type) {
		if (this.parameters == null) {
			this.parameters = new HashMap<String, String>();
		}
		this.parameters.put("content-type", type.toString());
	}

	public void setFileName(String name) {
		if (this.parameters == null) {
			this.parameters = new HashMap<String, String>();
		}
		this.parameters.put("file-name", name);
	}

	public void setFileSize(int bytes) {
		if (this.parameters == null) {
			this.parameters = new HashMap<String, String>();
		}
		this.parameters.put("file-size", String.valueOf(bytes));
	}

	public void setAction(Action action) {
		if (this.parameters == null) {
			this.parameters = new HashMap<String, String>();
		}
		this.parameters.put("action", action.toString());
	}

	public void setToken(String token) {
		if (this.parameters == null) {
			this.parameters = new HashMap<String, String>();
		}
		this.parameters.put("token", token);
	}

	public ViewMode getViewMode() {
		if (this.parameters == null) {
			return null;
		}
		return ViewMode.valueOf(this.parameters.get("view"));
	}

	public ContentType getContentType() {
		if (this.parameters == null) {
			return null;
		}
		return ContentType.valueOf(this.parameters.get("content-type"));
	}

	public String getFileName() {
		if (this.parameters == null) {
			return null;
		}
		return this.parameters.get("file-name");
	}

	public int getFileSize() {
		if (this.parameters == null) {
			return -1;
		}
		return Integer.parseInt(this.parameters.get("file-size"));
	}

	public Action getAction() {
		if (this.parameters == null) {
			return null;
		}

		return Action.valueOf(this.parameters.get("action"));
	}

	public String getToken() {
		if (this.parameters == null) {
			return null;
		}
		return this.parameters.get("token");
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
