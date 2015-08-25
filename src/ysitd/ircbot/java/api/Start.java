package ysitd.ircbot.java.api;

/**
 * 創見於 2015/8/8
 */
public class Start {
	public static void main(String[] args){
		JIRCBOTPlugin MPlugin=new JIRCBOTPlugin("irc.freenode.net","javabot","#ysitd",6777,"Bot da yo?");
		MPlugin.onEnable();
	}
}
