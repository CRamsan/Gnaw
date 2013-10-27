/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gnaw.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.gnaw.GnawApplication;
import com.gnaw.Profile;
import com.gnaw.discovery.event.BroadcastingEndEvent;
import com.gnaw.discovery.event.BroadcastingEndEventListener;
import com.gnaw.discovery.event.ClientFoundEvent;
import com.gnaw.discovery.event.ClientFoundEventListener;
import com.gnaw.interfaces.DataSourceInterface;
import com.gnaw.models.SharedFile;

/**
 * 
 * @author cesar
 */
public class MainGui extends JFrame implements DataSourceInterface,
		ClientFoundEventListener, BroadcastingEndEventListener {

	private GnawApplication application;
	private SharedFile sharedFiles = new SharedFile();

	/**
	 * Creates new form Main
	 */
	public MainGui() {
		initComponents();
	}

	private void initComponents() {

		jTabbedPane1 = new JTabbedPane();
		jPanel1 = new JPanel();
		jLabel5 = new JLabel();
		jTextField3 = new JTextField();
		jButton1 = new JButton();
		jButton1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				application.searchFile(jTextField3.getText());
			}
		});
		jScrollPane1 = new JScrollPane();
		jTextArea1 = new JTextArea();
		jSeparator4 = new JSeparator();
		jLabel6 = new JLabel();
		jScrollPane2 = new JScrollPane();
		jPanel2 = new JPanel();
		jLabel1 = new JLabel();
		jSlider1 = new JSlider();
		jSlider1.setMaximum(5);
		jSlider1.setPaintLabels(true);
		jSlider1.setValue(0);
		jSlider1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				String text = "Error";
				switch (jSlider1.getValue()) {
				case 0:
					text = "10  Seconds";
					break;
				case 1:
					text = "30 Seconds";
					break;
				case 2:
					text = "1 Minute";
					break;
				case 3:
					text = "5 Minutes";
					break;
				case 4:
					text = "30 Minutes";
					break;
				case 5:
					text = "Always ON";
					break;
				}
				lbDiscoveryTime.setText(text);
			}
		});
		jSeparator1 = new JSeparator();
		jLabel2 = new JLabel();
		jTextField1 = new JTextField();
		jTextField1.setText(System.getProperty("user.home") + File.separator
				+ "Gnaw");
		jButton2 = new JButton();
		jToggleButton1 = new JToggleButton();
		jToggleButton1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (jToggleButton1.isSelected()) {
					int seconds = 1;
					switch (jSlider1.getValue()) {
					case 0:
						seconds = 10;
						break;
					case 1:
						seconds = 30;
						break;
					case 2:
						seconds = 60;
						break;
					case 3:
						seconds = 300;
						break;
					case 4:
						seconds = 1800;
						break;
					case 5:
						seconds = -1;
						break;
					}
					application.startBroadcasting(MainGui.this, seconds);
				} else {
					application.stopBroadcasting();
				}
			}
		});
		jToggleButton2 = new JToggleButton();
		jToggleButton2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (jToggleButton2.isSelected()) {
					sharedFiles = SharedFile.load(jTextField1.getText());
					jTextField1.setEnabled(false);
					jButton2.setEnabled(false);
					jToggleButton2.setText("Disable Sharing");
				} else {
					jTextField1.setEnabled(true);
					jButton2.setEnabled(true);
					jToggleButton2.setText("Enable Sharing");
				}
			}
		});
		jSeparator2 = new JSeparator();
		jLabel3 = new JLabel();
		jLabel4 = new JLabel();
		jTextField2 = new JTextField();
		jTextField2.setText("UNKOWN");
		jSeparator3 = new JSeparator();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		jLabel5.setText("Search");

		jButton1.setText("Go");

		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jScrollPane1.setViewportView(jTextArea1);

		jLabel6.setText("Nearby");

		listModel = new DefaultListModel<String>();
		list = new JList(listModel);

		tglbtnNewToggleButton = new JToggleButton("Scan");
		tglbtnNewToggleButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tglbtnNewToggleButton.isSelected()) {
					application.startListening(MainGui.this);
					listModel.clear();
				} else {
					application.stopListening();
				}
			}
		});

		btnNewButton = new JButton("Get Profile");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProfileDialog newDialog = new ProfileDialog(application
						.requestProfile((String) list.getSelectedValue())
						.getProfile());
				newDialog.setVisible(true);
			}
		});

		btnNewButton_1 = new JButton("View Folder");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SharedFile sharedFiles = application.requestSharedFiles(
						(String) list.getSelectedValue()).getSharedFiles();
				SharedFilesDialog newDialog = new SharedFilesDialog(sharedFiles);
				newDialog.setVisible(true);
			}
		});

		btnNewButton_2 = new JButton("Send File");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				jSeparator4,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addGroup(
																								jPanel1Layout
																										.createSequentialGroup()
																										.addGap(6)
																										.addComponent(
																												jScrollPane2,
																												GroupLayout.DEFAULT_SIZE,
																												330,
																												Short.MAX_VALUE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addGroup(
																												jPanel1Layout
																														.createParallelGroup(
																																Alignment.TRAILING,
																																false)
																														.addComponent(
																																btnNewButton,
																																0,
																																0,
																																Short.MAX_VALUE)
																														.addComponent(
																																btnNewButton_2,
																																GroupLayout.DEFAULT_SIZE,
																																GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE)
																														.addComponent(
																																tglbtnNewToggleButton,
																																Alignment.LEADING,
																																GroupLayout.DEFAULT_SIZE,
																																117,
																																Short.MAX_VALUE)
																														.addComponent(
																																btnNewButton_1,
																																Alignment.LEADING,
																																GroupLayout.DEFAULT_SIZE,
																																GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE)))
																						.addGroup(
																								jPanel1Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel5)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												jTextField3,
																												GroupLayout.DEFAULT_SIZE,
																												339,
																												Short.MAX_VALUE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												jButton1))
																						.addComponent(
																								jScrollPane1,
																								GroupLayout.DEFAULT_SIZE,
																								459,
																								Short.MAX_VALUE)))
														.addComponent(jLabel6))
										.addContainerGap()));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																jTextField3,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel5)
														.addComponent(jButton1))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																jSeparator4,
																GroupLayout.PREFERRED_SIZE,
																10,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jScrollPane1,
																GroupLayout.PREFERRED_SIZE,
																130,
																GroupLayout.PREFERRED_SIZE))
										.addGap(14)
										.addComponent(jLabel6)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				btnNewButton_2)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				btnNewButton)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				btnNewButton_1)
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				79,
																				Short.MAX_VALUE)
																		.addComponent(
																				tglbtnNewToggleButton))
														.addComponent(
																jScrollPane2,
																GroupLayout.DEFAULT_SIZE,
																191,
																Short.MAX_VALUE))
										.addContainerGap()));
		jScrollPane2.setViewportView(list);
		jPanel1.setLayout(jPanel1Layout);

		jTabbedPane1.addTab("Share", jPanel1);

		jLabel1.setText("Discovery");

		jLabel2.setText("Shared Folder");

		jButton2.setText("Explore");

		jToggleButton1.setText("Enable");

		jToggleButton2.setText("Enable Sharing");
		jToggleButton2.setToolTipText("");

		jLabel3.setText("Profile");

		jLabel4.setText("Name:");

		lbDiscoveryTime = new JLabel("10 Seconds");

		GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
		jPanel2Layout
				.setHorizontalGroup(jPanel2Layout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																jSeparator1,
																GroupLayout.PREFERRED_SIZE,
																471,
																Short.MAX_VALUE)
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				jPanel2Layout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								jSeparator3,
																								GroupLayout.DEFAULT_SIZE,
																								471,
																								Short.MAX_VALUE)
																						.addComponent(
																								jSeparator2,
																								GroupLayout.DEFAULT_SIZE,
																								471,
																								Short.MAX_VALUE)
																						.addComponent(
																								jSlider1,
																								GroupLayout.DEFAULT_SIZE,
																								471,
																								Short.MAX_VALUE)
																						.addGroup(
																								jPanel2Layout
																										.createSequentialGroup()
																										.addGap(12)
																										.addComponent(
																												jLabel4,
																												GroupLayout.PREFERRED_SIZE,
																												45,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												jTextField2,
																												GroupLayout.DEFAULT_SIZE,
																												402,
																												Short.MAX_VALUE))
																						.addComponent(
																								jLabel1)
																						.addComponent(
																								jLabel2)
																						.addComponent(
																								jLabel3)
																						.addGroup(
																								jPanel2Layout
																										.createSequentialGroup()
																										.addGap(12)
																										.addComponent(
																												lbDiscoveryTime)
																										.addPreferredGap(
																												ComponentPlacement.RELATED,
																												GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE)
																										.addComponent(
																												jToggleButton1))
																						.addGroup(
																								Alignment.TRAILING,
																								jPanel2Layout
																										.createSequentialGroup()
																										.addComponent(
																												jTextField1,
																												GroupLayout.DEFAULT_SIZE,
																												379,
																												Short.MAX_VALUE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												jButton2))))
														.addGroup(
																Alignment.TRAILING,
																jPanel2Layout
																		.createSequentialGroup()
																		.addContainerGap(
																				342,
																				Short.MAX_VALUE)
																		.addComponent(
																				jToggleButton2)))
										.addContainerGap()));
		jPanel2Layout
				.setVerticalGroup(jPanel2Layout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(jLabel1)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(jSlider1,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lbDiscoveryTime)
														.addComponent(
																jToggleButton1))
										.addGap(12)
										.addComponent(jSeparator1,
												GroupLayout.PREFERRED_SIZE, 10,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(jLabel2)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																jTextField1,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(jButton2))
										.addGap(12)
										.addComponent(jToggleButton2)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addComponent(jSeparator2,
												GroupLayout.PREFERRED_SIZE, 10,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(jLabel3)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(jLabel4)
														.addComponent(
																jTextField2,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addComponent(jSeparator3,
												GroupLayout.PREFERRED_SIZE, 10,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(130, Short.MAX_VALUE)));
		jPanel2.setLayout(jPanel2Layout);

		jTabbedPane1.addTab("Settings", jPanel2);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addComponent(jTabbedPane1,
				GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addComponent(jTabbedPane1,
				GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE));
		getContentPane().setLayout(groupLayout);

		pack();

		this.application = new GnawApplication(this);
		this.application.init();
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(MainGui.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(MainGui.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(MainGui.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MainGui.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainGui().setVisible(true);
			}
		});
	}

	private JButton jButton1;
	private JButton jButton2;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JSeparator jSeparator1;
	private JSeparator jSeparator2;
	private JSeparator jSeparator3;
	private JSeparator jSeparator4;
	private JSlider jSlider1;
	private JTabbedPane jTabbedPane1;
	private JTextArea jTextArea1;
	private JTextField jTextField1;
	private JTextField jTextField2;
	private JTextField jTextField3;
	private JToggleButton jToggleButton1;
	private JToggleButton jToggleButton2;
	private JLabel lbDiscoveryTime;
	private JToggleButton tglbtnNewToggleButton;
	private JList list;
	private DefaultListModel listModel;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;

	@Override
	public Profile getProfile() {
		Profile profile = new Profile();
		profile.setName(jTextField2.getText());
		return profile;
	}

	@Override
	public void ClientFoundEventOccurred(ClientFoundEvent evt) {
		listModel.addElement(evt.getSource());
	}

	@Override
	public SharedFile getSharedFiles() {
		return this.sharedFiles;
	}

	@Override
	public boolean postMessage() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean postOffer() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean postOfferResponse() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean postSearchRequest() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean postSearchResult() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void BroadcastingEndEventOccurred(BroadcastingEndEvent evt) {
		jToggleButton1.setSelected(false);
	}

	@Override
	public boolean deliverMessage() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deliverOffer() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deliverOfferResponse() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deliverSearchRequest() {
		// TODO Auto-generated method stub
		return false;
	}
}
