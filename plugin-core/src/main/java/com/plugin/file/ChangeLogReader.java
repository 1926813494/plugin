package com.plugin.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import com.plugin.handler.LineHandler;
import com.plugin.opera.Proccess;

public class ChangeLogReader implements LogReader {

	private File file;
	
	private List<Proccess> proccesses = new ArrayList<Proccess>();
	
	private LineHandler lineHandler;
	
	public ChangeLogReader(File file) {
		super();
		this.file = file;
		this.lineHandler = new LineHandler(proccesses);
	}

	@Override
	public LogReader addProccess(Proccess proccess) {
		proccesses.add(proccess);
		return this;
	}
	
	@Override
	public void read() {
		RandomAccessFile rf = null;
		try {
			rf = new RandomAccessFile(file, "r");
			long len = rf.length();
			long start = rf.getFilePointer();
			long nextend = start + len - 1;
			String line = null;
			rf.seek(nextend);
			int c = -1;
			while (nextend > start) {
				c = rf.read();
				if (c == '\n' || c == '\r') {
					line = rf.readLine();
					if(line != null) {
						lineHandler.classify(line); //处理内容
					}
					nextend--;
				}
				nextend--;
				rf.seek(nextend);
				if (nextend == 0) {// 当文件指针退至文件开始处，输出第一行
					line = rf.readLine();
					if(line != null) {						
						lineHandler.classify(line); //处理内容
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rf != null)
					rf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public LineHandler getLineHandler() {
		return lineHandler;
	}
	
}
