package ysitd.ircbot.java.api.bot;

import java.io.IOException;

import ysitd.ircbot.java.api.JIRCBOTPlugin;
import ysitd.ircbot.java.api.staticMethod;
import ysitd.ircbot.java.api.Permission;

/**
 * 創見於 2015/8/8
 */
public class Start {
	
	static {
		try {
			staticMethod.loadjar();
			staticMethod.loadprofile();
			Permission.setupPermissionfile();
		} catch (ClassNotFoundException e) {
			System.out.println("沒有plugin喔"); //modifly later
		}
		   catch (IOException e1) {
			   e1.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		
		try {
			new JIRCBOTPlugin(JIRCBOTPlugin.getServername() , JIRCBOTPlugin.getNickname() , JIRCBOTPlugin.getChannel() , Integer.parseInt( JIRCBOTPlugin.getPort() ) , JIRCBOTPlugin.getDescribe() );
			for(JIRCBOTPlugin e : JIRCBOTPlugin.jircbotpluginlist){ //在上面完全執行完之前(連上irc server),就會執行到
				System.out.println(JIRCBOTPlugin.jircbotpluginlist);
				e.onEnable();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
