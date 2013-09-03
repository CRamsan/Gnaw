package com.cesarandres.ayllu.proto;

import java.io.IOException;

import com.cesarandres.ayllu.discovery.Listener;
import com.cesarandres.ayllu.discovery.events.ClientFoundEvent;
import com.cesarandres.ayllu.discovery.events.ClientFoundEventListener;

public class clientSend {
	public static void main(String[] args) throws IOException {
		Listener list = new Listener();
		list.start();
	}
}
