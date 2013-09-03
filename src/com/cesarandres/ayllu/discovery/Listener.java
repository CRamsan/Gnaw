package com.cesarandres.ayllu.discovery;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

import com.cesarandres.ayllu.discovery.events.ClientFoundEvent;
import com.cesarandres.ayllu.discovery.events.ClientFoundEventListener;
import com.cesarandres.ayllu.discovery.events.ClientFoundEventSource;

public class Listener implements ClientFoundEventSource {

	private ArrayList<ClientFoundEventListener> listeners;
	private boolean stop = false;
	private Thread worker;

	public void start() {
		this.stop = false;
		this.worker = new Thread(new ListeningThread());
		this.worker.start();
	}

	public void stopAndWait() throws InterruptedException {
		this.stop = true;
		this.worker.join();
	}

	public void stop() {
		this.stop = true;
	}

	@Override
	public void addClientFoundEventListener(ClientFoundEventListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public void removeClientFoundEventListener(ClientFoundEventListener listener) {
		this.listeners.remove(listener);
	}

	@Override
	public void fireClientFoundEvent(ClientFoundEvent evt) {
		for (ClientFoundEventListener listener : listeners) {
			listener.ClientFoundEventOccurred(evt);
		}
	}

	public class ListeningThread implements Runnable {

		public void run() {
			try {
				byte[] receiveData = new byte[100];
				DatagramSocket clientSocket;
				clientSocket = new DatagramSocket(1234);
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

				while (!stop) {
					try {
						clientSocket.receive(receivePacket);
						System.out.println(receivePacket.getAddress());
						ClientFoundEvent event = new ClientFoundEvent(receivePacket.getAddress().toString());
						fireClientFoundEvent(event);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				clientSocket.close();
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
