package ysitd.ircbot.java.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class CommandHandler implements Runnable{
	/*其實算是CommandEvent
	  *不過不使用Event的語法banfile
	  *@Runnable
	  *@prefix=\
	  *解析後若為指令
	  *呼叫UserCommandProcessEvent
	  *同時傳入資訊
	  *再由Event處理 , 呼叫
	  */
	Socket server;
	BufferedReader reader;
	
	public CommandHandler() throws IOException{
		server=JIRCBOTPlugin.getServer();
		reader=new BufferedReader( new InputStreamReader( server.getInputStream() ) );
	}

	@Override
	public void run() {
		//Can while( ( msline=reader.readLine() ) != null) run successfully? infinity loop?
		while(true){
			String msline;
			String username;
		
			try{
				while( ( msline=reader.readLine() ) != null){
			
					if( msline.matches("\bPRIVMSG\b") ){
						username=new String(msline).substring(msline.indexOf("#" , 2) , msline.indexOf(":" , 2));
						String[] arr=msline.split(":*:");
						msline=msline.substring(arr[0].length());
						if( msline.startsWith("\\") ){ //prefix setting還有event開始了.
							for(CommandExecutor command : JIRCBOTPlugin.commandlist){
								new UserCommandProcessEvent(username , "\\" , command.getName() , msline.replaceFirst("\\" , "").split(" ") , command).Do();
								System.out.println(msline.replaceFirst("\\" , "").split(" ")); //debug
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
}






