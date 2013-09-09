/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesarandres.ayllu.discovery;

import java.util.EventListener;

/**
 *
 * @author cesar
 */
public interface ClientFoundEventListener extends EventListener {

    public void ClientFoundEventOccurred(ClientFoundEvent evt);
}
