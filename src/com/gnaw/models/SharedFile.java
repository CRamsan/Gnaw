package com.gnaw.models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialException;
import javax.sql.rowset.serial.SerialJavaObject;

import org.apache.commons.io.FileUtils;

import com.gnaw.chord.ChordCallbackImpl;

import de.uniba.wiai.lspi.chord.console.command.entry.Key;
import de.uniba.wiai.lspi.chord.service.AsynChord;

public class SharedFile {
	private int size;
	private String name;
	private String path;
	private boolean isFolder;
	private ArrayList<SharedFile> sharedFiles;

	public SharedFile() {
		this.name = "";
		this.sharedFiles = new ArrayList<SharedFile>(0);
	}

	public SharedFile(File file) {
		this.name = file.getName();
		this.setPath(file.getAbsolutePath());
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

	public List<String> getSharedFilepaths() {

		List<String> sharedFilenames = new ArrayList<String>();
		File sharedFile = new File(path);
		
		if (sharedFile.isDirectory()) {
			for (File file : FileUtils.listFiles(sharedFile, null, true)) {
				sharedFilenames.add(file.getAbsolutePath());
			}

		} else {
			
			sharedFilenames.add(sharedFile.getAbsolutePath());
		}
		
		return sharedFilenames;

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

	@Override
	public String toString(){
		return getName();
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void insert(AsynChord chord){
		try {
			chord.insert(new Key(path + File.separator + name), new SerialJavaObject(name), new ChordCallbackImpl());
		} catch (SerialException e) {
			e.printStackTrace();
		}
		for(SharedFile file : this.sharedFiles){
			file.insert(chord);
		}
	}
	
	public void remove(AsynChord chord){
		try {
			chord.remove(new Key(path + File.separator + name), new SerialJavaObject(name), new ChordCallbackImpl());
		} catch (SerialException e) {
			e.printStackTrace();
		}
		for(SharedFile file : this.sharedFiles){
			file.remove(chord);
		}
	}
	
}
