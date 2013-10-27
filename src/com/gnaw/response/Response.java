package com.gnaw.response;

import com.gnaw.Profile;
import com.gnaw.models.SharedFile;

public class Response {

	public static final int REQUEST_IDENTIFIER_NOT_FOUND = 5123;
	public static final int PROFILE_FOUND = 6342;
	public static final int SHARED_FILES_FOUND = 6342;
	public static final int PROFILE_NOT_FOUND = 4342;
	public static final int MESSAGE_DELIVERED = 5923;
	public static final int MESSAGE_NOT_DELIVERED = 1241;

	private int code;
	private Profile profile;
	private SharedFile sharedFiles;
	private String message;

	public Response() {

	}

	public Response(int code, Profile profile) {
		this.code = code;
		this.setProfile(profile);
	}

	public Response(int code, String message) {
		this.code = code;
		this.setMessage(message);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public SharedFile getSharedFiles() {
		return sharedFiles;
	}

	public void setSharedFiles(SharedFile sharedFiles) {
		this.sharedFiles = sharedFiles;
	}

}