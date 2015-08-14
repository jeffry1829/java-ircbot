package irc.bot;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class staticMethod {
	static void invokeOverrideEvent(CustomEvent e){
		for(  JIRCBOTListener event :  JIRCBOTPlugin.jircbotlistenerlist ){
			Method[] methods=event.getClass().getMethods();
			for( Method method : methods ){
				if( method.getParameterTypes().equals(e) ){ //為什麼沒有contains?
					try {
						method.invoke(e); //觸發了自己寫的Method
					} catch (IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
}
