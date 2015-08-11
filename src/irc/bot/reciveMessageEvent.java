package irc.bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
		
		for(  JIRCBOTListener event :  JIRCBOTPlugin.jircbotlistenerlist ){
			Method[] methods=event.getClass().getMethods();
			for( Method method : methods ){
				if( method.getParameterTypes().equals(this) ){ //為什麼沒有contains?
					try {
						method.invoke(this); //觸發了自己寫的Method
					} catch (IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
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
