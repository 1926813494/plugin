package com.plugin.shell;

public class ShellUtils {

	public static String regClassFile(String classFile) {
		//UserServle{t,t\$*}.class
		int index = classFile.lastIndexOf("/");
		String classPackage = classFile.substring(0, index);
		String className = classFile.substring(index + 1, classFile.lastIndexOf(".class"));
		int lastCharLen = className.length() - 1;
		String lastChar = className.substring(lastCharLen);
		String prefixStr = className.substring(0,lastCharLen);
		return classPackage+"/"+prefixStr+"{"+lastChar+","+lastChar+"\\$*}.class";
	}
	
	public static String filterClassFile(String classFile) {
		if(classFile.lastIndexOf(".class") > -1) {
			return regClassFile(classFile);
		}
		return classFile;
	}	
}
