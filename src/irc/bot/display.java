package irc.bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class display {
	
	Socket server;
	BufferedReader reader;
	reciveMessageEvent ReciveEvent=new reciveMessageEvent();
	
	public display() throws IOException{
		server=JIRCBOTPlugin.getServer();
		reader=new BufferedReader( new InputStreamReader( server.getInputStream() ) );
	}
	
	public void go() throws IOException{
		while( reader.readLine() != null ){
			
			ReciveEvent.Do();
			
			if(reader.readLine().matches(".* PRIVMSG .*")){
				System.out.println(reader.readLine().substring( reader.readLine().indexOf("PRIVMSG")+9 ));
			}
			else{
				System.out.println( reader.readLine() );
			}
		}
	}
	
}
