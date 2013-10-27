package com.gnaw.interfaces;

import com.gnaw.Profile;
import com.gnaw.models.File;

/**
 * @author cesar
 * 
 *         This interface should be implemented by the class that will receive
 *         calls from @GnawApplication. As long as this interface is implemented
 *         correctly, it does not matter if the application is a GUI, CLI or a
 *         daemon. There are two main kinds of methods that a class implementing
 *         this interface should be aware.
 * 
 *         GET methods are request for information that is locally available.
 *         When a the application receives a GET request, the information should
 *         be inmediatly returned on the response.
 * 
 *         POST methods are used to communicate with remote clients and send
 *         information to them. One kind of POST methods is the message request,
 *         where the client will post a message to another client. The client
 *         will respond if the delivery was successful. Another kind of POST
 *         requests is when an action is send to another client, such as
 *         searching for a file or pushing a file. This methods will respond
 *         with the information of the delivery of the request, later another
 *         POST request can be made with the result of the request.
 */
public interface DataSourceInterface {

	/**
	 * This method should return information inmediatly. The information should
	 * be already available(either on disk or RAM) for this method to retrieve
	 * it. Operations such as network calls should not be used.
	 * 
	 * @return a Profile object containing the information requested
	 */
	public Profile getProfile();

	/**
	 * This method should return information inmediatly. The information should
	 * be already available(either on disk or RAM) for this method to retrieve
	 * it. Operations as traversing a directory should not be used. Instead it
	 * is better to have a cache always ready with a list of all the files.
	 * 
	 * @return a File object containing the structure of the shared folder.
	 */
	public File getSharedFiles();

	/**
	 * This method should handle a message received. If the application can not
	 * handle messages or there is any other problem then return false. If the
	 * message was delivered successfully to the appropriate class to handle it,
	 * true should be returned.
	 * 
	 * @return True if the message was received and passed to the next layer.
	 *         False otherwise.
	 */
	public boolean postMessage();

	/**
	 * This method should handle an incoming file offer. If the application can
	 * not handle receiving messages or there is any other problem then return
	 * false. If the offer was delivered successfully to the appropriate class
	 * to handle it, true should be returned.
	 * 
	 * @return True if the offer was received and passed to the next layer.
	 *         False otherwise.
	 */
	public boolean postOffer();

	/**
	 * This method should handle an incoming file offerResponse. If the
	 * application can not handle receiving messages or there is any other
	 * problem then return false. If the offerResponse was delivered
	 * successfully to the appropriate class to handle it, true should be
	 * returned.
	 * 
	 * @return True if the offerResponse was received and passed to the next
	 *         layer. False otherwise.
	 */
	public boolean postOfferResponse();

	/**
	 * This method should handle an incoming file search request . All
	 * applications have to handle this kind of requests. If there is any
	 * problem then return false. If the search request was delivered
	 * successfully to the appropriate class to handle it, true should be
	 * returned.
	 * 
	 * @return True if the search request was received and passed to the next
	 *         layer. False otherwise.
	 */
	public boolean postSearchRequest();

	/**
	 * This method should handle an incoming file search result. All
	 * applications have to handle this kind of requests. If there is any
	 * problem then return false. If the search result was delivered
	 * successfully to the appropriate class to handle it, true should be
	 * returned.
	 * 
	 * @return True if the search result was received and passed to the next
	 *         layer. False otherwise.
	 */
	public boolean postSearchResult();

	public boolean deliverMessage();

	public boolean deliverOffer();

	public boolean deliverOfferResponse();

	public boolean deliverSearchRequest();
}
