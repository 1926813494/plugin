package com.plugin.ui.handler;

import java.io.File;

import com.plugin.file.ChangeLogReader;
import com.plugin.file.PluginImpl;
import com.plugin.opera.JavaFileProccess;
import com.plugin.opera.ResourceFileProccess;
import com.plugin.opera.StaticFileProccess;
import com.plugin.shell.ShellClient;
import com.plugin.shell.ShellClientImpl;

public class PluginHandler {

	private File logFile;
	
	private String pluginDir;
	
	private String projectDir;
	
	public PluginHandler(String logFile,String projectDir,String pluginDir) {
		this.logFile = new File(logFile);
		this.pluginDir = pluginDir;
		this.projectDir = projectDir;
	}
	
	public void run() {
		ChangeLogReader changeLogReader = new ChangeLogReader(logFile);		
		changeLogReader.addProccess(new JavaFileProccess())
		.addProccess(new StaticFileProccess())
		.addProccess(new ResourceFileProccess());
		ShellClient shellClient = new ShellClientImpl();
		/*第一个参数：编译好的工程的目录，第二个参数：补丁包存放的目录*/
		PluginImpl plugin = new PluginImpl(projectDir, pluginDir);
		plugin.setLogReader(changeLogReader);
		plugin.setShellClient(shellClient);
		plugin.generate();
	}
	
}
