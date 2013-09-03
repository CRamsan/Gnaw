package com.cesarandres.ayllu.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.spi.CurrencyNameProvider;

public class SenderNetwork  {

	public static final int SOCKET_NUMBER = 4444;				// The socket number to listen on
	private ServerSocket serverSocket;							// The server socket for connect handling
	private ArrayList<AylluConnection> clientList;				// The arrayList of clients
	private boolean shouldStop; 								// whether the server should be stopped or not

	public SenderNetwork() {
		shouldStop = false;
		clientList = new ArrayList<AylluConnection>();
	}

	public void startListening() {

		try {
			System.out.println("Trying to open socket: " + SOCKET_NUMBER);
			serverSocket = new ServerSocket(SOCKET_NUMBER);
		} catch (IOException e) {
			System.err.println("Could not listen on port: " + SOCKET_NUMBER);
			System.err.println("Exiting.");
			System.exit(-1);
		}

		System.out.println("Starting to listen for clients");
		int id = 0;

		System.out.println("Starting clients pool");
		while (!shouldStop) {
			// listen for threads
			try {
				AylluConnection thread = new AylluConnection(serverSocket.accept(),
						id, this, true);
				clientList.add(thread);
				thread.start();
				System.out.println("Client connected.");
				id++;
			} catch (IOException e) {
				System.err.println("Error while connecting client.");
			}
		}

		// shut down server
		try {
			serverSocket.close();
		} catch (IOException e) {
			System.err.println("Error while closing server socket");
		}

		// close client connections
		System.out.println("Closing connections.");
		for (int i = 0; i < clientList.size(); i++) {
			clientList.get(i).close();
		}

		// wait for client threads to die
		for (int i = 0; i < clientList.size(); i++) {
			try {
				clientList.get(i).join();
				System.out.println("Thread " + i + " closed.");
			} catch (InterruptedException e) {
				System.err.println("Error while closing thread " + i);
			}
		}
	}

	public void stopServer() {
		System.out.println("Closing network");
		shouldStop = true;
	}

	public void stopClient(int id) {
		System.out.println("Closing connection with client " + id);
		clientList.get(id).close();
	}

	public void sendTaskResponseToClient(int id, TaskResponse task) {
		clientList.get(id).sendTaskResponse(task);
	}

	public void sendTaskResponseToAllClients(TaskResponse task) {
		for(CNPConnection client : clientList){
			client.sendTaskResponse(task);			
		}
	}
}