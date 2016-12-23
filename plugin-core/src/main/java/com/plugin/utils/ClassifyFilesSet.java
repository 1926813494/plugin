package com.plugin.utils;

import java.util.HashSet;
import java.util.Set;

public class ClassifyFilesSet {

	private Set<String> classFiles = new HashSet<String>();
	
	private Set<String> resoucesFiles = new HashSet<String>();
	
	private Set<String> staticsFiles = new HashSet<String>();

	public Set<String> getClassFiles() {
		return classFiles;
	}

	public Set<String> getResoucesFiles() {
		return resoucesFiles;
	}

	public Set<String> getStaticsFiles() {
		return staticsFiles;
	}

	@Override
	public String toString() {
		return "ModFiles [classFiles=" + classFiles + ", resoucesFiles=" + resoucesFiles + ", staticsFiles="
				+ staticsFiles + "]";
	}
}
