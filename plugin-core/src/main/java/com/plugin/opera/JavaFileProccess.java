package com.plugin.opera;

import com.plugin.utils.GlobalParam;
import com.plugin.utils.SetFileEnum;

public class JavaFileProccess implements Proccess {
	private String src = "src/main/java";

	@Override
	public String proccess(String line) {
		String substring = line.substring(line.indexOf(src)+src.length()+1, line.lastIndexOf(".java"));
		substring = substring + ".class";
		return GlobalParam.WEBINF_CLASSES+"/"+substring;
	}

	@Override
	public int verify(String line) {
		int index = line.indexOf(src);
		return index;
	}

	@Override
	public SetFileEnum getFileEnum() {
		return SetFileEnum.CLASS;
	}

}
