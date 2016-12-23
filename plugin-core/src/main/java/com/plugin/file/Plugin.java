package com.plugin.file;

import com.plugin.shell.ShellClient;

public interface Plugin {

	/**
	 * 产生插件
	 */
	public void generate();
	
	public void setLogReader(LogReader logReader);
	
	public void setShellClient(ShellClient shellClient);
}
