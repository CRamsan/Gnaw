/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesarandres.ayllu.discovery.event;

/**
 *
 * @author cesar
 */
public interface BroadcastingEndEventSource {

    public void addBroadcastingEndEventListener(
            BroadcastingEndEventListener listener);

    public void removeBroadcastingEndEventListener(
            BroadcastingEndEventListener listener);
}