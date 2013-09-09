/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesarandres.ayllu.transmission;

import com.cesarandres.ayllu.discovery.BeaconServer;
import com.cesarandres.ayllu.discovery.BeaconServerThread;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cesar
 */
public class TransmissionServer {

    private static TransmissionServerListeningThread listeningThread;
    private static ServerSocket serverSocket;

    public TransmissionServer() {
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
                        new TransmissionServerThread(socket).start();
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(TransmissionServer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        public void stopListening() {
            this.listening = false;
        }

        public void closeConnections() {
            try {
                TransmissionServer.serverSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(TransmissionServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
