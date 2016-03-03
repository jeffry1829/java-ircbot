package ysitd.ircbot.java.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
	
public class Permission implements Cancelable{
	
	/*
	 * 本來要做出hook(可以取消掉這一個Permission系統的作法)
	 * 但是不知道該cancel什麼
	 * 因此先暫時不進行任何實作部份
	 */
	
	boolean cancel;
	static File permissionfile=new File("./permissins.properties");
	static Properties permission=new Properties();
	
	@SuppressWarnings("deprecation")
	public static void setupPermissionfile(){
		try {
			if( !permissionfile.exists() ){
				permissionfile.createNewFile();
			}
			permission.load(new FileInputStream(permissionfile));
			permission.save(new FileOutputStream(permissionfile) , "setup successfully");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//By String Array
	public static String[] getPermission(String username){
		if(permission.getProperty(username) == null){
			return new String[]{};
		}
		return permission.getProperty(username).split(" ");
		
	}
	
	//By String
	public static String getPermissionByString(String username){
		if(permission.getProperty(username) == null){
			return "";
		}
		return permission.getProperty(username);
	}
	/*
	 * default是一個預設的Permission node的username
	 */
	public static void putPermissionnode(String username , String permissionnode){
		if(getPermission(username).length==0){
			permission.put(username , permissionnode);
		}else if(Permission.contains(username, permissionnode)){
			return;
		}else{
			permission.put(username , permission.getProperty(username) + " " + permissionnode.replaceAll(" ", "")  );
		}
	}
	
	public static void removePermissionnode(String username , String permissionnode){
		permission.put(username, getPermissionByString(username).replace(permissionnode, ""));
	}
	
	@SuppressWarnings("deprecation")
	public static void savePermissions(){
		try {
			permission.save(new FileOutputStream(permissionfile) , "saved");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//會回傳錯誤的版本
	public static boolean contains(String username , String permissionnodestring , String from){
		for(String permissionnode : getPermission(username)){
				if(permissionnode.matches(".*"+permissionnodestring+".*")){
					return true;
				}
		}
		PluginMain.say("申し訳ありません , あなたのアクセスが十分ではありません" , from);
		return false;
	}
	
	//不向聊天室回傳錯誤的版本
	public static boolean contains(String username , String permissionnodestring){
		for(String permissionnode : getPermission(username)){
				if(permissionnode.matches(".*"+permissionnodestring+".*")){
					return true;
				}
		}
		return false;
	}
	
	@Override
	public boolean isCanceled() {
		return cancel;
	}
	
	@Override
	public void setCanceled(Boolean is) {
		cancel=is;
	}
	
}
