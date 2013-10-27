package com.gnaw.models;

import java.io.File;
import java.util.ArrayList;

public class SharedFile {
	private int size;
	private String name;
	private boolean isFolder;
	private ArrayList<SharedFile> sharedFiles;

	public SharedFile() {
		this.name = "";
		this.sharedFiles = new ArrayList<SharedFile>(0);
	}

	public SharedFile(File file) {
		this.name = file.getName();
		if (file.isDirectory()) {
			this.isFolder = true;
			this.sharedFiles = new ArrayList<SharedFile>();
			for (File childFile : file.listFiles()) {
				this.sharedFiles.add(new SharedFile(childFile));
			}
		} else {
			this.isFolder = false;
		}
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isFolder() {
		return isFolder;
	}

	public void setFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}

	public ArrayList<SharedFile> getSharedFiles() {
		return sharedFiles;
	}

	public void setSharedFiles(ArrayList<SharedFile> sharedFiles) {
		this.sharedFiles = sharedFiles;
	}

	public static SharedFile load(String path) {
		File root = new File(path);
		if (!root.exists()) {
			if (root.mkdirs()) {
				return null;
			}
		}

		SharedFile file = new SharedFile(root);
		return file;
	}
}
