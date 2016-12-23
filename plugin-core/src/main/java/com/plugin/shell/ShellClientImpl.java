package com.plugin.shell;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.plugin.opera.LineConvertor;
import com.plugin.utils.IOUtils;

public class ShellClientImpl extends AbstractShellClient {
	
	private LineConvertor lineConvertor;
	
	private File pluginFile;
	
	public ShellClientImpl() {
		super();
	}

	public ShellClientImpl(LineConvertor lineConvertor,File pluginPath) {
		super();
		this.lineConvertor = lineConvertor;
		pluginFile = new File(pluginPath, getShName());
	}

	
	@Override
	public void touchSh() {
		//写头部
		writeInfo(headerInfo());
		//写删除
		Shell delShell = new DelShell(pluginFile, lineConvertor.getDelFiles());
		delShell.writeShell();
		//写覆盖
		Shell modShell = new ModShell(pluginFile, lineConvertor.getCpMapFiles());
		modShell.writeShell();
		//写结束
		writeInfo(footerInfo());
	}
	
	private void writeInfo(String info) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(pluginFile, true);
			fw.write(info);
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			IOUtils.closeQuietly(fw);
		}	
	}
	
	public LineConvertor getLineConvertor() {
		return lineConvertor;
	}

	public void setLineConvertor(LineConvertor lineConvertor) {
		this.lineConvertor = lineConvertor;
	}

	public File getPluginFile() {
		return pluginFile;
	}

	public void setPluginFile(File pluginFile) {
		this.pluginFile = pluginFile;
	}

	@Override
	public void setPluginPath(File pluginPath) {
		this.pluginFile = new File(pluginPath, getShName());
	}
}
