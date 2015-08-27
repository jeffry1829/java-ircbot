package ysitd.ircbot.java.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class staticMethod {
	
	public static void invokeOverrideEvent(CustomEvent e){
		for(  JIRCBOTListener event :  JIRCBOTPlugin.jircbotlistenerlist ){
			Method[] methods=event.getClass().getMethods();
			for( Method method : methods ){
				if( method.getParameterTypes().equals(e) ){ //為什麼沒有contains?
					try {
						method.invoke(e,e); //觸發了自己寫的Method | invoke(owner,args[]) IMPORTENT EVOLUTION!
					} catch (IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
	
	public static void loadjar() throws ClassNotFoundException, IOException{
		File folder=new File("./");
		JarEntry je;
		Enumeration<JarEntry> e;
		ArrayList<JarFile> jarfile=new ArrayList<JarFile>();
		ClassLoader loader;
		for(File file : folder.listFiles()){
			if( file.getName().matches(".*.jar$") &&
					!file.getName().equals("javaircbot.jar"))
				jarfile.add(new JarFile(file.getAbsolutePath()));
		}
		
		URL[] url=new URL[jarfile.size()];
		for(int i=0;i<jarfile.size();i++){
			url[i]=new URL("jar:file:" + jarfile.get(i).getName() + "!/");
		}
		loader= URLClassLoader.newInstance(url); //resource, close?
		
		for(JarFile j : jarfile){
			e=j.entries();
			while(e.hasMoreElements()){
				 je=e.nextElement();
				 if(je.isDirectory() || !je.getName().endsWith(".class")){
					 continue;
				 }
				 //-6 because of .class
				String classname=je.getName().substring(0, je.getName().length() - 6);
				classname=classname.replace('/', '.');
				try {
					loader.loadClass(classname).newInstance(); //IMPORTENT EVOLUTION!
				} catch (InstantiationException | IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
	}
	
	public static void loadprofile(){
		
		File propertiesfile=new File("./setup.properties");
		if( !propertiesfile.exists() ){
			try {
				propertiesfile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Properties properties=new Properties();
		try {
			properties.load( new FileInputStream(propertiesfile) );
			
			properties.put("server" , "irc.freenode.net");
			properties.put("nickname" , "javabot_ysitd");
			properties.put("channel" , "#ysitd");
			properties.put("port" , "6667");
			properties.put("describe" , "8 * : I am a Bot");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JIRCBOTPlugin.setServername( properties.getProperty("server") );
		JIRCBOTPlugin.setNickname( properties.getProperty("nickname")  );
		JIRCBOTPlugin.setChannel( properties.getProperty("channel")  );
		JIRCBOTPlugin.setPort( properties.getProperty("port")  );
		JIRCBOTPlugin.setDescribe( properties.getProperty("describe")  );
		
	}
	
}
