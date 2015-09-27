package ysitd.ircbot.java.api;

import java.io.IOException;

import ysitd.ircbot.java.api.CommandExecutor;

public class CommandReload implements CommandExecutor{

	@Override
	public String getName() {
		final String name = "none";
		return name;
	}

	@Override
	public boolean onCommand(String username, String prefix, String[] argument) {
		try {
			staticMethod.loadjar();
			staticMethod.loadprofile();
			Permission.setupPermissionfile();
		} catch (ClassNotFoundException e) {
			System.out.println("沒有plugin喔");
		}
		   catch (IOException e1) {
			   e1.printStackTrace();
		}
		return false;
	}

	@Override
	public String getHelp() {
		return " usage: ]reload" +
				"  CANNOT USE YET!";
	}

}
