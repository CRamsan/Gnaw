/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesarandres.ayllu.discovery;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cesar
 */
class BeaconClientThread extends Thread implements ClientFoundEventSource {

    protected MulticastSocket socket;
    protected InetAddress group;
    protected boolean runFlag = false;
    protected int timeToLive = -1;
    protected ClientFoundEventListener listener;

    public BeaconClientThread() throws IOException {
        this("BeaconServerThread", 30);
    }

    public BeaconClientThread(String name, int seconds) throws IOException {
        super(name);
        this.socket = new MulticastSocket(4446);
        this.group = InetAddress.getByName("224.0.113.0");
    }

    public void setListener(ClientFoundEventListener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        int counter = timeToLive;
        byte[] buf;
        try {
            this.socket.joinGroup(group);
        } catch (IOException ex) {
            Logger.getLogger(BeaconClientThread.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        DatagramPacket packet;
        this.runFlag = true;
        while (runFlag) {
            try {
                buf = new byte[256];
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                String received = new String(packet.getData());
                fireClientFoundEvent(new ClientFoundEvent(received));

                counter--;
            } catch (IOException ex) {
                Logger.getLogger(BeaconClientThread.class.getName()).log(Level.SEVERE, null, ex);
                runFlag = false;
            }

        }
        try {
            this.socket.leaveGroup(group);
        } catch (IOException ex) {
            Logger.getLogger(BeaconClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.socket.close();
    }

    public void stopSignal() {
        this.runFlag = false;
        try {
            this.socket.leaveGroup(group);
        } catch (IOException ex) {
            Logger.getLogger(BeaconClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.socket.close();
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
}
