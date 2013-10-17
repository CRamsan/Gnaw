/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesarandres.ayllu.transmission;

import java.awt.image.RescaleOp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.gnaw.response.Request;
import com.gnaw.response.Response;
import com.google.gson.Gson;

/**
 *
 * @author cesar
 */
public class TransmissionServerThread extends Thread {

    private Socket socket = null;
    private static final Gson gson = new Gson();
    
    public TransmissionServerThread(Socket socket) {
        super("KKMultiServerThread");
        this.socket = socket;
    }

    public void run() {

        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                    socket.getInputStream()));

            String inputLine, outputLine = null;
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                Request request = gson.fromJson(inputLine, Request.class);
                out.print(executeRequest(request));
            }
            out.close();
            in.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private Response executeRequest(Request request){
    	return new Response();
    }
}
