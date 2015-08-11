package irc.bot;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

abstract class JIRCBOTPlugin implements CommandExecutor{
	
	String server , nickname , channel;
	private static Socket socket;
	private static PrintWriter writer;
	
	public static ArrayList<JIRCBOTListener> jircbotlistenerlist=new ArrayList<JIRCBOTListener>();
	public static ArrayList<CommandExecutor> commandlist=new ArrayList<CommandExecutor>();
	
	public JIRCBOTPlugin(String server , String nickname , String channel){
		this.server=server;
		this.nickname=nickname;
		this.channel=channel;
		try{
			socket=new Socket(server , 6667);
			writer=new PrintWriter(socket.getOutputStream());
			
			writer.println("NICK " + nickname);
			writer.println("USER " + nickname + " 8 * : I am a Bot");
			writer.println("JOIN " + channel);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void registerAnEvent(JIRCBOTListener jircbotlistenertlistener){
		jircbotlistenerlist.add(jircbotlistenertlistener);
	}
	
	public void registerAnCommand(CommandExecutor icommandexecutor){
		commandlist.add(icommandexecutor);
	}
	
	public static Socket getServer(){
		return socket; 
	}

	public static PrintWriter getWriter(){
		return writer;
	}
	
	public boolean execCommand(){
		return false;
	}
	
	public boolean onCommand(String username , String prefix , String command , String[] argument){
		return false;
	}
	
	abstract void onEnable();
	
	abstract void onDisable();
	
}
