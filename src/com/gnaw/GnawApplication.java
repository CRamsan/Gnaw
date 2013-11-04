/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnaw;

import java.io.IOException;
import java.util.UUID;

import com.gnaw.discovery.BeaconClient;
import com.gnaw.discovery.BeaconServer;
import com.gnaw.discovery.event.BroadcastingEndEventListener;
import com.gnaw.discovery.event.ClientFoundEventListener;
import com.gnaw.interfaces.DataSourceInterface;
import com.gnaw.interfaces.GnawApplicationInterface;
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
public class GnawApplication extends GnawApplicationInterface {

	private BeaconServer beaconServer;
	private BeaconClient beaconClient;
	private TransmissionServer transmissionServer;
	private TransmissionClient transmissionClient;
	private DataSourceInterface source;

	public GnawApplication(DataSourceInterface source) {
		this.source = source;
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

	@Override
	public Response requestProfile(String address) {
		Request profileRequest = new Request(RequestIdentifier.GET_PROFILE,
				this.source.getProfile().getName());
		return this.transmissionClient.startConnection(address, profileRequest);
	}

	@Override
	public Response requestSharedFiles(String address) {
		Request filesRequest = new Request(RequestIdentifier.GET_SHARED_FILES,
				this.source.getProfile().getName());
		return this.transmissionClient.startConnection(address, filesRequest);
	}

	@Override
	public Response sendMessage(String address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response sendOffer(String address, SharedFile file) {
		Request fileOffer = new Request(RequestIdentifier.OFFER, this.source
				.getProfile().getName());
		fileOffer.setFileName(file.getName());
		return this.transmissionClient.startConnection(address, fileOffer);
	}

	@Override
	public Response sendOfferResponse(String address, boolean accept) {
		Request offerResponse = new Request(RequestIdentifier.RESPONSE,
				this.source.getProfile().getName());
		if (accept) {
			offerResponse.setAction(Action.ACCEPT);
			offerResponse.setToken(UUID.randomUUID().toString());
		} else {
			offerResponse.setAction(Action.REJECT);
		}
		return this.transmissionClient.startConnection(address, offerResponse);
	}

	@Override
	public Response sendSearchRequest(String address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response sendSearchResult(String address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendFile(String address, String filePath) {
		// TODO Auto-generated method stub		
	}
}
