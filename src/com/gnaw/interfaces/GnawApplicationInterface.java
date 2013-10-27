package com.gnaw.interfaces;

import com.gnaw.response.Response;

/**
 * @author cesar
 * 
 *         This is an abstract class that will be inherited by @GnawApplication
 *         or any other class that will handle the network calls. The purpose of
 *         this class is to provide the common set of calls required by the
 *         layer that interfaces with the user. This class, similarly to
 *         @DataSourceInterface, has two kinds of methods.
 * 
 *         REQUEST methods are send to a remote client to request for
 *         information that should be readily available. When a the application
 *         receives a GET request, the information will be immediately returned
 *         on the response.
 * 
 *         SEND methods are used to communicate with remote clients and ask them
 *         for information or request actions that are not immediate. The
 *         request will be made to request some response to actions like
 *         accepting an incoming file or searching for a file in the network.
 *         The remote client will respond with success if the action requested
 *         was received successfully. The remote client or some other client may
 *         send another SEND request at any point in time with the result of the
 *         requested action.
 */
public abstract class GnawApplicationInterface {

	/**
	 * This method will post a request for profile information to a remote client. 
	 * The remote client will respond immediately. 
	 * 
	 * @return a Response object with the profile requested
	 */
	public abstract Response requestProfile(String address);

	/**
	 * This method will post a request for the shared files of a remote client. 
	 * The remote client will respond immediately. 
	 * 
	 * @return a Response object with the shared files requested
	 */
	public abstract Response requestSharedFiles(String address);

	/**
	 * This method will send a message to a remote client. The client will respond 
	 * immediately with a response about the delivery of the message.
	 * 
	 * @return Response with the information about the delivery of the message.
	 */
	public abstract Response sendMessage(String address);

	/**
	 * This method will send a file offer to a remote client. The client will respond
	 * immediately with a response about the delivery of the offer.
	 * 
	 * @return Response with the information about the delivery of the offer.
	 */
	public abstract Response sendOffer(String address);

	/**
	 * This method will send a response to a previous offer request to a remote client. 
	 * The client will respond immediately with a response about the delivery of the offer
	 * response.
	 * 
	 * @return Response with the information about the delivery of the offer response.
	 */
	public abstract Response sendOfferResponse(String address);

	/**
	 * This method will send a search request to a remote client. The client will respond 
	 * immediately with a response about the delivery of the request.
	 * 
	 * @return Response with the information about the delivery of the request.
	 */
	public abstract Response sendSearchRequest(String address);

	/**
	 * This method will send a message with the search results to a remote client that sent 
	 * a search request. The client will respond immediately with a response about the delivery
	 * of the message.
	 * 
	 * @return Response with the information about the delivery of the message.
	 */
	public abstract Response sendSearchResult(String address);
}
