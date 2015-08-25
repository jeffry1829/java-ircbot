package ysitd.ircbot.java.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class CommandExit implements CommandExecutor{
	
	Socket server;
	BufferedReader reader;
	
	public CommandExit() throws IOException{
		server=JIRCBOTPlugin.getServer();
		reader=new BufferedReader( new InputStreamReader( server.getInputStream() ) );
		JIRCBOTPlugin.registerAnCommand(this);
	}
	
	@Override
	public String getName() {
		String name="exit";
		return name;
	}

	@Override
	public boolean onCommand(String username, String prefix, String command, String[] argument) {
		//wait for permission feature complete
		if( username.startsWith("petjelinux") ){
			JIRCBOTPlugin.shutdown();
		}
		return false;
	}
	
}
