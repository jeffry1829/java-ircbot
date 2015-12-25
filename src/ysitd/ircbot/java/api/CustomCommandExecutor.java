package ysitd.ircbot.java.api;

public interface CustomCommandExecutor {
	String getName();
	String getHelp();
	boolean onCommand(String username , String prefix , String from, String[] argument);
}
