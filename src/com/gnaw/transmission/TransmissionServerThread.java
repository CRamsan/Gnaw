/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnaw.transmission;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.gnaw.Profile;
import com.gnaw.interfaces.DataSourceInterface;
import com.gnaw.models.SharedFile;
import com.gnaw.request.Request;
import com.gnaw.response.Response;
import com.google.gson.Gson;

/**
 * 
 * @author cesar
 */
public class TransmissionServerThread extends Thread {

	private Socket socket = null;
	private DataSourceInterface application;
	private static final Gson gson = new Gson();
	private boolean readStream = false;
	private int size = 0;
	
	public TransmissionServerThread(Socket socket,
			DataSourceInterface application) {
		super("KKMultiServerThread");
		this.socket = socket;
		this.application = application;
	}

	public void run() {

		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			String inputLine;
			inputLine = in.readLine();

			Request request = gson.fromJson(inputLine, Request.class);
			request.setAddress(this.socket.getInetAddress().getHostAddress());

			String response = gson.toJson(executeRequest(request));
			out.println(response);

			if (readStream) {
			}

			out.close();
			in.close();
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Response executeRequest(Request request) {
		Request.RequestIdentifier identifier;
		try {
			identifier = Request.RequestIdentifier
					.valueOf(request.getRequest());
		} catch (EnumConstantNotPresentException e) {
			return new Response(Response.REQUEST_IDENTIFIER_NOT_FOUND, "");
		}
		switch (identifier) {
		case GET_PROFILE:
			return this.getProfileResponse(request);
		case GET_SHARED_FILES:
			return this.getSharedFilesResponse(request);
		case MESSAGE:
			return this.processMessage();
		case OFFER:
			return this.processOffer(request);
		case RESPONSE:
			return this.processOfferResonse(request);
		case PUSH:
			return this.processPush(request);
		case SEARCH:
			break;
		case RESULT:
			break;
		default:
			break;
		}
		return null;
	}

	private Response getProfileResponse(Request request) {
		Profile profile = this.application.getProfile();
		Response response = new Response();
		if (profile != null) {
			response.setProfile(profile);
			response.setCode(Response.PROFILE_FOUND);
		} else {
			profile = new Profile();
			profile.setName("UNKOWN");
			response.setProfile(profile);
			response.setCode(Response.PROFILE_NOT_FOUND);
		}
		return response;
	}

	private Response getSharedFilesResponse(Request request) {
		SharedFile sharedFilesd = this.application.getSharedFiles();
		Response response = new Response();
		response.setSharedFiles(sharedFilesd);
		response.setCode(Response.SHARED_FILES_FOUND);
		return response;
	}

	private Response processMessage() {
		boolean delivered = this.application.deliverMessage();
		Response response = new Response();
		if (delivered) {
			response.setMessage("Delivered");
			response.setCode(Response.MESSAGE_DELIVERED);
		} else {
			response.setMessage("Not Delivered");
			response.setCode(Response.MESSAGE_NOT_DELIVERED);
		}
		return response;
	}

	private Response processOffer(Request request) {
		boolean delivered = this.application.deliverOffer(request);
		Response response = new Response();
		if (delivered) {
			response.setMessage("Delivered");
			response.setCode(Response.MESSAGE_DELIVERED);
		} else {
			response.setMessage("Not Delivered");
			response.setCode(Response.MESSAGE_NOT_DELIVERED);
		}
		return response;
	}

	private Response processOfferResonse(Request request) {
		boolean delivered = this.application.deliverOfferResponse(request);
		Response response = new Response();
		if (delivered) {
			response.setMessage("Delivered");
			response.setCode(Response.MESSAGE_DELIVERED);
		} else {
			response.setMessage("Not Delivered");
			response.setCode(Response.MESSAGE_NOT_DELIVERED);
		}
		return response;
	}

	private Response processPush(Request request) {
		boolean confirm = this.application.deliverPushRequest(request);
		Response response = new Response();
		if (confirm) {
			this.readStream = true;
			this.size = request.getFileSize();
			response.setMessage("Initiating transfer");
			response.setCode(Response.MESSAGE_DELIVERED);
		} else {
			response.setMessage("Transfer not authorized");
			response.setCode(Response.MESSAGE_NOT_DELIVERED);
		}
		return response;
	}
	
	private Response processSearch() {
		boolean delivered = this.application.deliverSearchRequest();
		Response response = new Response();
		if (delivered) {
			response.setMessage("Delivered");
			response.setCode(Response.MESSAGE_DELIVERED);
		} else {
			response.setMessage("Not Delivered");
			response.setCode(Response.MESSAGE_NOT_DELIVERED);
		}
		return response;
	}
}
