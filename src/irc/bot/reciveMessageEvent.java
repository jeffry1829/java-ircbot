package irc.bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class reciveMessageEvent implements CustomEvent,Runnable{
	
	Socket server;
	BufferedReader reader;
	
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
	
	@Override
	public void run() {
		
		while(true){
		
			String readline;
		
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
