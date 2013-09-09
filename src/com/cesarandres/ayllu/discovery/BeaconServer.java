package com.cesarandres.ayllu.discovery;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BeaconServer {

    private static BeaconServerThread broadcastingThread;

    public BeaconServer() {
    }

    public void startBroadcasting() throws IOException {
        BeaconServer.broadcastingThread = new BeaconServerThread();
        BeaconServer.broadcastingThread.start();
    }

    public void stopBroadcasting() throws InterruptedException {
        BeaconServer.broadcastingThread.stopSignal();
        BeaconServer.broadcastingThread.join();
    }
}
