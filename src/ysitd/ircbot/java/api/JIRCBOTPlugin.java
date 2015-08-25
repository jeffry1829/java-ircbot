package ysitd.ircbot.java.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

class JIRCBOTPlugin implements CommandExecutor{
	
	String server , nickname , channel;
	private static Socket socket;
	private static PrintWriter writer;
	
	public static ArrayList<JIRCBOTListener> jircbotlistenerlist=new ArrayList<JIRCBOTListener>();
	public static ArrayList<CommandExecutor> commandlist=new ArrayList<CommandExecutor>();
	
	static{
		try {
			staticMethod.loadjar();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public JIRCBOTPlugin(String server , String nickname , String channel , int port , String msg){
		this.server=server;
		this.nickname=nickname;
		this.channel=channel;
		
		try{
			socket=new Socket(server , port);
			writer=new PrintWriter(socket.getOutputStream());
			
			writer.println("NICK " + nickname);
			writer.println("USER " + nickname + " " + msg);
			writer.println("JOIN " + channel);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public JIRCBOTPlugin(){}
	
	public static void registerAnEvent(JIRCBOTListener jircbotlistenertlistener){
		jircbotlistenerlist.add(jircbotlistenertlistener);
	}
	
	public static void registerAnCommand(CommandExecutor icommandexecutor){
		commandlist.add(icommandexecutor);
	}
	
	public static Socket getServer(){
		return socket; 
	}

	public static PrintWriter getWriter(){
		return writer;
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
		JIRCBOTPlugin plugin=new JIRCBOTPlugin();
		plugin.onDisable();
		System.exit(0);
	}
	
}
