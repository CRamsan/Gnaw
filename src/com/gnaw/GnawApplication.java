/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnaw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.gnaw.discovery.BeaconClient;
import com.gnaw.discovery.BeaconServer;
import com.gnaw.interfaces.Application;
import com.gnaw.transmission.TransmissionClient;
import com.gnaw.transmission.TransmissionServer;

/**
 * 
 * @author cesar
 */
public class GnawApplication implements Application {

	private static final String BROADCAST = "broadcast";
	private static final String LISTEN = "listen";
	private static final String START = "start";
	private static final String STOP = "stop";
	private static final String POST = "post";
	private static final String QUIT = "quit";

	private BeaconServer beaconServer;
	private BeaconClient beaconClient;
	private TransmissionServer transmissionServer;
	private TransmissionClient transmissionClient;

	public void init() {
		this.beaconServer = new BeaconServer();
		this.beaconClient = new BeaconClient();
		this.transmissionClient = new TransmissionClient();
		this.transmissionServer = new TransmissionServer(this);

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			String input;
			boolean stop = false;
			while ((input = br.readLine()) != null && !false) {
				switch (input) {
				case BROADCAST:
					beaconServer.startBroadcasting();
					break;
				case LISTEN:
					beaconClient.startListening();
					break;
				case START:
					transmissionServer.startListening();
					break;
				case STOP:
					transmissionServer.stopListening();
					break;
				case POST:
					break;
				case QUIT:
					stop = true;
					break;
				default:
					System.out.println("Command not found");
					break;
				}
			}
		} catch (IOException io) {
			io.printStackTrace();
		}

	}

	public static void main(String args[]) {
		GnawApplication gnaw = new GnawApplication();
		gnaw.init();
	}

	@Override
	public Profile getProfile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deliverMessage() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deliverOffer() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deliverOfferResponse() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deliverSearchRequest() {
		// TODO Auto-generated method stub
		return false;
	}
}
