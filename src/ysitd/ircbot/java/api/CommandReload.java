package ysitd.ircbot.java.api;

import java.io.IOException;

public class CommandReload implements CommandExecutor{
	
	@Override
	public String getName() {
		final String name="reload";
		return name;
	}
	
	@Override
	public boolean onCommand(String username , String prefix , String command , String[] argument) {
		if(argument[0].equals(command)){
			if( !Permission.contains(username , "operator.reload") ) return false;
			try {
				staticMethod.Unloadjar();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
}
