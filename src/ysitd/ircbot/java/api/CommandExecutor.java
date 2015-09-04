package ysitd.ircbot.java.api;

public interface CommandExecutor {
	String getName();
	boolean onCommand(String username , String prefix , String[] argument);
}
