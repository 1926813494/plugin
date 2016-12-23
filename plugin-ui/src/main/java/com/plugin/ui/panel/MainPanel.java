package com.plugin.ui.panel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.plugin.ui.listener.DirChooserListener;
import com.plugin.ui.listener.ExtractListener;
import com.plugin.ui.listener.FileChooserListener;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel logLabel = new JLabel("选择svn日志文件");
	
	private JLabel projectLabel = new JLabel("选择编译工程目录");
	
	private JLabel pluginLable = new JLabel("选择补丁存放的目录");
	
	private JLabel tipsLabel = new JLabel();
	
	private JTextField logTextField;
	
	private JTextField projectTextField;
	
	private JTextField pluginTextField;

	private JFileChooser fileChooser=new JFileChooser();
	private JButton projSelBtn;
	private JButton pluginSelBtn;
	private JButton logSelBtn;
	private JButton genderBtn;

	public MainPanel() {
		super();
		this.logTextField = new JTextField();
		this.logTextField.setToolTipText("选择svn生成的日志文件");
		this.projectTextField = new JTextField();
		this.projectTextField.setToolTipText("选择编译工程目录");
		this.pluginTextField = new JTextField();
		this.pluginTextField.setToolTipText("选择补丁存放的目录");
		this.projSelBtn = new JButton("选择");
		this.pluginSelBtn = new JButton("选择");
		this.logSelBtn = new JButton("选择");
		this.genderBtn = new JButton("抽取");
		this.setPlayView();
		this.addListener();
	}
	
	
	private void setPlayView() {
		this.setLayout(null);
		Color color = new Color(86,157,229);
		Border tfBorder = BorderFactory.createLineBorder(color);
		this.logLabel.setLocation(60, 10);
		this.logLabel.setSize(100, 20);
		this.logTextField.setSize(250, 25);
		this.logTextField.setLocation(60, 30);
		this.logTextField.setEditable(false);
		this.logTextField.setBorder(tfBorder);
		this.logSelBtn.setSize(80, 25);
		this.logSelBtn.setLocation(320, 30);
		
		this.projectLabel.setLocation(60, 60);
		this.projectLabel.setSize(120, 20);
		this.projectTextField.setSize(250, 25);
		this.projectTextField.setLocation(60,80);
		this.projectTextField.setEditable(false);
		this.projectTextField.setBorder(tfBorder);
		this.projSelBtn.setSize(80, 25);
		this.projSelBtn.setLocation(320,80);
		
		this.pluginLable.setLocation(60, 110);
		this.pluginLable.setSize(120, 20);
		this.pluginTextField.setSize(250, 25);
		this.pluginTextField.setLocation(60, 130);
		this.pluginTextField.setEditable(false);
		this.pluginTextField.setBorder(tfBorder);
		this.pluginSelBtn.setSize(80, 25);
		this.pluginSelBtn.setLocation(320, 130);
		this.genderBtn.setSize(80, 25);
		this.genderBtn.setLocation(170,200);
		this.tipsLabel.setSize(200, 20);
		this.tipsLabel.setLocation(170, 240);
		this.add(logLabel);
		this.add(logTextField);
		this.add(projectLabel);
		this.add(projectTextField);
		this.add(projSelBtn);
		this.add(pluginLable);
		this.add(pluginTextField);
		this.add(pluginSelBtn);
		this.add(logSelBtn);
		this.add(genderBtn);
		this.add(tipsLabel);
	}
	
	private void addListener() {
		this.logSelBtn.addActionListener(new FileChooserListener(fileChooser, logTextField));
		this.projSelBtn.addActionListener(new DirChooserListener(fileChooser,this.projectTextField));
		this.pluginSelBtn.addActionListener(new DirChooserListener(fileChooser, pluginTextField));
		this.genderBtn.addMouseListener(new ExtractListener(this));
	}


	public JLabel getTipsLabel() {
		return tipsLabel;
	}


	public JTextField getLogTextField() {
		return logTextField;
	}


	public JTextField getProjectTextField() {
		return projectTextField;
	}


	public JTextField getPluginTextField() {
		return pluginTextField;
	}
	public JButton getGenderBtn() {
		return genderBtn;
	}
}
