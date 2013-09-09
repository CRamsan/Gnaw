package com.cesarandres.ayllu.discovery;

import java.io.IOException;

public class BeaconClient {

    private static BeaconClientThread listeningThread;

    public BeaconClient() {
    }

    public void startListening() throws IOException {
        BeaconClient.listeningThread = new BeaconClientThread();
        BeaconClient.listeningThread.start();
    }

    public void stopListening() throws InterruptedException {
        BeaconClient.listeningThread.stopSignal();
        BeaconClient.listeningThread.join();
    }

    public void addClientFoundEventListener(ClientFoundEventListener listener) {
        BeaconClient.listeningThread.addClientFoundEventListener(listener);
    }

    public void removeClientFoundEventListener(ClientFoundEventListener listener) {
        BeaconClient.listeningThread.removeClientFoundEventListener(listener);
    }
}