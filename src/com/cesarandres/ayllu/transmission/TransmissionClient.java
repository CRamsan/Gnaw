/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesarandres.ayllu.transmission;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cesar
 */
public class TransmissionClient {

    private static Socket kkSocket;
    private static PrintWriter out;
    private static BufferedReader in;
    private static TransmissionClientConnectionThread transmissionThread;

    public TransmissionClient() {
    }

    public void startConnection(String address, String data) {
        TransmissionClient.transmissionThread = new TransmissionClientConnectionThread(address, data);
        TransmissionClient.transmissionThread.start();
    }

    public void stopConnection() {
        TransmissionClient.transmissionThread.stopConnection();
    }

    public class TransmissionClientConnectionThread extends Thread {

        private String data;

        public TransmissionClientConnectionThread(String address, String data) {
            super("KKMultiServerThread");
            this.data = data;
            try {
                TransmissionClient.kkSocket = new Socket(address, 4444);
                TransmissionClient.out = new PrintWriter(kkSocket.getOutputStream(), true);
                TransmissionClient.in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
            } catch (UnknownHostException ex) {
                Logger.getLogger(TransmissionClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(TransmissionClient.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        @Override
        public void run() {
            out.println(this.data);
            TransmissionClient.out.close();
            try {
                TransmissionClient.kkSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(TransmissionClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public void stopConnection() {
            try {
                TransmissionClient.out.close();
                TransmissionClient.kkSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(TransmissionClient.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
