package ysitd.ircbot.java.plugin;

import java.io.IOException;
import java.io.PrintWriter;

import ysitd.ircbot.java.api.*;

public class ReJoin implements PluginListener{
	
	PrintWriter writer;
	
	public void recive(ReciveMessageEvent e){
		
		//:petjelinux!~petjelinu@61-230-156-86.dynamic.hinet.net KICK #petjelinuxtest javabot_ysitd :User terminated!
		
		//:orwell.freenode.net 433 * petjelinux :Nickname is already in use.
		//:orwell.freenode.net 451 * :You have not registered

		
		if(e.getALine().contains("KICK")&&e.getALine().contains(PluginMain.getNickname())){
			rejoin_kicked(e.getFrom());
		}
		else if(e.getALine().contains("433 *")){
			rejoin_notregyet(PluginMain.getNickname()+Math.round(Math.random()*100));
		}
		
	}

	public void rejoin_notregyet(String name){
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			PluginMain.Login();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PluginMain.setNickname( name );
	}

	public void rejoin_kicked(String from){
		try {
			Thread.sleep(1000000L); //sleep 100secs.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		writer=PluginMain.getWriter();
		writer.println("JOIN " + from);
		writer.flush();
	}
} 

