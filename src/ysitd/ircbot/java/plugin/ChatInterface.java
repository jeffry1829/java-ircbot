package ysitd.ircbot.java.plugin;

import java.util.Scanner;

import ysitd.ircbot.java.api.PluginMain;

public class ChatInterface implements Runnable{
	
	@Override
	public void run() {
		for(;;){
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			String s = null;
			while( (s=scanner.nextLine()) != null){
				PluginMain.say(s , PluginMain.getChannel());
			}
		}
	}
	
}
