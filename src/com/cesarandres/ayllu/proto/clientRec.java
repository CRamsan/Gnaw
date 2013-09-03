package com.cesarandres.ayllu.proto;

import java.io.IOException;

import com.cesarandres.ayllu.discovery.Broadcaster;

public class clientRec {
	public static void main(String[] args) throws IOException {
		Broadcaster broad = new Broadcaster();
		broad.start();
	}
}
