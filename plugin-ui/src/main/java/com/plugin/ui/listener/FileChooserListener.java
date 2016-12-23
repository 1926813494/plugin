package com.plugin.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class FileChooserListener implements ActionListener {
	
	private JFileChooser fileChooser;
	
	private JTextField jTextField;
	
	public FileChooserListener(JFileChooser fileChooser, JTextField jTextField) {
		super();
		this.fileChooser = fileChooser;
		this.jTextField = jTextField;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int status = fileChooser.showOpenDialog(null);
		if(JFileChooser.APPROVE_OPTION == status) {
			File selectedFile = fileChooser.getSelectedFile();
			jTextField.setText(selectedFile.getAbsolutePath());
		}		
	}

}
