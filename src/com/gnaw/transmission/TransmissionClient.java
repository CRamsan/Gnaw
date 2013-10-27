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
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gnaw.request.Request;
import com.gnaw.response.Response;
import com.google.gson.Gson;

/**
 * 
 * @author cesar
 */
public class TransmissionClient {

	private Socket kkSocket;
	private PrintWriter out;
	private BufferedReader in;
	private static final Gson gson = new Gson();

	public TransmissionClient() {
	}

	public Response startConnection(String address, Request request) {
		String data = gson.toJson(request);
		Response response = null;
		try {
			kkSocket = new Socket(address, 4444);
			out = new PrintWriter(kkSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					kkSocket.getInputStream()));
		} catch (UnknownHostException ex) {
			Logger.getLogger(TransmissionClient.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(TransmissionClient.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		out.println(data);
		String inputLine;
		try {
			inputLine = in.readLine();
			response = gson.fromJson(inputLine, Response.class);
			in.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			kkSocket.close();
		} catch (IOException ex) {
			Logger.getLogger(TransmissionClient.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		return response;
	}

	public void stopConnection() {
		try {
			out.close();
			kkSocket.close();
		} catch (IOException ex) {
			Logger.getLogger(TransmissionClient.class.getName()).log(
					Level.SEVERE, null, ex);
		}

	}
}
