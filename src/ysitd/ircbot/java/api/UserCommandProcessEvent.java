package ysitd.ircbot.java.api;

public class UserCommandProcessEvent implements Cancelable , CustomEvent{
	
	/*
	 * @CustomEvent
	 * 從CommandHandler獲取資訊後
	 * 先呼叫實作的Event( Do() )
	 * 再來使用給予的資訊進行呼叫onCommand
	 */
	
	private String username , prefix , from;
	private String[] argument;
	CommandExecutor ce;
	private boolean canceled;
	
	public UserCommandProcessEvent(String msline){
		/*
		 * It is not an Override method
		 * Can while( ( msline=reader.readLine() ) != null) run successfully? infinity loop?
		 * 對話例子/
		 * :petjelinux!~petjelinu@61-230-157-131.dynamic.hinet.net PRIVMSG #ysitd :要修還需要修一陣子 , 所以能夠給我一段嗎 包括對話
		 * 01    ~    10   
		 */
		String username,from;
			try{
				if( msline.matches(".* PRIVMSG .*") ){
					from=new String(msline).substring(msline.indexOf("#",1),msline.indexOf(":",1)-1);
					username=new String(msline).substring(1 , msline.indexOf("!" , 1)); //result="petjelinux"
					msline=msline.replaceFirst(":.*?:","");//result="要修還需要修一陣子 , 所以能夠給我一段嗎 包括對話"
					if( msline.startsWith("]") ){
								this.from=from;
								this.username=username;
								this.prefix="]";
								this.argument=msline.replaceFirst("]" , "").split(" ");
								Do();
					}
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
	}
	
	public String getUserName(){
		return username;
	}
	
	public String getFrom(){
		return from;
	}
	
	public String getprefix(){
		return prefix;
	}
	
	public String getcommandName(){
		return argument[0];
	}
	
	@Override
	public boolean isCanceled() {
		return canceled;
	}

	@Override
	public void setCanceled(Boolean is) {
		canceled=is;
	}
	
	@Override
	public String getEventName() {
		final String name="UserCommandProcessEvent";
		return name;
	}

	@Override
	public void Do() {
		
		//invoke overrided event
		staticMethod.invokeOverrideEvent(this);
		
		//3 lines here are used to call overrided onCommand method with data from CommandHandler
		if( !isCanceled() ){
			for(CommandExecutor commandexe : JIRCBOTPlugin.commandlist){
		  		if(commandexe.getName().equals(argument[0])){
					commandexe.onCommand(username , prefix , from , argument);
					return;
				}
			}
		}
	}
	
}




