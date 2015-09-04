package ysitd.ircbot.java.plugin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import ysitd.ircbot.java.api.*;

public class CommandBind implements CommandExecutor , JIRCBOTListener{
	
	File filefile=new File("./bindlist");
	BufferedReader reader1;
	BufferedWriter writer1;
	BufferedReader reader2;
	BufferedWriter writer2;
	BufferedReader reader3;
	BufferedWriter writer3;
	
	 public CommandBind(){
		 if( !filefile.exists() ){
			 try {
				filefile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		 }
	 }
	
	@Override
	public String getName() {
		final String name="bind";
		return name;
	}
	
	@Override
	public boolean onCommand(String username , String prefix , String[] argument) {
		try{
				reader1=new BufferedReader(new FileReader(filefile));
				writer1=new BufferedWriter(new FileWriter(filefile , true));
				if(argument[1].equalsIgnoreCase("add")){
					writer1.write(argument[2] + "||" + argument[3] + "\n");
				}
				if(argument[1].equalsIgnoreCase("remove")){
					String sl;
					while( (sl = reader1.readLine()) != null ){
						if(sl.split("\\|\\|")[0].contains(argument[2])){
							removeLine(filefile , sl);
						}
					}
				}
				writer1.flush();
		}
		catch(IOException exception){
			exception.printStackTrace();
		}
	return false;
	}
	
	public void removeLine(File f , String torem) throws IOException{
		reader2=new BufferedReader(new FileReader(f));
		writer2=new BufferedWriter(new FileWriter(f));
		String l;
		while( (l=reader2.readLine()) != null){
			if(l.equals(torem)) continue;
			writer2.write(l+"\n");
		}
		writer2.flush();
	}
	
	public void messageEvent(reciveMessageEvent event) throws IOException{
		reader3=new BufferedReader(new FileReader(filefile));
		String l;
		while( (l=reader3.readLine()) !=null ){
			if(event.getSay().matches(l.split("\\|\\|")[0].replaceAll("_" , ""))){
				JIRCBOTPlugin.say(l.split("\\|\\|")[1].replaceAll("_" , ""), JIRCBOTPlugin.getChannel());
			}
		}
	}
	
}
