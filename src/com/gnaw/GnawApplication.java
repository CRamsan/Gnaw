/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnaw;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.UUID;

import javax.xml.ws.soap.AddressingFeature.Responses;

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
		this.sendRequests = new HashMap<>();
		this.receiveRequests = new HashMap<>();
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

	public Response sendPushRequest(String address, String token) {
		Request pushResponse = new Request(RequestIdentifier.PUSH, this.source
				.getProfile().getName());
		String filename = this.sendRequests.get(token);
		File file = new File(filename);
		pushResponse.setFileSize(file.length());
		return this.transmissionClient.startConnection(address, pushResponse);
	}

	public void sendFile(String address, String token,
			TransmissionProgressInterface listener) {
		Socket socket = null;

		try {
			socket = new Socket(address, 4444);
			String filename = this.sendRequests.get(token);
			File file = new File(filename);

			long length = file.length();
			byte[] bytes = new byte[(int) length];

			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			BufferedOutputStream out = new BufferedOutputStream(
					socket.getOutputStream());

			int count, i = 0;

			while ((count = bis.read(bytes)) > 0) {
				out.write(bytes, 0, count);
				i++;
				listener.setProgress((int) (100 * i / length));
			}

			out.flush();
			out.close();
			fis.close();
			bis.close();
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
