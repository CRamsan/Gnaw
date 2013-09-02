package com.cesarandres.ayllu.discovery;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Broadcaster {

	private boolean isRunning = false;
	private Thread worker;

	public void start() {
		this.worker = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					DatagramSocket socket = new DatagramSocket();
					socket.setBroadcast(true);
					byte[] b = new byte[100];
					DatagramPacket p = new DatagramPacket(b, b.length);
					p.setAddress(InetAddress.getByAddress(new byte[] {
							(byte) 255, (byte) 255, (byte) 255, (byte) 255 }));
					p.setPort(80);

					int i = 0;
					while (isRunning) {
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
		});
		this.worker.start();
	}

	public void stop() throws InterruptedException {
		this.isRunning = false;
		this.worker.join();
	}
}
