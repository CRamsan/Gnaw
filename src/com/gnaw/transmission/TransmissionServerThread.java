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
import com.gnaw.interfaces.Application;
import com.gnaw.request.Request;
import com.gnaw.response.Response;
import com.google.gson.Gson;

/**
 * 
 * @author cesar
 */
public class TransmissionServerThread extends Thread {

	private Socket socket = null;
	private Application application;
	private static final Gson gson = new Gson();

	public TransmissionServerThread(Socket socket, Application application) {
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
			out.println(gson.toJson(executeRequest(request)));
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
			return this.getProfileResponse();
		case MESSAGE:
			return this.processMessage();
		case OFFER:
			break;
		case RESPONSE:
			break;
		case SEARCH:

			break;
		}
		return null;
	}

	private Response getProfileResponse() {
		Profile profile = this.application.getProfile();
		Response response = new Response();
		if (profile != null) {
			response.setContent(profile);
			response.setCode(Response.PROFILE_FOUND);
		} else {
			profile = new Profile();
			profile.setName("UNKOWN");
			response.setContent(profile);
			response.setCode(Response.PROFILE_NOT_FOUND);
		}
		return response;
	}

	private Response processMessage() {
		boolean delivered = this.application.deliverMessage();
		Response response = new Response();
		if (delivered) {
			response.setContent("Delivered");
			response.setCode(Response.MESSAGE_DELIVERED);
		} else {
			response.setContent("Not Delivered");
			response.setCode(Response.MESSAGE_NOT_DELIVERED);
		}
		return response;
	}

	private Response processOffer() {
		boolean delivered = this.application.deliverOffer();
		Response response = new Response();
		if (delivered) {
			response.setContent("Delivered");
			response.setCode(Response.MESSAGE_DELIVERED);
		} else {
			response.setContent("Not Delivered");
			response.setCode(Response.MESSAGE_NOT_DELIVERED);
		}
		return response;
	}

	private Response processOfferResonse() {
		boolean delivered = this.application.deliverOfferResponse();
		Response response = new Response();
		if (delivered) {
			response.setContent("Delivered");
			response.setCode(Response.MESSAGE_DELIVERED);
		} else {
			response.setContent("Not Delivered");
			response.setCode(Response.MESSAGE_NOT_DELIVERED);
		}
		return response;
	}

	private Response processSearch() {
		boolean delivered = this.application.deliverSearchRequest();
		Response response = new Response();
		if (delivered) {
			response.setContent("Delivered");
			response.setCode(Response.MESSAGE_DELIVERED);
		} else {
			response.setContent("Not Delivered");
			response.setCode(Response.MESSAGE_NOT_DELIVERED);
		}
		return response;
	}
}
