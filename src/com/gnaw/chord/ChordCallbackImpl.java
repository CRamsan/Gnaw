package com.gnaw.chord;

import java.io.Serializable;
import java.util.Set;

import javax.swing.DefaultListModel;

import de.uniba.wiai.lspi.chord.service.ChordCallback;
import de.uniba.wiai.lspi.chord.service.Key;

public class ChordCallbackImpl implements ChordCallback {

	private DefaultListModel textArea;
	
	public ChordCallbackImpl(DefaultListModel textArea){
		this.textArea = textArea;
	}
	
	public ChordCallbackImpl(){
	}
	
	public void retrieved(Key key, Set<Serializable> entries, Throwable t) {
		if (t == null) {
			textArea.clear();
			if (!entries.isEmpty()) {
				System.out.println("Successfully found: ");
				for(Serializable i : entries){
					System.out.println(i);
					textArea.addElement(i.toString());
				}
			} else {
				System.out.println("Search results are empty.");
			}
		} else {
			System.err.println("Error while searching for " + key);
			t.printStackTrace();
		}
	}
	
	public void inserted(Key key, Serializable entry, Throwable t) {

		if (t == null) {
			System.out.println("Successfully inserted " + entry + " with key " + key);
		} else {
			System.err.println("Insertion of " + entry + " with key " + key + " failed !");                              
			t.printStackTrace();
		}
	}
	
	public void removed(Key key, Serializable entry, Throwable t) {
		
		if (t == null) {
			System.out.println("Successfully removed " + entry + " with key " + key);
		} else {
			System.err.println("Removal of " + entry + " with key "	+ key + " failed !");
			t.printStackTrace();
		}
	}
}