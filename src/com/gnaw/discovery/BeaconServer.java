package com.gnaw.discovery;

import com.gnaw.discovery.event.BroadcastingEndEventListener;
import com.gnaw.discovery.event.BroadcastingEndEventSource;
import java.io.IOException;
import java.util.ArrayList;

public class BeaconServer implements BroadcastingEndEventSource {

	private static BeaconServerThread broadcastingThread;
	protected ArrayList<BroadcastingEndEventListener> listeners;

	public BeaconServer() {
		this.listeners = new ArrayList<>();
	}

	public void startBroadcasting() throws IOException {
		BeaconServer.broadcastingThread = new BeaconServerThread();
		BeaconServer.broadcastingThread.setListener(listeners);
		BeaconServer.broadcastingThread.start();
	}

	public void startBroadcasting(int value) throws IOException {
		BeaconServer.broadcastingThread = new BeaconServerThread(value);
		BeaconServer.broadcastingThread.setListener(listeners);
		BeaconServer.broadcastingThread.start();
	}

	public void stopBroadcasting() throws InterruptedException {
		BeaconServer.broadcastingThread.stopSignal();
		BeaconServer.broadcastingThread.join();
	}

	@Override
	public void addBroadcastingEndEventListener(
			BroadcastingEndEventListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public void removeBroadcastingEndEventListener(
			BroadcastingEndEventListener listener) {
		this.listeners.remove(listener);
	}
}
