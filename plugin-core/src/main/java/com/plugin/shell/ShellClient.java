package com.plugin.shell;

import java.io.File;

import com.plugin.opera.LineConvertor;

public interface ShellClient {

	public void touchSh();
	
	public void setLineConvertor(LineConvertor lineConvertor);
	
	public void setShName(String shName);
	
	public void setPluginPath(File pluginPath);
}
