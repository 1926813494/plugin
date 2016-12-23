package com.plugin.shell;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.plugin.utils.GlobalParam;
import com.plugin.utils.IOUtils;

/**
 * 修改脚本生成
 * @author Loren
 *
 */
public class ModShell implements Shell {
	
	/**
	 * 键是文件，值是复制到的包或目录
	 */
	private Map<String, String> cpMapFiles = new HashMap<String,String>();
	
	private File shellFile;
	
	public ModShell(File shellFile,Map<String, String> cpMapFiles) {
		this.shellFile = shellFile;
		this.cpMapFiles = cpMapFiles;
	}
	
	@Override
	public void writeShell() {
		//| mkdir -p project/com/ &&
		//yes | cp ${pluginPath}"/""copy1.txt" "$projectPath"
		//echo "cp $pluginPath/copy1.txt"
		//cp UserServle{t,t\$*}.class /opt/shell/
		FileWriter fw = null;
		try {
			fw = new FileWriter(shellFile,true);
			fw.write("#seccond cp the projectPath files \n");
			for(Map.Entry<String, String> entry : cpMapFiles.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				String cpShell = "yes | mkdir -p "+"${"+GlobalParam.SHELL_PROJECT_PATH+"}"+"\"/"+value+"\""
										+" && cp ${"+GlobalParam.SHELL_PLUGIN_PATH+"}/"+ShellUtils.filterClassFile(key)+"  "+
									"${"+GlobalParam.SHELL_PROJECT_PATH+"}"+"\"/"+value+"\"";
				String echoCpShell = "echo \"cp $"+GlobalParam.SHELL_PLUGIN_PATH+"/"+key+"\"";
				fw.write(cpShell+"\n");
				fw.write(echoCpShell+"\n");
			}
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			IOUtils.closeQuietly(fw);
		}

	}

	

}
