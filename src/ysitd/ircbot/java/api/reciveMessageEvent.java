package ysitd.ircbot.java.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class reciveMessageEvent implements CustomEvent , Runnable{
	
	/*
	 * @Runnable
	 * 若得到message呼叫實作以後的Event( Do() )
	 */
	
	Socket server;
	BufferedReader reader;
	String readline;
	
	public reciveMessageEvent() throws IOException{
		server=JIRCBOTPlugin.getServer();
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
		staticMethod.invokeOverrideEvent(this);
		
	}
	
	public String getALine(){
		return readline;
	}
	
	@Override
	public void run() {
		
		while(true){
			
			try {
				readline = reader.readLine();
					while( readline != null ){
			
						Do();
					
					}
			}
			catch(IOException e1){
				e1.printStackTrace();
			}
		
		}
	
	}
	
}
