package ysitd.ircbot.java.plugin;

import ysitd.ircbot.java.api.CustomCommandExecutor;
import ysitd.ircbot.java.api.PluginMain;

public class CommandJoin implements CustomCommandExecutor{

	@Override
	public String getName() {
		final String name="join";
		return name;
	}

	@Override
	public boolean onCommand(String username , String prefix , String from, String[] argument , String[] option) {
	/*
     * argument[1]==channel
     */
		join(argument[1]);
		return true;
	}

	public void join(String channel){
		PluginMain.getWriter().println("JOIN "+channel);
		PluginMain.getWriter().flush();
	}

	@Override
	public String getHelp() {
		return " usage: ]join channel";
	}
}
