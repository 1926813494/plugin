package com.plugin.ui.main;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.plugin.ui.panel.MainPanel;

public class MainWindown extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		MainWindown mainWindown = new MainWindown();
		MainPanel mainPanel = new MainPanel();
		mainWindown.setSize(450, 300);
		mainWindown.setLocationRelativeTo(null);
		mainWindown.setResizable(false);
		mainWindown.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		mainWindown.setContentPane(mainPanel);
		mainWindown.setVisible(true);
	}
	
	
}
