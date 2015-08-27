package ysitd.ircbot.java.plugin;

import ysitd.ircbot.java.api.*;

public class Main extends JIRCBOTPlugin implements JIRCBOTListener{
	
	boolean reachPING;
	
	static {
		JIRCBOTPlugin.registerAnmain(new Main());
	}
	
	@Override
	public void onEnable(){
		System.out.println("Plugin Enabled!");
		registerAnCommand(this);
		registerAnEvent(this);
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
	public boolean onCommand(String username , String prefix , String command , String[] argument){
		if(argument[0].equals("ping")){
			say("pong", getChannel());
		}
		return false;	
	}
	//感覺像是Override但實際上才不是呢 >_< XDD
	public void reciveEvent(reciveMessageEvent e){
		if(e.getALine().startsWith("PING") && !reachPING){
			String pingIP=e.getALine().substring(6);
			getWriter().println("PONG " + pingIP);
			getWriter().flush();
		}
		else if( e.getSay().startsWith("ping") ){
			say( "pong" , getChannel() );
		}
		System.out.println(e.getALine());
	}
}
