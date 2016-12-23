package com.plugin.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class DirChooserListener implements ActionListener {

	private JFileChooser fileChooser;
	
	private JTextField jTextField;
	
	public DirChooserListener(JFileChooser fileChooser) {
		super();
		this.fileChooser = fileChooser;
	}

	public DirChooserListener(JFileChooser fileChooser, JTextField jTextField) {
		super();
		this.fileChooser = fileChooser;
		this.jTextField = jTextField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int status = fileChooser.showOpenDialog(null);
		if(JFileChooser.APPROVE_OPTION == status) {
			File selectedFile = fileChooser.getSelectedFile();
			jTextField.setText(selectedFile.getAbsolutePath());
		}		
	}

}
