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
		System.out.println(name);
		if(name != null) {
			int classIndex = name.lastIndexOf(".class");
			if(classIndex > -1) {				
				String noSuffixName = name.substring(0,classIndex);
				if(noSuffixName.equals(className)
						|| noSuffixName.contains(className+"$")) {
					return true;
				}
			}
		}
		return false;
	}

}
