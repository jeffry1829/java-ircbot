package ysitd.ircbot.java.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReciveMessageEvent implements CustomEvent , Runnable{
	
	/*
	 * @Runnable
	 * 若得到message呼叫實作以後的Event( Do() )
	 */
	
	Socket server;
	BufferedReader reader;
	String readline;
	boolean isLogin;
	
	public ReciveMessageEvent() throws IOException{
		server=PluginMain.getServer();
		reader=new BufferedReader( new InputStreamReader( server.getInputStream() ) );
	}
	
	@Override
	public String getEventName() {
		final String name="reciveMessageEvent";
		return name;
	}

	@Override
	public void Do() {
		
		//invoke overrided event
		StaticMethods.invokeOverrideEvent(this);
		
	}
	
	public boolean isLogin(){
		return isLogin;
	}
	
	public String getFrom(){
			return new String(readline).substring(readline.indexOf("#",1),readline.indexOf(":",1)-1);
	}
	
	public String getALine(){
		return readline;
	}
	
	public String getSay(){
		if( getALine().matches(".* PRIVMSG .*") ){
			String msline=getALine().replaceFirst(":.*:","");//result="要修還需要修一陣子 , 所以能夠給我一段嗎 包括對話"
			return msline;
		}
		else{
			return "";
		}
	}
	
	@Override
	public void run() {
		while(true){
			try {
				while( (readline=reader.readLine()) != null ){
					
					if(readline.contains("328")){isLogin=true;}
					Do();
					new UserCommandProcessEvent(readline);
					
				}
			}
			catch(IOException e1){
				e1.printStackTrace();
			}
		}
	}
	
}
