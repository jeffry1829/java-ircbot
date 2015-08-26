package ysitd.ircbot.java.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class JIRCBOTPlugin implements CommandExecutor{
	
	protected String server , nickname , channel;
	private static Socket socket;
	private static PrintWriter writer;
	
	public static ArrayList<JIRCBOTListener> jircbotlistenerlist=new ArrayList<JIRCBOTListener>();
	public static ArrayList<CommandExecutor> commandlist=new ArrayList<CommandExecutor>();
	public static ArrayList<JIRCBOTPlugin> jircbotpluginlist=new ArrayList<JIRCBOTPlugin>();
	
	static {
		try {
			staticMethod.loadjar();
		} catch (ClassNotFoundException e) {
			System.out.println("沒有plugin喔"); //modifly later
		}
		   catch (IOException e1) {
			   e1.printStackTrace();
		}
	}
	
	public JIRCBOTPlugin(String server , String nickname , String channel , int port , String msg) throws IOException{
		this.server=server;
		this.nickname=nickname;
		this.channel=channel;
		
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
		
		Thread commandHandler=new Thread( new CommandHandler()  );
		Thread recivemessageEvent=new Thread(  new reciveMessageEvent() );
		commandHandler.start();
		recivemessageEvent.start();
	}
	
	public JIRCBOTPlugin(){}
	
	public static void registerAnEvent(JIRCBOTListener jircbotlistenertlistener){
		jircbotlistenerlist.add(jircbotlistenertlistener);
	}
	
	public static void registerAnCommand(CommandExecutor icommandexecutor){
		commandlist.add(icommandexecutor);
	}
	
	public static void registerAnmain( JIRCBOTPlugin jircbotplugin){
		jircbotpluginlist.add(jircbotplugin);
	}
	
	public static Socket getServer(){
		return socket; 
	}

	public static PrintWriter getWriter(){
		return writer;
	}
	
	public static void say(String tosay , String channel){
		 JIRCBOTPlugin.getWriter().println("PRIVMSG "+channel+" : "+tosay);
		 JIRCBOTPlugin.getWriter().flush();
	}
	
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
		for(JIRCBOTPlugin e : jircbotpluginlist){
			e.onDisable();
		}
		System.exit(0);
	}
	
}
