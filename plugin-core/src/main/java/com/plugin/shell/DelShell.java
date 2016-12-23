package com.plugin.shell;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import com.plugin.utils.GlobalParam;
import com.plugin.utils.IOUtils;
/**
 * 删除脚本生成
 * @author Loren
 *
 */
public class DelShell implements Shell {
	
	private Set<String> delFiles;

	private File shellFile;
	
	public DelShell(File shellFile,Set<String> delFiles) {
		this.shellFile = shellFile;
		this.delFiles = delFiles;
	}

	@Override
	public void writeShell() {
		FileWriter fw = null;
		try {
			fw = new FileWriter(shellFile, true);
			fw.write("#firstly delete the projectPath files \n");
			for(String delFile : delFiles) {
				//rm -rf ${projectPath}"/""1.txt"
				String delShell = "rm -rf ${"+GlobalParam.SHELL_PROJECT_PATH+"}/"+ShellUtils.filterClassFile(delFile);
				String echoDelShell = "echo \"delete $"+GlobalParam.SHELL_PROJECT_PATH+"/"+delFile+"\"";
				fw.write(delShell+"\n");
				fw.write(echoDelShell+"\n");
			}
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			IOUtils.closeQuietly(fw);
		}
		
	}	
	
}
