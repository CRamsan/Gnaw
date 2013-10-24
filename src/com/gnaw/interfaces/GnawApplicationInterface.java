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
 *         receives a GET request, the information will be inmediatly returned
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

	public abstract Response requestProfile();

	public abstract Response requestSharedFiles();

	public abstract Response sendMessage();

	public abstract Response sendOffer();

	public abstract Response sendOfferResponse();

	public abstract Response sendSearchRequest();

	public abstract Response sendSearchResult();
}
