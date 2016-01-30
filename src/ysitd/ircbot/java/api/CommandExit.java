package ysitd.ircbot.java.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class CommandExit implements CustomCommandExecutor{
	
	Socket server;
	BufferedReader reader;
	
	public CommandExit() throws IOException{
		server=PluginMain.getServer();
		reader=new BufferedReader( new InputStreamReader( server.getInputStream() ) );
	}
	
	@Override
	public String getName() {
		String name="exit";
		return name;
	}

	@Override
	public boolean onCommand(String username , String prefix , String from, String[] argument , String[] option) {
		if( Permission.contains(username, "operator.shutdown",from)){
			PluginMain.shutdown();
			return true;
		}
		return false;
	}

	@Override
	public String getHelp() {
		return "usage: ]exit" +
				"  REQUIRE ADMIN";
	}
	
}
