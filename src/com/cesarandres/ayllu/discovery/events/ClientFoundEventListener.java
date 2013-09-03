package com.cesarandres.ayllu.discovery.events;

import java.util.EventListener;

public interface ClientFoundEventListener extends EventListener {

	public void ClientFoundEventOccurred(ClientFoundEvent evt);
}