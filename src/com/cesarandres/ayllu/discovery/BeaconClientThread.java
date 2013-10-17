/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesarandres.ayllu.discovery;

import com.cesarandres.ayllu.discovery.event.ClientFoundEventListener;
import com.cesarandres.ayllu.discovery.event.ClientFoundEvent;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author cesar
 */
public class BeaconClientThread extends Thread {

	protected MulticastSocket socket;
	protected InetAddress group;
	protected boolean runFlag = false;
	protected int timeToLive = -1;
	protected ArrayList<ClientFoundEventListener> listeners;
	protected Hashtable<String, String> clientsFound;

	public BeaconClientThread() throws IOException {
		this("BeaconServerThread", 30);
	}

	public BeaconClientThread(String name, int seconds) throws IOException {
		super(name);
		this.socket = new MulticastSocket(4446);
		this.group = InetAddress.getByName("224.0.113.0");
		this.clientsFound = new Hashtable<>();
	}

	public void setListener(ArrayList<ClientFoundEventListener> listeners) {
		this.listeners = listeners;
	}

	@Override
	public void run() {
		int counter = timeToLive;
		byte[] buf;
		try {
			this.socket.joinGroup(group);
		} catch (IOException ex) {
			Logger.getLogger(BeaconClientThread.class.getName()).log(
					Level.SEVERE, null, ex);
			return;
		}
		DatagramPacket packet;
		this.runFlag = true;
		while (runFlag) {
			try {
				buf = new byte[256];
				packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				counter--;
				if (this.clientsFound.containsKey(packet.getAddress()
						.getHostAddress())) {
					continue;
				}
				String received = new String(packet.getData());
				this.clientsFound.put(packet.getAddress().getHostAddress(),
						received);
				System.out.println("Client found at: "
						+ packet.getAddress().getHostAddress());
				fireClientFoundEvent(new ClientFoundEvent(packet.getAddress()
						.getHostAddress()));
			} catch (IOException ex) {
				Logger.getLogger(BeaconClientThread.class.getName()).log(
						Level.SEVERE, null, ex);
				runFlag = false;
			}

		}
		try {
			this.socket.leaveGroup(group);
		} catch (IOException ex) {
			Logger.getLogger(BeaconClientThread.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		this.socket.close();
	}

	public void stopSignal() {
		this.runFlag = false;
		try {
			this.socket.leaveGroup(group);
		} catch (IOException ex) {
			Logger.getLogger(BeaconClientThread.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		this.socket.close();
	}

	private void fireClientFoundEvent(ClientFoundEvent evt) {
		for (ClientFoundEventListener listener : listeners) {
			listener.ClientFoundEventOccurred(evt);
		}
	}
}
