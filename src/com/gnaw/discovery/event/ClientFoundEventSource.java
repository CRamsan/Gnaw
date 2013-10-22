/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnaw.discovery.event;

/**
 *
 * @author cesar
 */
public interface ClientFoundEventSource {

    public void addClientFoundEventListener(
            ClientFoundEventListener listener);

    public void removeClientFoundEventListener(
            ClientFoundEventListener listener);
}