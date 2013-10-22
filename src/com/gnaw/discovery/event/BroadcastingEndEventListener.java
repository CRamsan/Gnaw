/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnaw.discovery.event;

import java.util.EventListener;

/**
 *
 * @author cesar
 */
public interface BroadcastingEndEventListener extends EventListener {

    public void BroadcastingEndEventOccurred(BroadcastingEndEvent evt);
}
