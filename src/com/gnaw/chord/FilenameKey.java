package com.gnaw.chord;

import de.uniba.wiai.lspi.chord.service.Key;

/**
 * Key for a file. Corresponds to the md5 hash of the file.
 * @author Josh Tan
 *
 */
public class FilenameKey implements Key {

	private String filename;

	public FilenameKey(String theString) {
		filename = theString;
	}

	public byte[] getBytes() {
		return filename.getBytes();
	}

	public int hashCode() {
		return filename.hashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof FilenameKey)
			return ((FilenameKey)obj).filename.equals(filename);
		return false;
	}
}