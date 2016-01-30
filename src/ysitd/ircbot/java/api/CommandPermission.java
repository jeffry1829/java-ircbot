package ysitd.ircbot.java.api;

public class CommandPermission implements CustomCommandExecutor{

	@Override
	public String getName() {
		final String name = "permission";
		return name;
	}

	@Override
	public String getHelp() {
		return "permission add <user>\n" +
			   "permission remove <user> <node>\n" +
			   "permission ";
	}

	@Override
	public boolean onCommand(String username, String prefix, String from, String[] argument , String[] option) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
