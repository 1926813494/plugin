package com.plugin.ui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import com.plugin.ui.handler.PluginHandler;
import com.plugin.ui.panel.MainPanel;

public class ExtractListener extends MouseAdapter {
	
	private MainPanel mainPanel;
	
	public ExtractListener() {
		super();
	}

	public ExtractListener(MainPanel mainPanel) {
		super();
		this.mainPanel = mainPanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		mainPanel.getGenderBtn().setEnabled(false);
		verify();
		mainPanel.getGenderBtn().setEnabled(true);
	}
	
	private void verify() {
		String logFile = mainPanel.getLogTextField().getText();
		if(verifyText(logFile)) {
			String projectPath = mainPanel.getProjectTextField().getText();
			if(verifyText(projectPath)) {
				String pluginPath = mainPanel.getPluginTextField().getText();
				if(verifyText(pluginPath)) {
					mainPanel.getTipsLabel().setText("正在抽取补丁中...");
					new PluginHandler(logFile, projectPath, pluginPath).run();
					mainPanel.getTipsLabel().setText("补丁文件抽取完成");
				}else {
					showMessage("请选择补丁存放的目录");					
				}
			}else {
				showMessage("请选择编译的工程目录");				
			}
		}else {			
			showMessage("请选择svn日志文件");
		}
	}
	
	private void showMessage(String message) {
		JOptionPane.showMessageDialog(mainPanel,message);
	}
	
	private boolean verifyText(String text) {
		if(text == null || "".equals(text)) {
			return false;
		}
		return true;
	}
	
}
