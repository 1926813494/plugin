package com.plugin.file;

import java.io.File;
import java.io.FilenameFilter;

public class ClassFileNameFilter implements FilenameFilter {
	
	private String className;
	
	public ClassFileNameFilter(String className) {
		super();
		this.className = className;
	}

	@Override
	public boolean accept(File dir, String name) {
		if(name != null) {
			String noSuffixName = name.substring(0,name.lastIndexOf(".class"));
			if(noSuffixName.equals(className)
					|| noSuffixName.contains(className+"$")) {
				return true;
			}
		}
		return false;
	}

}
