package ysitd.ircbot.java.api.bot;

import java.io.IOException;

import ysitd.ircbot.java.api.JIRCBOTPlugin;

/**
 * 創見於 2015/8/8
 */
public class Start {
	public static void main(String[] args){
		
		try {
			JIRCBOTPlugin MPlugin = new JIRCBOTPlugin("irc.freenode.net","javabot_ysitd_","#ysitd",6667,"8 * : I am a Bot");
			for(JIRCBOTPlugin e : JIRCBOTPlugin.jircbotpluginlist){
				e.onEnable();
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
