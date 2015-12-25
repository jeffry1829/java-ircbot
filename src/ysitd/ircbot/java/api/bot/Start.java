package ysitd.ircbot.java.api.bot;

import java.io.IOException;

import ysitd.ircbot.java.api.PluginMain;
import ysitd.ircbot.java.api.StaticMethods;
import ysitd.ircbot.java.api.Permission;

/**
 * 創見於 2015/8/8
 */
public class Start {
	
	static {
		try {
			StaticMethods.loadjar();
			StaticMethods.loadprofile();
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
			new PluginMain(PluginMain.getServername() , PluginMain.getNickname() , PluginMain.getChannel() , Integer.parseInt( PluginMain.getPort() ) , PluginMain.getDescribe() );
			for(PluginMain e : PluginMain.jircbotpluginlist){ //在上面完全執行完之前(連上irc server),就會執行到
				System.out.println(PluginMain.jircbotpluginlist);
				e.onEnable();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
