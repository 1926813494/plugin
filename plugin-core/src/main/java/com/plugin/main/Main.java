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
//		args = new String[3];
//		args[0] = "G:/jenkins_svn/test/changeLog.txt";
//		args[1] = "G:/jenkins_svn/test/ciq_admin";
//		args[2] = "G:/jenkins_svn/test/plugins";
		if(args== null || args.length < 3) {
			System.out.println("参数：first--the svn changeLog file");
			System.out.println("参数：seccond--compile web class project path");
			System.out.println("参数：third--plugin store path");
			return;
		}
		File file = new File(args[0]); //日志存放目录
		ChangeLogReader changeLogReader = new ChangeLogReader(file);		
		changeLogReader.addProccess(new JavaFileProccess())
			.addProccess(new StaticFileProccess())
			.addProccess(new ResourceFileProccess());		
		ShellClient shellClient = new ShellClientImpl();
		/*第一个参数：编译好的工程的目录，第二个参数：补丁包存放的目录*/
		PluginImpl plugin = new PluginImpl(args[1],args[2]);
		plugin.setLogReader(changeLogReader);
		plugin.setShellClient(shellClient);
		plugin.generate();		
	}
	
}
