package irc.bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class CommandHandler implements Runnable{
	
	Socket server;
	BufferedReader reader;
	
	public CommandHandler() throws IOException{
		server=JIRCBOTPlugin.getServer();
		reader=new BufferedReader( new InputStreamReader( server.getInputStream() ) );
	}

	@Override
	public void run() {
		String msline;
		String username;
		try{
			while( ( msline=reader.readLine() ) != null){
			
				if( msline.matches(".* PRIVMSG .*") ){
					username=new String(msline).substring(msline.indexOf("#" , 2) , msline.indexOf(":" , 2));
					String[] arr=msline.split(":*:");
					msline=msline.substring(arr[0].length());
					if(msline.startsWith("\\")){ //prefix setting
						for(CommandExecutor command : JIRCBOTPlugin.commandlist){
							command.onCommand(username , "\\" , command.getName() , msline.replaceFirst("\\" , "").split(" "));
						}
					}
				}
			
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
		
}







