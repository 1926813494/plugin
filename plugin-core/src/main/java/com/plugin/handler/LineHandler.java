package com.plugin.handler;

import java.util.List;

import com.plugin.opera.Proccess;
import com.plugin.utils.ClassifyFilesSet;

public class LineHandler {

	public static final String A_STRING = "A";

	public static final String M_STRING = "M";

	public static final String D_STRING = "D";

	private ClassifyFilesSet delFiles = new ClassifyFilesSet();
		
	private ClassifyFilesSet modFiles = new ClassifyFilesSet();
	
	private List<Proccess> proccesses = null;

	public LineHandler(List<Proccess> proccesses) {
		this.proccesses = proccesses;
	}

	public ClassifyFilesSet getModFiles() {
		return modFiles;
	}

	public ClassifyFilesSet getDelFiles() {
		return delFiles;
	}

	public void classify(String line) {
		if (line != null) {
			line = line.trim();
			if (line.startsWith(A_STRING) || line.startsWith(M_STRING)) {
				modFiles(line);
			} else if (line.startsWith(D_STRING)) {
				delFiles(line);
			}
		}
	}
	
	private void modFiles(String line) {
		diff(line,this.modFiles);
	}

	private void delFiles(String line) {
		diff(line,this.delFiles);
	}

	private void diff(String line,ClassifyFilesSet cfs) {
		for (Proccess proccess : this.proccesses) {
			boolean verify = verify(proccess, line);
			if(verify) {				
				line = proccess.proccess(line);
				typeFile(proccess, line,cfs);
			}
		}
	}

	private void typeFile(Proccess proccess,String line,ClassifyFilesSet cfs) {
		switch (proccess.getFileEnum()) {
		case CLASS:
			cfs.getClassFiles().add(line);			
			break;
		case RESOUCES:
			cfs.getResoucesFiles().add(line);
			break;
		case STATIC:
			cfs.getStaticsFiles().add(line);
			break;
		}
	}
	
	private boolean verify(Proccess proccess,String line) {
		int verify = proccess.verify(line);
		if(verify > -1) {			
			int index = line.lastIndexOf(".");
			if(index > -1) {
				return true;
			}
		}
		return false;
	}
	
}
