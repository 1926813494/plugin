package com.plugin.shell;

public abstract class AbstractShellClient implements ShellClient {
	private String shName = "plugin.sh";

	public String headerInfo() {
		StringBuffer sb = new StringBuffer("#!/bin/bash \n");
		sb.append("#first param is projectPath \n");
		sb.append("#seccond param is PluginPath \n");
		sb.append("projectPath=\"$1\"\n");
		sb.append("pluginPath=\"$2\"\n");
		sb.append("if [ ! -n \"$projectPath\" -o ! -d \"$projectPath\" ]; then\n");
		sb.append("  echo \"projectPath not exist\"\n");
		sb.append("  exit 0\n");
		sb.append("fi\n");
		sb.append("if [ ! -n \"$pluginPath\" -o ! -d \"$pluginPath\" ]; then\n");
		sb.append("  echo \"pluginPath not exist\"\n");
		sb.append("  exit 0\n");
		sb.append("fi\n");
		return sb.toString();
	}

	public String footerInfo() {
		return "echo \"exec success!\"\n";
	}

	public void setShName(String shName) {
		this.shName = shName;
	}

	public String getShName() {
		return shName;
	}
}
