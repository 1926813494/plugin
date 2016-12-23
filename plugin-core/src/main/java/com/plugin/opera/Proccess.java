package com.plugin.opera;

import com.plugin.utils.SetFileEnum;

public interface Proccess {

	public String proccess(String line);
	
	public int verify(String line);

	public SetFileEnum getFileEnum();
}
