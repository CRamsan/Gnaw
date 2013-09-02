package com.cesarandres.ayllu.discovery;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Receiver {
	public void listen(){
		 byte[] receiveData = new byte[100];
		   DatagramSocket clientSocket = new DatagramSocket(1234);
		  DatagramPacket receivePacket =
		          new DatagramPacket(receiveData,
		                       receiveData.length);
		  
		       clientSocket.receive(receivePacket);
		       System.out.println(receivePacket.getAddress());
	}
}
