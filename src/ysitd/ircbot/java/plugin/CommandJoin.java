package ysitd.ircbot.java.plugin;

import java.io.PrintWriter;

import ysitd.ircbot.java.api.CommandExecutor;
import ysitd.ircbot.java.api.JIRCBOTPlugin;

public class CommandJoin implements CommandExecutor{

	@Override
	public String getName() {
		final String name="join";
		return name;
	}

	@Override
	public boolean onCommand(String username, String prefix, String[] argument) {
	/*
     * argument[1]==channel [2]==name
     */
    if(argument.length==2){
      re(argument[2],argument[1]);
    }
		return false;
	}

	public void re(String name , String channel){
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		PrintWriter writer= JIRCBOTPlugin.getWriter();
		writer.println("NICK " + name);
		writer.println("USER " + name + " " + JIRCBOTPlugin.getDescribe());
		writer.println("JOIN " + channel);
		writer.flush();
	}

	@Override
	public String getHelp() {
		return " usage: ]join channel nickname";
	}
}
