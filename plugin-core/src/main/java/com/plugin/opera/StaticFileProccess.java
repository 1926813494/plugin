package com.plugin.opera;

import com.plugin.utils.SetFileEnum;

public class StaticFileProccess implements Proccess {

	private String webapp = "src/main/webapp";
	
	@Override
	public String proccess(String line) {
		String substring = line.substring(line.indexOf(webapp)+webapp.length()+1,line.length());
		return substring;
	}

	@Override
	public int verify(String line) {
		int index = line.indexOf(webapp);
		return index;
	}

	@Override
	public SetFileEnum getFileEnum() {
		return SetFileEnum.STATIC;
	}

	
	
}
