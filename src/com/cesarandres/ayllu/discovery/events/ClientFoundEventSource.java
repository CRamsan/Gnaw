package com.cesarandres.ayllu.discovery.events;

public interface ClientFoundEventSource {

	public void addClientFoundEventListener(ClientFoundEventListener listener);

	public void removeClientFoundEventListener(ClientFoundEventListener listener);

	public void fireClientFoundEvent(ClientFoundEvent evt);
}