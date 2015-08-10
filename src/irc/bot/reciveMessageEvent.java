package irc.bot;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class reciveMessageEvent implements CustomEvent{
	
	 Socket server=JIRCBOTPlugin.getServer();
	
	@Override
	public String getEventName() {
		final String name="reciveMessageEvent";
		return name;
	}

	@Override
	public void Do() {
		
		for(  JIRCBOTListener event :  JIRCBOTPlugin.jircbotlistenerlist ){
			Method[] methods=event.getClass().getMethods();
			for( Method method : methods ){
				if( method.getParameterTypes().equals(this) ){ //為什麼沒有contains?
					try {
						method.invoke(this); //觸發了自己寫的Method
					} catch (IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	
}
