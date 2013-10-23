/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnaw.transmission;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gnaw.interfaces.DataSourceInterface;

/**
 * 
 * @author cesar
 */
public class TransmissionServer {

	private static TransmissionServerListeningThread listeningThread;
	private static ServerSocket serverSocket;
	private DataSourceInterface application;

	public TransmissionServer(DataSourceInterface application) {
		this.application = application;
	}

	public void startListening() {
		TransmissionServer.listeningThread = new TransmissionServerListeningThread();
		TransmissionServer.listeningThread.start();
	}

	public void stopListening() {
		TransmissionServer.listeningThread.stopListening();
	}

	public void closeConnections() throws InterruptedException {
		TransmissionServer.listeningThread.closeConnections();
		TransmissionServer.listeningThread.join();
	}

	public class TransmissionServerListeningThread extends Thread {

		private boolean listening;

		public TransmissionServerListeningThread() {
			super("KKMultiServerThread");
		}

		@Override
		public void run() {
			this.listening = true;
			try {
				TransmissionServer.serverSocket = new ServerSocket(4444);
				Socket socket;
				while (listening) {
					socket = serverSocket.accept();
					if (listening) {
						new TransmissionServerThread(socket, application)
								.start();
					}
				}
			} catch (IOException ex) {
				Logger.getLogger(TransmissionServer.class.getName()).log(
						Level.SEVERE, null, ex);
			}

		}

		public void stopListening() {
			this.listening = false;
		}

		public void closeConnections() {
			try {
				TransmissionServer.serverSocket.close();
			} catch (IOException ex) {
				Logger.getLogger(TransmissionServer.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		}
	}
}
