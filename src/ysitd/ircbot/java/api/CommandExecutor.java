package ysitd.ircbot.java.api;

public interface CommandExecutor {
	String getName();
	String getHelp();
	boolean onCommand(String username , String prefix , String[] argument);
}
