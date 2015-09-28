package ysitd.ircbot.java.plugin;

import java.io.PrintWriter;

import ysitd.ircbot.java.api.*;

public class ReJoin implements JIRCBOTListener{

	public void recive(reciveMessageEvent e){
		
		//:petjelinux!~petjelinu@61-230-156-86.dynamic.hinet.net KICK #petjelinuxtest javabot_ysitd :User terminated!
		
		//:orwell.freenode.net 433 * petjelinux :Nickname is already in use.
		//:orwell.freenode.net 451 * :You have not registered

		
		if(e.getALine().contains("KICK")&&e.getALine().contains(JIRCBOTPlugin.getNickname())){
			re(null,e.getFrom());
		}
		else if(e.getALine().contains("451 * :You have not registered")){
			re(JIRCBOTPlugin.getNickname()+Math.round(Math.random()*100),e.getFrom());
		}
		
	}
	
	public void re(String name,String from){
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(name==null){ //KICK
			PrintWriter writer= JIRCBOTPlugin.getWriter();
			writer.println("JOIN " + from);
			writer.flush();
		}
		else{
			PrintWriter writer= JIRCBOTPlugin.getWriter();
			writer.println("NICK " + name);
			writer.println("USER " + name + " " + JIRCBOTPlugin.getDescribe());
			writer.println("JOIN " + JIRCBOTPlugin.getChannel());
			writer.flush();
			JIRCBOTPlugin.setNickname( name );
		}
	}
} 

