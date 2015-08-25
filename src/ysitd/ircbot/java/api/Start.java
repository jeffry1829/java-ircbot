package ysitd.ircbot.java.api;

import java.io.IOException;

/**
 * 創見於 2015/8/8
 */
public class Start {
	public static void main(String[] args){
		JIRCBOTPlugin MPlugin;
		try {
			MPlugin = new JIRCBOTPlugin("irc.freenode.net","javabot_ysitd_","#ysitd",6667,"8 * : I am a Bot");
			MPlugin.onEnable();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
