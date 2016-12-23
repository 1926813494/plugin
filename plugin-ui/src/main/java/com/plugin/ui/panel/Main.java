package com.plugin.ui.panel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;

public class Main extends JFrame implements ActionListener {

	public static void main(String[] args) throws Exception {
		JButton button = new JButton("测试");
		button.setSize(80, 30);
		Main main = new Main();
		JPanel panel = new JPanel();
		panel.setSize(300, 300);
		main.setSize(400, 300);
		main.setLocationRelativeTo(null);
		//panel.add(button);
		button.addActionListener(main);
		main.getContentPane().setLayout(null);
		main.getContentPane().add(button);
		main.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		main.setVisible(true);
	}

	static class IamgePanel extends JPanel {
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			ImageIcon imageIcon = new ImageIcon(Main.class.getResource("/com/plugin/ui/resources/loading.gif"));
//			ImageIcon imageIcon = new ImageIcon("E:/images/loading.gif");
			g.drawImage(imageIcon.getImage(),0,0,200,200, this);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JDialog jDialog = new JDialog(this);
		jDialog.setLocation(this.getX(), this.getY());
		jDialog.setModal(true);
		jDialog.setResizable(false);
		jDialog.setSize(200, 230);
		JPanel imagePanel = new IamgePanel();
		jDialog.add(imagePanel);
		imagePanel.setSize(200, 200);
		jDialog.setVisible(true);
		//jDialog.add(comp);
	}
	
}
