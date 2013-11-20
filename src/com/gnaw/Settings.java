package com.gnaw;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Settings {

	private static String CONFIG_FILE = ".config";
	private HashMap<String, String> map;

	public void open() {
		map = new HashMap<String, String>();
		File config = new File(CONFIG_FILE);
		try {
			if (!config.exists()) {
				config.createNewFile();
			}
			String result = null;
			Scanner scan = new Scanner(config);
			String[] entry = null;
			while (scan.hasNext()) {
				entry = scan.nextLine().split("=");
				map.put(entry[0], entry[1]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void close() {
		File config = new File(CONFIG_FILE);
		try {
			if (!config.exists()) {
				config.createNewFile();
			}
			FileWriter fw = new FileWriter(config.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for (Entry<String, String> entry : map.entrySet()) {
				bw.write(entry.getKey() + "=" + entry.getValue() + "\n");
			}
			bw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setValue(String key, String value){
		map.put(key, value);
	}
	
	public String getValue(String key){
		if(map.containsKey(key)) {
			return map.get(key);
		}else{
			return null;
		}
	}
}
