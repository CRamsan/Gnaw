/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesarandres.ayllu.discovery.event;

import java.util.EventObject;

/**
 *
 * @author cesar
 */
public class ClientFoundEvent extends EventObject {

    private static final long serialVersionUID = 1L;

    public ClientFoundEvent(String data) {
        super(data);
    }
}
