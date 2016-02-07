package ysitd.ircbot.java.plugin;

import ysitd.ircbot.java.api.*;

public class Main extends PluginMain implements PluginListener{
	
	static {
		PluginMain.registerAnmain(new Main());
	}
	
	@Override
	public void onEnable(){
		System.out.println("Plugin Enabled!");
		registerAnCommand(this);
		registerAnEvent(this);
		registerAnCommand(new TextImage());
		CommandBind cb=new CommandBind();
		registerAnCommand(cb);
		registerAnEvent(cb);
		registerAnEvent(new NoSuch());
		registerAnCommand(new CommandJoin());
		//registerAnEvent(new ReJoin());
		
		
		Thread say_th=new Thread(new ChatInterface());
		say_th.start();
	}
	@Override
	public void onDisable(){
		System.out.println("Plugin Disabled!");
	}
	@Override
	public String getName(){
		final String name="ping";
		return name;
	}
	@Override
	public boolean onCommand(String username , String prefix , String from, String[] argument , String[] option){
		if(argument[0].equals("ping")){
			say("pong" , from);
			return true;
		}
		return false;	
	}
	//感覺像是Override但實際上才不是呢 >_< XDD
	public void reciveEvent(ReciveMessageEvent e){
		if(e.getALine().startsWith("PING")){
			String pingIP=e.getALine().substring(6);
			getWriter().println("PONG " + pingIP);
			getWriter().flush();
		}
		System.out.println(e.getALine());
	}
}
