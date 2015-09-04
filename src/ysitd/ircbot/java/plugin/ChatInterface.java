package ysitd.ircbot.java.plugin;

import java.util.Scanner;

import ysitd.ircbot.java.api.JIRCBOTPlugin;

public class ChatInterface implements Runnable{
	
	@Override
	public void run() {
		for(;;){
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			String s = null;
			while( (s=scanner.nextLine()) != null){
				JIRCBOTPlugin.say(s , JIRCBOTPlugin.getChannel());
			}
		}
	}
	
}
