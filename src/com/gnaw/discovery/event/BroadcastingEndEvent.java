/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnaw.discovery.event;

import java.util.EventObject;

/**
 *
 * @author cesar
 */
public class BroadcastingEndEvent extends EventObject {

    private static final long serialVersionUID = 1L;

    public BroadcastingEndEvent(String data) {
        super(data);
    }
}
