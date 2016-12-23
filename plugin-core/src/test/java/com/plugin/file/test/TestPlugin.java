package com.plugin.file.test;

import org.junit.Test;

import com.plugin.file.PluginImpl;

public class TestPlugin {

	@Test
	public void testModClass() {
		
		PluginImpl plugin = new PluginImpl("G:/jenkins and svn/plugin/incre_demo-0.0.2", "G:/jenkins and svn/plugin/plugins");
		plugin.modClass("WEB-INF/classes/com/incre_demo/servlet/ShopsServlet.class");
	}
	
	@Test
	public void testModStaticFile() {
		PluginImpl plugin = new PluginImpl("G:/jenkins and svn/plugin/incre_demo-0.0.2", "G:/jenkins and svn/plugin/plugins");
		plugin.modStaticFile("WEB-INF/jsp/shops.jsp");
	}
	
	@Test
	public void testModResourcesFile() {
		PluginImpl plugin = new PluginImpl("G:/jenkins and svn/plugin/incre_demo-0.0.2", "G:/jenkins and svn/plugin/plugins");
		plugin.modResoucesFile("WEB-INF/classes/log4j.properties");
	}
	
	
}
