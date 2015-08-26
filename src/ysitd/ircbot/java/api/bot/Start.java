package ysitd.ircbot.java.api.bot;

import java.io.IOException;

import ysitd.ircbot.java.api.JIRCBOTPlugin;
import ysitd.ircbot.java.api.staticMethod;

/**
 * 創見於 2015/8/8
 */
public class Start {
	
	static {
		try {
			staticMethod.loadjar();
		} catch (ClassNotFoundException e) {
			System.out.println("沒有plugin喔"); //modifly later
		}
		   catch (IOException e1) {
			   e1.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		
		try {
			JIRCBOTPlugin MPlugin = new JIRCBOTPlugin("irc.freenode.net","javabot_ysitd_","#ysitd",6667,"8 * : I am a Bot");
			for(JIRCBOTPlugin e : JIRCBOTPlugin.jircbotpluginlist){ //在上面完全執行完之前(連上irc server),就會執行到
				System.out.println(JIRCBOTPlugin.jircbotpluginlist+" list");
				e.onEnable();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
