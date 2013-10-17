/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnaw.response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.cesarandres.ayllu.discovery.BeaconClient;
import com.cesarandres.ayllu.discovery.BeaconServer;
import com.cesarandres.ayllu.transmission.TransmissionClient;
import com.cesarandres.ayllu.transmission.TransmissionServer;

/**
 * 
 * @author cesar
 */
public class GnawApplication {

	private static final String BROADCAST = "broadcast";
	private static final String LISTEN = "listen";
	private static final String POST = "post";
	private static final String QUIT = "quit";

	private static BeaconServer beaconServer;
	private static BeaconClient beaconClient;
	private static TransmissionServer transmissionServer;
	private static TransmissionClient transmissionClient;

	public static void main(String args[]) {
		GnawApplication.beaconServer = new BeaconServer();
		GnawApplication.beaconClient = new BeaconClient();
		GnawApplication.transmissionClient = new TransmissionClient();
		GnawApplication.transmissionServer = new TransmissionServer();

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
}
