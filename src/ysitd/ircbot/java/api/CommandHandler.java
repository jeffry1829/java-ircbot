package ysitd.ircbot.java.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class CommandHandler{
	/*
	 * 從reciveMessageEvent呼叫 , 避免訊息接收不完全 (Thread互搶)
	  *@prefix=]
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

	
	public void run(String msline) {
		/*
		 * It is not an Override method
		 * Can while( ( msline=reader.readLine() ) != null) run successfully? infinity loop?
		 * 對話例子/
		 * :petjelinux!~petjelinu@61-230-157-131.dynamic.hinet.net PRIVMSG #ysitd :要修還需要修一陣子 , 所以能夠給我一段嗎 包括對話
		 * 01    ~    10   
		 */
		
		String username;
			try{
				if( msline.matches(".* PRIVMSG .*") ){
					username=new String(msline).substring(1 , msline.indexOf("!" , 1)); //result="petjelinux"
					msline=msline.replaceFirst(":.*:","");//result="要修還需要修一陣子 , 所以能夠給我一段嗎 包括對話"
					if( msline.startsWith("]") ){
						for(CommandExecutor command : JIRCBOTPlugin.commandlist){
							new UserCommandProcessEvent(username , "]" , command.getName() , msline.replaceFirst("]" , "").split(" ") , command).Do();
						}
					}
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		
	}		
}






