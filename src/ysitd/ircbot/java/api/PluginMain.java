package ysitd.ircbot.java.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import sun.misc.BASE64Encoder;

public class PluginMain implements CustomCommandExecutor{
	
	private static String server , nickname , channel , describe , username , password;
	private static int port;
	private static Socket socket;
	private static PrintWriter writer;
	
	public static ArrayList<PluginListener> jircbotlistenerlist=new ArrayList<PluginListener>();
	public static ArrayList<CustomCommandExecutor> commandlist=new ArrayList<CustomCommandExecutor>();
	public static ArrayList<PluginMain> jircbotpluginlist=new ArrayList<PluginMain>();
	
	public PluginMain(){}
	
	public static void Constructor(){
		try{
			socket=new Socket(server , port);
			writer=new PrintWriter(socket.getOutputStream());
			
			Login();
			
			registerAnCommand( new CommandExit() );
			registerAnCommand( new CommandHelp() );
			
			Thread recivemessageEvent=new Thread(  new ReciveMessageEvent() );
			recivemessageEvent.start();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void Login() throws IOException{
		
		//程式結構完全被打亂了...
		BufferedReader reader=new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
		BASE64Encoder encoder = new BASE64Encoder();
		
		writer.println("CAP REQ :sasl");
		writer.println("NICK " + nickname);
		writer.println("USER " + username + " " + describe);
		writer.flush();
		while(reader.readLine().matches("/(CAP)&(ACK)&(:sasl)/g")){
			writer.println("AUTHENTICATE PLAIN");
			writer.flush();
			break;
		}
		while(reader.readLine().matches("/AUTHENTICATE \\+/g")){
			byte[] textbyte = (nickname+username+password).getBytes("UTF-8");
			writer.println("AUTHENTICATE "+encoder.encode(textbyte));
			writer.flush();
			break;
		}
		while(reader.readLine().matches("/:SASL authentication successful/g")){
			writer.println("CAP END");
			writer.flush();
			break;
		}
		writer.println("JOIN " + channel);
		writer.flush();
	}
	
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
	
	public static int getPort(){
		return port;
	}
	
	public static String getDescribe(){
		return describe;
	}
	
	public static String getUsername(){
		return username;
	}
	
	public static String getPassword(){
		return password;
	}
	
	public static void setUsername(String name){
		username=name;
	}
	
	public static void setPassword(String name){
		password=name;
	}
	
	public static void setDescribe(String name){
		describe=name;
	}
	
	public static void setPort(int name){
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
	public boolean onCommand(String username , String prefix , String command , String[] argument , String[] option){
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
