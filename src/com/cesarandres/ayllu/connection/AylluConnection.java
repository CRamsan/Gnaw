package com.cesarandres.ayllu.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AylluConnection extends Thread {
	private Socket socket = null;
	private PrintWriter out = null;
	private BufferedReader in = null;
	private boolean isServer;
	private boolean stop = false;
	private int sessionID;
	private int userID;
	private String auth;

	public AylluConnection(Socket socket, boolean isServer) {
		super();
		this.socket = socket;
		this.isServer = isServer;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		} catch (IOException e) {
			System.err.println("Error establishing in/out streams");
		}
	}

	public void run() {
		try {
			System.out.println("Thread started");
			String inputLine;
			StringBuffer buff;
			while ((inputLine = in.readLine()) != null && !stop) {
				System.out.println("Message recieved");
				taskSource.fireTaskReceivedEvent(new TaskReceivedEvent(task, this));
			}
			System.out.println("Thread for client stopped correctly");
		} catch (IOException e) {
			System.err.println("Thread for client stopped with an exception");
		}
		taskSource.fireTaskReceivedEvent(new TaskReceivedEvent(new LeaveSessionTask(userID, "", sessionID, auth), this));
	}

	public void sendTask(Task task) {
		String message = TaskMessageFactory.fromTaskToMessage(task).getMessageString();
		sendData(message);
		System.out.println("Task sent");
	}

	public void sendTaskResponse(TaskResponse task) {
		String message = TaskMessageFactory.fromTaskResponseToMessage(task).getMessageString();
		sendData(message);
		System.out.println("TaskResponse sent");
	}

	private void sendData(String message) {
		out.println(message);
	}

	public void close() {
		try {
			out.close();
			in.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isStop() {
		return stop;
	}


	public void stopThread() {
		this.stop = true;
	}
}