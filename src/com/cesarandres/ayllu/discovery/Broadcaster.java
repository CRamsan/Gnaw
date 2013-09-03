package com.cesarandres.ayllu.discovery;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Broadcaster {

	private boolean stop = false;
	private Thread worker;

	public void start() {
		this.stop = false;
		this.worker = new Thread(new BroadcastingThread());
		this.worker.start();
	}

	public void stopAndWait() throws InterruptedException {
		this.stop = true;
		this.worker.join();
	}

	public void stop() {
		this.stop = true;
	}
	
	public class BroadcastingThread implements Runnable {

		public void run() {
			try {
				DatagramSocket socket = new DatagramSocket();
				socket.setBroadcast(true);
				byte[] b = new byte[100];
				DatagramPacket p = new DatagramPacket(b, b.length);
				p.setAddress(InetAddress.getByAddress(new byte[] { (byte) 255,
						(byte) 255, (byte) 255, (byte) 255 }));
				p.setPort(80);

				int i = 0;
				while (!stop) {
					String s = new Integer(i++).toString();

					b = s.getBytes();
					p.setData(b);
					socket.send(p);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				socket.close();
			} catch (IOException e) {

			}
		}
	}

}
