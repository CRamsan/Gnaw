/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnaw;

import com.gnaw.discovery.BeaconClient;
import com.gnaw.discovery.BeaconServer;
import com.gnaw.gui.QuickSend;
import com.gnaw.transmission.TransmissionClient;
import com.gnaw.transmission.TransmissionServer;

/**
 * s/Ayllu/Gnaw/g
 * 
 * @author cesar
 */
public class AylluApplication {

	private static BeaconServer beaconServer;
	private static BeaconClient beaconClient;
	private static TransmissionServer transmissionServer;
	private static TransmissionClient transmissionClient;

	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed"
		// desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(QuickSend.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(QuickSend.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(QuickSend.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(QuickSend.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		AylluApplication.beaconServer = new BeaconServer();
		AylluApplication.beaconClient = new BeaconClient();
		AylluApplication.transmissionClient = new TransmissionClient();
		AylluApplication.transmissionServer = new TransmissionServer(this);

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new QuickSend(beaconClient, beaconServer, transmissionClient,
						transmissionServer).setVisible(true);
			}
		});
	}
}
