package com.gnaw.chord;

import java.io.Serializable;
import java.util.Set;

import javax.swing.JTextArea;

import de.uniba.wiai.lspi.chord.service.ChordCallback;
import de.uniba.wiai.lspi.chord.service.Key;

public class ChordCallbackImpl implements ChordCallback {

	private JTextArea textArea;
	
	public ChordCallbackImpl(JTextArea textArea){
		this.textArea = textArea;
	}
	
	public ChordCallbackImpl(){
	}
	
	public void retrieved(Key key, Set<Serializable> entries, Throwable t) {
		if (t == null) {
			System.out.println("Successfully found: ");
			textArea.setText(null);
			for(Serializable i : entries){
				System.out.println(i);
				textArea.append(i.toString());
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