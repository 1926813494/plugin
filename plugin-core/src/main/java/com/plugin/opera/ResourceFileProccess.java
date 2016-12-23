package com.plugin.opera;

import com.plugin.utils.GlobalParam;
import com.plugin.utils.SetFileEnum;

public class ResourceFileProccess implements Proccess {

	private String resources = "src/main/resources";
	
	@Override
	public String proccess(String line) {
		String substring = line.substring(line.indexOf(resources)+resources.length()+1,line.length());
		return GlobalParam.WEBINF_CLASSES+"/"+substring;
	}

	@Override
	public int verify(String line) {
		int index = line.indexOf(resources);
		return index;
	}

	@Override
	public SetFileEnum getFileEnum() {
		return SetFileEnum.RESOUCES;
	}

}
