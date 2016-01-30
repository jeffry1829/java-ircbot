package ysitd.ircbot.java.api;

public class CommandHelp implements CustomCommandExecutor{

	@Override
	public String getName() {
		final String name="help";
		return name;
	}

	@Override
	public String getHelp() {
		return " usage: ]help";
	}

	@Override
	public boolean onCommand(String username , String prefix , String from, String[] argument , String[] option) {
		for(CustomCommandExecutor e:PluginMain.commandlist){
			PluginMain.say(e.getHelp(), from);
		}
		return true;
	}

}
