package com.gnaw.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;

import com.gnaw.gui.file.FileSystemModel;
import com.gnaw.models.SharedFile;

public class SharedFilesDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTree tree;

	/**
	 * Create the dialog.
	 */
	public SharedFilesDialog(SharedFile root) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			tree = new JTree(new FileSystemModel(root));
		}
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(
				Alignment.LEADING).addComponent(tree, GroupLayout.DEFAULT_SIZE,
				436, Short.MAX_VALUE));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(
				Alignment.LEADING).addComponent(tree, GroupLayout.DEFAULT_SIZE,
				230, Short.MAX_VALUE));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
