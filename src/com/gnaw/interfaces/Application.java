package com.gnaw.interfaces;

import com.gnaw.Profile;

public interface Application {
	public Profile getProfile();

	public boolean deliverMessage();
	
	public boolean deliverOffer();
	
	public boolean deliverOfferResponse();
	
	public boolean deliverSearchRequest();
}
