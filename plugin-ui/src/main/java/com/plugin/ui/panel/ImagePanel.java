package com.plugin.ui.panel;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 942250908555932504L;
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		ImageIcon imageIcon = new ImageIcon(Main.class.getResource("/com/plugin/ui/resources/loading.gif"));
		g.drawImage(imageIcon.getImage(),0,0,200,200, this);
	}
	
}
