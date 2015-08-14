package irc.bot;

public class UserCommandProcessEvent implements Cancelable , CustomEvent{
	
	String username , prefix , command;
	String[] argument;
	CommandExecutor ce;
	private boolean canceled;
	
	public UserCommandProcessEvent(String username , String prefix , String command , String[] argument , CommandExecutor ce){
		username=this.username;
		prefix=this.prefix;
		command=this.command;
		argument=this.argument;
		ce=this.ce;
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
			ce.onCommand(username , prefix , command , argument);
		}
		
	}
	
}




