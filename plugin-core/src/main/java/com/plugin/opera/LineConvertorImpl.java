package com.plugin.opera;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.plugin.handler.LineHandler;
import com.plugin.utils.ClassifyFilesSet;

public class LineConvertorImpl implements LineConvertor {
	
	private LineHandler lineHandler;
	
	private Set<String> delFiles = new HashSet<String>();
	
	private Map<String, String> cpMapFiles = new HashMap<String,String>();
	
	public LineConvertorImpl(LineHandler lineHandler) {
		this.lineHandler = lineHandler;
		converDelFiles();
		converCpMapFiles();
	}
	
	@Override
	public Map<String, String> getCpMapFiles() {
		return cpMapFiles;
	}
	
	private void converCpMapFiles() {
		this.cpMapFiles.putAll(converCpClassFiles(lineHandler.getModFiles().getClassFiles()));
		this.cpMapFiles.putAll(converCpClassFiles(lineHandler.getModFiles().getResoucesFiles()));
		this.cpMapFiles.putAll(converCpClassFiles(lineHandler.getModFiles().getStaticsFiles()));		
	}
	
	private Map<String, String> converCpClassFiles(Set<String> files) {
		Map<String, String> map = new HashMap<String,String>();
		for(String classFile:files) {
			String key = converKey(classFile);
			String value = converValue(classFile);
			map.put(key, value);
		}
		return map;
	}
	
	private String converKey(String modFile) {
		return modFile;		
	}
	
	private String converValue(String modFile) {
		int indexOf = modFile.lastIndexOf("/");
		if(indexOf > -1) {
			return modFile.substring(0, indexOf);
		}else {
			return "";
		}
	}

	private void converDelFiles() {
		ClassifyFilesSet classifyFilesSet = lineHandler.getDelFiles();
		Set<String> classFiles = classifyFilesSet.getClassFiles();
		Set<String> resoucesFiles = classifyFilesSet.getResoucesFiles();
		Set<String> staticsFiles = classifyFilesSet.getStaticsFiles();
		this.delFiles.addAll(classFiles);
		this.delFiles.addAll(resoucesFiles);
		this.delFiles.addAll(staticsFiles);
	}
	
	@Override
	public Set<String> getDelFiles() {
		return this.delFiles;
	}

}
