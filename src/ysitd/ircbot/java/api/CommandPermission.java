package ysitd.ircbot.java.api;

public class CommandPermission implements CustomCommandExecutor{

	@Override
	public String getName() {
		final String name = "permission";
		return name;
	}

	@Override
	public String getHelp() {
		return "permission add <user> <node>\n" +
			   "permission remove <user> <node>";
	}

	@Override
	public boolean onCommand(String username, String prefix, String from, String[] argument , String[] option) {
		//找天把Permission node像Bukkit獨立出去, 這樣太不美觀了
		if(Permission.contains(argument[2], "permission.add", from) && argument[1].equals("add")){
			Permission.putPermissionnode(argument[2], argument[3]);
		}else if(Permission.contains(argument[2], "permission.remove", from) && argument[1].equals("remove")){
			Permission.removePermissionnode(argument[2], argument[3]);
		}else{
			return false;
		}
		return true;
	}
}
