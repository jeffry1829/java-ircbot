package ysitd.ircbot.java.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
//import org.apache.jmeter.DynamicClassLoader;

public class StaticMethods {
	
	public static void invokeOverrideEvent(CustomEvent e){
		for(  PluginListener event :  PluginMain.jircbotlistenerlist ){
			Method[] methods=event.getClass().getDeclaredMethods();
			for( Method method : methods ){
				if( method.getParameterTypes().length>0 && method.getParameterTypes()[0].isInstance(e) ){ //fixed
					try {
						method.invoke(event,e); //觸發了自己寫的Method | invoke(owner,args[]) IMPORTENT EVOLUTION!
					} catch (IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
	//更改到./plugins資料夾裡, 以避免不必要的判斷
	public static void loadjar() throws ClassNotFoundException, IOException{
		File folder=new File("./plugins");
		if(!folder.exists()){
			folder.mkdirs();
		}
		JarEntry je;
		Enumeration<JarEntry> e;
		ArrayList<JarFile> jarfile=new ArrayList<JarFile>();
		for(File file : folder.listFiles()){
			if(file.getName().matches(".*.jar$"))
				jarfile.add(new JarFile(file.getAbsolutePath()));
		}
		
		URL[] url=new URL[jarfile.size()];
		for(int i=0;i<jarfile.size();i++){
			url[i]=new URL("jar:file:" + jarfile.get(i).getName() + "!/");
		}
		ClassLoader loader = URLClassLoader.newInstance(url); //resource, close?
		//DynamicClassLoader loader = new DynamicClassLoader(url);
		//DynamicClassLoader.newInstance(url);
		
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
					e1.printStackTrace();
				}
			}
		}
		
	}
	
	@SuppressWarnings("deprecation")
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
			if( properties.isEmpty() ){
				properties.put("server" , "irc.freenode.net");
				properties.put("nickname" , "javabot_ysitd");
				properties.put("channel" , "#ysitd");
				properties.put("port" , "6667");
				properties.put("describe" , "8 * : I am a Bot");
				properties.put("username" , "javabot_ysitd");
				properties.put("password" , "botpw");
				properties.save(new FileOutputStream(propertiesfile) , "initial");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		PluginMain.setServername( properties.getProperty("server") );
		PluginMain.setNickname( properties.getProperty("nickname")  );
		PluginMain.setChannel( properties.getProperty("channel")  );
		PluginMain.setPort( Integer.parseInt(properties.getProperty("port"))  );
		PluginMain.setDescribe( properties.getProperty("describe")  );
		PluginMain.setUsername( properties.getProperty("username")  );
		PluginMain.setPassword( properties.getProperty("password")  );
		
		try {
			properties.save(new FileOutputStream(propertiesfile) , "finally");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
}
