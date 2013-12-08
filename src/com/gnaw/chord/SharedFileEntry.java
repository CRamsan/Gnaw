package com.gnaw.chord;

import java.io.Serializable;

public class SharedFileEntry implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String filename;
	private String ownerHost;
	private int ownerPort;

	public SharedFileEntry(String filename, String ownerHost, int ownerPort) {
		this.filename = filename;
		this.ownerHost = ownerHost;
		this.ownerPort = ownerPort;
	}

	@Override
	public String toString() {
		return filename + "(" + ownerHost + ":" + ownerPort + ")";
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getOwnerHost() {
		return ownerHost;
	}

	public void setOwnerHost(String ownerHost) {
		this.ownerHost = ownerHost;
	}

	public int getOwnerPort() {
		return ownerPort;
	}

	public void setOwnerPort(int ownerPort) {
		this.ownerPort = ownerPort;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((filename == null) ? 0 : filename.hashCode());
		result = prime * result
				+ ((ownerHost == null) ? 0 : ownerHost.hashCode());
		result = prime * result + ownerPort;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SharedFileEntry other = (SharedFileEntry) obj;
		if (filename == null) {
			if (other.filename != null)
				return false;
		} else if (!filename.equals(other.filename))
			return false;
		if (ownerHost == null) {
			if (other.ownerHost != null)
				return false;
		} else if (!ownerHost.equals(other.ownerHost))
			return false;
		if (ownerPort != other.ownerPort)
			return false;
		return true;
	}


}