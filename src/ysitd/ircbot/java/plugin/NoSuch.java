package ysitd.ircbot.java.plugin;

import java.util.ArrayList;

import ysitd.ircbot.java.api.CustomCommandExecutor;
import ysitd.ircbot.java.api.PluginListener;
import ysitd.ircbot.java.api.PluginMain;
import ysitd.ircbot.java.api.UserCommandProcessEvent;

public class NoSuch implements PluginListener {
	
	static ArrayList<String>  cnsl=new ArrayList<String>();
	
	public NoSuch(){

	}
	
	public void onCommand(UserCommandProcessEvent ev) {
		cnsl.clear();
		for(CustomCommandExecutor acommand : PluginMain.commandlist){
			cnsl.add(acommand.getName());
		}
		if( !cnsl.contains(ev.getcommandName()) ){
			PluginMain.say("この命令が存在しないです", ev.getFrom());
			ev.setCanceled(true);
		}
	}

}
