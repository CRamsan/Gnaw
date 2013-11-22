/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnaw;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import com.gnaw.discovery.BeaconClient;
import com.gnaw.discovery.BeaconServer;
import com.gnaw.discovery.event.BroadcastingEndEventListener;
import com.gnaw.discovery.event.ClientFoundEventListener;
import com.gnaw.interfaces.DataSourceInterface;
import com.gnaw.interfaces.TransmissionProgressInterface;
import com.gnaw.models.SharedFile;
import com.gnaw.request.Request;
import com.gnaw.request.Request.Action;
import com.gnaw.request.Request.RequestIdentifier;
import com.gnaw.response.Response;
import com.gnaw.transmission.TransmissionClient;
import com.gnaw.transmission.TransmissionServer;

/**
 * 
 * @author cesar
 */
public class GnawApplication {

	private BeaconServer beaconServer;
	private BeaconClient beaconClient;
	private TransmissionServer transmissionServer;
	private TransmissionClient transmissionClient;
	private DataSourceInterface source;
	private HashMap<String, String> sendRequests;
	private HashMap<String, String> receiveRequests;

	public GnawApplication(DataSourceInterface source) {
		this.source = source;
		this.sendRequests = new HashMap<String, String>();
		this.receiveRequests = new HashMap<String, String>();
		
	}

	public void init() {
		this.beaconServer = new BeaconServer();
		this.beaconClient = new BeaconClient();
		this.transmissionClient = new TransmissionClient();
		this.transmissionServer = new TransmissionServer(this.source);
		this.transmissionServer.startListening();
	}

	public boolean startBroadcasting(BroadcastingEndEventListener listener,
			int seconds) {
		try {
			this.beaconServer.startBroadcasting(seconds);
			this.beaconServer.addBroadcastingEndEventListener(listener);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean stopBroadcasting() {
		try {
			this.beaconServer.stopBroadcasting();
			return true;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean startListening(ClientFoundEventListener listener) {
		try {
			this.beaconClient.startListening();
			this.beaconClient.addClientFoundEventListener(listener);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean stopListening() {
		try {
			this.beaconClient.stopListening();
			return true;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean searchFile(String term) {
		return true;
	}

	public Response requestProfile(String address) {
		Request profileRequest = new Request(RequestIdentifier.GET_PROFILE,
				this.source.getProfile().getName());
		return this.transmissionClient.startConnection(address, profileRequest);
	}

	public Response requestSharedFiles(String address) {
		Request filesRequest = new Request(RequestIdentifier.GET_SHARED_FILES,
				this.source.getProfile().getName());
		return this.transmissionClient.startConnection(address, filesRequest);
	}

	public Response sendMessage(String address) {
		// TODO Auto-generated method stub
		return null;
	}

	public Response sendOffer(String address, SharedFile file) {
		Request fileOffer = new Request(RequestIdentifier.OFFER, this.source
				.getProfile().getName());
		fileOffer.setFileName(file.getName());
		String uuid = UUID.randomUUID().toString();
		fileOffer.setToken(uuid);
		Response response = this.transmissionClient.startConnection(address,
				fileOffer);
		this.sendRequests.put(uuid, file.getPath());
		return response;
	}

	public Response sendOfferResponse(String address, boolean accept,
			String filename, String token) {
		Request offerResponse = new Request(RequestIdentifier.RESPONSE,
				this.source.getProfile().getName());
		if (accept) {
			offerResponse.setAction(Action.ACCEPT);
			offerResponse.setToken(token);
			this.receiveRequests.put(token, filename);
		} else {
			offerResponse.setAction(Action.REJECT);
		}
		return this.transmissionClient.startConnection(address, offerResponse);
	}

	public Response sendSearchRequest(String address) {
		// TODO Auto-generated method stub
		return null;
	}

	public Response sendSearchResult(String address) {
		// TODO Auto-generated method stub
		return null;
	}

	public Response sendPushRequest(String address, String token,
			TransmissionProgressInterface listener) {
		Request pushResponse = new Request(RequestIdentifier.PUSH, this.source
				.getProfile().getName());
		String filename = this.sendRequests.get(token);
		File file = new File(filename);
		pushResponse.setFileSize(file.length());
		pushResponse.setFileName(file.getName());
		this.transmissionClient.setListener(listener);
		return this.transmissionClient.startConnection(address, pushResponse,
				filename);
	}

	public void saveSettings(String key, String value) {
		Settings sett = new Settings();
		sett.open();
		sett.setValue(key, value);
		sett.close();
	}
	
	public String retrieveSettings(String key){
		Settings sett = new Settings();
		sett.open();
		String result = sett.getValue(key);
		sett.close();
		return result;
	}
}
