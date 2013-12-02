package com.gnaw.chord;

import java.io.Serializable;
import java.util.Set;

import de.uniba.wiai.lspi.chord.service.ChordCallback;
import de.uniba.wiai.lspi.chord.service.Key;

public class ChordCallbackImpl implements ChordCallback {
	

	public void retrieved(Key key, Set<Serializable> entries, Throwable t) {
		// TODO
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