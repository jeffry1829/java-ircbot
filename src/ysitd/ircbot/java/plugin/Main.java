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
	public String getName(){
		final String name="ping";
		return name;
	}
	@Override
	public boolean onCommand(String username , String prefix , String command , String[] argument){
		if(argument[0].equals("ping")){
			JIRCBOTPlugin.say("pong", channel); //channel inherit from superclass
		}
		return false;	
	}
}
