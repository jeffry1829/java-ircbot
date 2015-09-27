package ysitd.ircbot.java.plugin;

import java.util.ArrayList;

import ysitd.ircbot.java.api.CommandExecutor;
import ysitd.ircbot.java.api.JIRCBOTListener;
import ysitd.ircbot.java.api.JIRCBOTPlugin;
import ysitd.ircbot.java.api.UserCommandProcessEvent;

public class NoSuch implements JIRCBOTListener {
	
	static ArrayList<String>  cnsl=new ArrayList<String>();
	
	public NoSuch(){

	}
	
	public void onCommand(UserCommandProcessEvent ev) {
		cnsl.clear();
		for(CommandExecutor acommand : JIRCBOTPlugin.commandlist){
			cnsl.add(acommand.getName());
		}
		if( !cnsl.contains(ev.getcommandName()) ){
			JIRCBOTPlugin.say("この命令が存在しないです", JIRCBOTPlugin.getChannel());
			ev.setCanceled(true);
		}
	}

}
