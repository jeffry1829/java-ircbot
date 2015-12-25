package ysitd.ircbot.java.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class PluginMain implements CustomCommandExecutor{
	
	private static String server , nickname , channel , port , describe;
	private static Socket socket;
	private static PrintWriter writer;
	
	public static ArrayList<PluginListener> jircbotlistenerlist=new ArrayList<PluginListener>();
	public static ArrayList<CustomCommandExecutor> commandlist=new ArrayList<CustomCommandExecutor>();
	public static ArrayList<PluginMain> jircbotpluginlist=new ArrayList<PluginMain>();
	
	public PluginMain(String serverr , String nicknamee , String channell , int port , String msg) throws IOException{
		server=serverr;
		nickname=nicknamee;
		channel=channell;
		
		try{
			socket=new Socket(server , port);
			writer=new PrintWriter(socket.getOutputStream());
			
			writer.println("NICK " + nickname);
			writer.println("USER " + nickname + " " + msg);
			writer.println("JOIN " + channel);
			writer.flush();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		registerAnCommand( new CommandExit() );
		registerAnCommand( new CommandHelp() );
		
		Thread recivemessageEvent=new Thread(  new ReciveMessageEvent() );
		recivemessageEvent.start();
	}
	
	public PluginMain(){}
	
	public static void registerAnEvent(PluginListener jircbotlistenertlistener){
		jircbotlistenerlist.add(jircbotlistenertlistener);
	}
	
	public static void registerAnCommand(CustomCommandExecutor icommandexecutor){
		commandlist.add(icommandexecutor);
	}
	
	public static void registerAnmain( PluginMain jircbotplugin){
		jircbotpluginlist.add(jircbotplugin);
	}
	
	public static Socket getServer(){
		return socket; 
	}

	public static PrintWriter getWriter(){
		return writer;
	}
	
	public static String getServername(){
		return server;
	}
	
	public static String getNickname(){
		return nickname;
	}
	
	public static String getChannel(){
		return channel;
	}
	
	public static String getPort(){
		return port;
	}
	
	public static String getDescribe(){
		return describe;
	}
	
	public static void setDescribe(String name){
		describe=name;
	}
	
	public static void setPort(String name){
		port=name;
	}
	
	public static void setServername(String name){
		server=name;
	}
	
	public static void setNickname(String name){
		nickname=name;
	}
	
	public static void setChannel(String name){
		channel=name;
	}
	
	public static void say(String tosay , String channel){
		 PluginMain.getWriter().println("PRIVMSG "+channel+" :"+tosay);
		 PluginMain.getWriter().flush();
		 System.out.println(channel);
	}
	@Override
	public boolean onCommand(String username , String prefix , String command , String[] argument){
		return false;
	}
	
	public void onEnable(){}
	
	public void onDisable(){}

	@Override
	public String getName() {
		// need plugin support
		return null;
	};
	/*讓我想想更好的寫法*/
	static void shutdown(){
		for(PluginMain e : jircbotpluginlist){
			e.onDisable();
		}
		System.exit(0);
	}
	
	@Override
	public String getHelp() {
		return null;
	}
	
}
