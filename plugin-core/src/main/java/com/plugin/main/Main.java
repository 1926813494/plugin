package com.plugin.main;

import java.io.File;

import com.plugin.file.ChangeLogReader;
import com.plugin.file.PluginImpl;
import com.plugin.opera.JavaFileProccess;
import com.plugin.opera.ResourceFileProccess;
import com.plugin.opera.StaticFileProccess;
import com.plugin.shell.ShellClient;
import com.plugin.shell.ShellClientImpl;

/**
 * 基于SVN作为版本控制，maven作为构建工具的抽取补丁包
 * @author Loren
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		File file = new File("G:/jenkins and svn/test/changeLog.txt"); //日志存放目录
		ChangeLogReader changeLogReader = new ChangeLogReader(file);		
		changeLogReader.addProccess(new JavaFileProccess())
			.addProccess(new StaticFileProccess())
			.addProccess(new ResourceFileProccess());
		ShellClient shellClient = new ShellClientImpl();
		/*第一个参数：编译好的工程的目录，第二个参数：补丁包存放的目录*/
		PluginImpl plugin = new PluginImpl("G:/jenkins and svn/test/incre_demo-0.0.2", "G:/jenkins and svn/test/plugins");
		plugin.setLogReader(changeLogReader);
		plugin.setShellClient(shellClient);
		plugin.generate();
		System.out.println(changeLogReader.getLineHandler().getDelFiles());
		System.out.println(changeLogReader.getLineHandler().getModFiles());		
	}
	
}
