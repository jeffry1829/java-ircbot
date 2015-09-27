package ysitd.ircbot.java.api;

public class CommandHelp implements CommandExecutor{

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
	public boolean onCommand(String username, String prefix, String[] argument) {
		for(CommandExecutor e:JIRCBOTPlugin.commandlist){
			JIRCBOTPlugin.say(e.getHelp(), JIRCBOTPlugin.getChannel());
		}
		return true;
	}

}
