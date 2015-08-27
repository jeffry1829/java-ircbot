package ysitd.ircbot.java.plugin;

import java.util.Timer;
import java.util.TimerTask;

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
		/*
		 * 每60秒PONG一次
		 */
		if(e.getALine().startsWith("PING") && reachPING){
			final String pingIP=e.getALine().substring(6);
			Thread timepong=new Thread(new Runnable(){
				@Override
				public void run(){
					Timer permin=new Timer();
					permin.schedule(new TimerTask(){
						@Override
						public void run() {
							getWriter().println("PONG " + pingIP);
							getWriter().flush();
						}
					}, 60);
				}
				
			});
			timepong.start();
			reachPING=true;
			
		}
		else if( e.getSay().startsWith("ping") ){
			say( "pong" , getChannel() );
		}
		System.out.println(e.getALine());
	}
}
