package com.plugin.file;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import com.plugin.opera.LineConvertorImpl;
import com.plugin.shell.ShellClient;
import com.plugin.utils.ClassifyFilesSet;
import com.plugin.utils.FileUtils;

public class PluginImpl implements Plugin {

	private File compileProjPath;

	private File pluginPath;

	private LogReader logReader;
	
	private ShellClient shellClient;
	
	/**
	 * 
	 * @param webPath
	 *            编译好的class工程，包括jsp等其他文件(绝对路径)
	 * @param pluginPath
	 *            存放大补丁包的文件夹（绝对路径）
	 */
	public PluginImpl(String compileProjPath, String pluginPath) {
		super();
		this.compileProjPath = new File(compileProjPath);
		this.pluginPath = new File(pluginPath);
	}

	/**
	 * 产生插件
	 */
	@Override
	public void generate() {
		logReader.read();
		//处理class文件
		ClassifyFilesSet modFiles = logReader.getLineHandler().getModFiles();
		batchModClassFiles(modFiles.getClassFiles());
		batchModResoucesFiles(modFiles.getResoucesFiles());
		batchModStaticFiles(modFiles.getStaticsFiles());
		this.shellClient.setLineConvertor(new LineConvertorImpl(this.logReader.getLineHandler()));
		this.shellClient.setPluginPath(pluginPath);
		this.shellClient.touchSh();
	}

	private void batchModStaticFiles(Set<String> set) {
		if(set != null) {
			for(String file : set) {
				modStaticFile(file);
			}
		}
	}
	
	private void batchModResoucesFiles(Set<String> set) {
		if(set != null) {
			for(String file:set) {
				modResoucesFile(file);
			}
		}
	}
	
	private void batchModClassFiles(Set<String> set) {
		if(set != null) {
			for(String file : set) {
				modClass(file);
			}
		}
	}
	
	public void modResoucesFile(String modFile) {
		modFile(modFile);
	}

	public void modStaticFile(String modFile) {
		modFile(modFile);
	}
	
	private void modFile(String modFile) {
		int index = modFile.lastIndexOf("/");
		File staticDir = this.pluginPath;
		if (index > -1) {
			staticDir = new File(this.pluginPath, modFile.substring(0, index));
		}
		if (!staticDir.exists()) {
			staticDir.mkdirs();
		}
		File absFile = new File(this.compileProjPath, modFile);
		try {
			FileUtils.copyFileToDirectory(absFile, staticDir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void modClass(String modFile) {
		int index = modFile.lastIndexOf("/");
		if (index > -1) {
			String classPackage = modFile.substring(0, index);
			String className = modFile.substring(index + 1, modFile.lastIndexOf(".class"));
			File classDir = new File(compileProjPath, classPackage);
			String[] list = classDir.list(new ClassFileNameFilter(className));
			for (String l : list) {
				File copyFile = new File(classDir, l);
				copyClass(classPackage, copyFile);
			}
		}
	}

	/**
	 * 复制类到补丁目录
	 * 
	 * @param classPackage
	 * @param copyFile
	 */
	public void copyClass(String classPackage, File copyFile) {
		File file = new File(pluginPath, classPackage);
		if (!file.exists()) {
			file.mkdirs();
		}
		try {
			FileUtils.copyFileToDirectory(copyFile, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setLogReader(LogReader logReader) {
		this.logReader = logReader; 
	}

	@Override
	public void setShellClient(ShellClient shellClient) {
		this.shellClient = shellClient;
	}

}
