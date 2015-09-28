package ysitd.ircbot.java.plugin;

import ysitd.ircbot.java.api.CommandExecutor;
import ysitd.ircbot.java.api.JIRCBOTPlugin;

public class CommandJoin implements CommandExecutor{

	@Override
	public String getName() {
		final String name="join";
		return name;
	}

	@Override
	public boolean onCommand(String username , String prefix , String from, String[] argument) {
	/*
     * argument[1]==channel
     */
		re(argument[1]);
		return true;
	}

	public void re(String channel){
		JIRCBOTPlugin.getWriter().println("JOIN "+channel);
		JIRCBOTPlugin.getWriter().flush();
	}

	@Override
	public String getHelp() {
		return " usage: ]join channel";
	}
}
