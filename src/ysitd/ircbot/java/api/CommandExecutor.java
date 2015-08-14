package ysitd.ircbot.java.api;

interface CommandExecutor {
	String getName();
	boolean onCommand(String username , String prefix , String command , String[] argument);
}
