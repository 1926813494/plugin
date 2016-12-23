package com.plugin.shell.test;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.plugin.shell.DelShell;
import com.plugin.shell.ModShell;
import com.plugin.shell.Shell;

public class TestShell {

	@Test
	public void testDelShell() {
		Set<String> delFiles = new HashSet<String>();
		delFiles.add("WEB-INF/classes/com/shop/servelt/index.class");
		delFiles.add("WEB-INF/classes/com/shop/servelt/shop.class");
		File file = new File("F:/plugin.sh");
		Shell delShell = new DelShell(file,delFiles);
		delShell.writeShell();
	}
	
	@Test
	public void testModShell() {
		Map<String, String> modFiles = new HashMap<>();
		modFiles.put("com/incre_demo/servlet/shopServlet.class", "WEB-INF/classes/com/incre_demo/servlet");
		modFiles.put("com/incre_demo/servlet/shopServlet$dsads.class", "WEB-INF/classes/com/incre_demo/servlet");
		modFiles.put("com/incre_demo/servlet/shopServlet$AAA.class", "WEB-INF/classes/com/incre_demo/servlet");
		File file = new File("F:/plugin.sh");
		Shell modShell = new ModShell(file, modFiles);
		modShell.writeShell();
	}
	
	@Test
	public void testShell() {
	}
	
}
