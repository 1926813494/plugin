package com.plugin.ui.panel;

import javax.swing.JDialog;

public class LodingPanel {

	public static JDialog jDialog = new JDialog();
	
	static {
		jDialog.setModal(true);
		jDialog.setResizable(false);
		jDialog.setSize(200, 230);
		ImagePanel imagePanel = new ImagePanel();
		imagePanel.setSize(200, 200);
		jDialog.add(imagePanel);
	}
	
	public static void show() {
	}
	
}
