package com.plugin.file;

import com.plugin.handler.LineHandler;
import com.plugin.opera.Proccess;

public interface LogReader {
	public void read();
	public LogReader addProccess(Proccess proccess);
	public LineHandler getLineHandler();
}
