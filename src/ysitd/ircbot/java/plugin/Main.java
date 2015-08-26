package ysitd.ircbot.java.plugin;

import ysitd.ircbot.java.api.*;

public class Main extends JIRCBOTPlugin{
	
	static {
		JIRCBOTPlugin.registerAnmain(new Main());
	}
	
	@Override
	public void onEnable(){
		System.out.println("Plugin Enabled!");
		registerAnCommand(this);
	}
	@Override
	public void onDisable(){
		System.out.println("Plugin Disabled!");
	}
	@Override
	public boolean onCommand(String username , String prefix , String command , String[] argument){
		if(command.equals("ping")){
			getWriter().println("pong");
			getWriter().flush();
		}
		return false;	
	}
}
