package com.gnaw.chord;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;

import de.uniba.wiai.lspi.chord.service.Key;

/**
 * Key for a file. Corresponds to the md5 hash of the file.
 * @author Josh Tan
 *
 */
public class FileHashKey implements Key {

	private byte[] fileHash;

	public FileHashKey(String filename) throws IOException {

		FileInputStream fis = new FileInputStream(new File(filename));
		fileHash = DigestUtils.md5Hex(fis).getBytes();
		fis.close();

	}

	public byte[] getBytes() {
		return fileHash;
	}

	public int hashCode() {
		return fileHash.hashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof FileHashKey)
			return Arrays.equals(((FileHashKey) obj).fileHash, fileHash);
		return false;
	}
}