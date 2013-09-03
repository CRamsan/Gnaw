package com.cesarandres.ayllu.discovery.events;

import java.util.EventObject;

public class ClientFoundEvent extends EventObject {

	private static final long serialVersionUID = -3570442799657796767L;
	private String clientAddress;

	public ClientFoundEvent(String address) {
		super(address);
		this.clientAddress = address;
	}

	public String getClientAddress() {
		return clientAddress;
	}
}