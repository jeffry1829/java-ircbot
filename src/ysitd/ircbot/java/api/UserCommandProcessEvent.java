package ysitd.ircbot.java.api;

public class UserCommandProcessEvent implements Cancelable , CustomEvent{
	
	/*
	 * @CustomEvent
	 * 從CommandHandler獲取資訊後
	 * 先呼叫實作的Event( Do() )
	 * 再來使用給予的資訊進行呼叫onCommand
	 */
	
	private String username , prefix , from;
	private String[] argument,option;
	CustomCommandExecutor ce;
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
					msline=msline.replaceFirst(":.*#.*?:","");//result="要修還需要修一陣子 , 所以能夠給我一段嗎 包括對話"
					//我猜這樣就可以了
					if( msline.startsWith("]") ){
								this.from=from;
								this.username=username;
								this.prefix="]";
								this.argument=msline.replaceFirst("]" , "").split(" "); //包含指令的名稱
								this.option=processOption(this.argument);
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
	
	public String[] processOption(String[] args){ //順便修正了this.argument... 感覺寫得有點醜
		String[] result=new String[args.length];
		String preresult="";
		int null_count=0;
		boolean clean=false,pastis=false; //if clean => remove this element, if pastis => clean=true;
		for(int i=0;i<args.length;i++){
			String element = args[i];
			clean = true;
			if(element.matches("/^-[^-]/g")){
				preresult+=element.replaceFirst("-", "");
			}else if(element.matches("/^--[^-]/g")){
				preresult+=element.replaceFirst("--", "");
			}else{
				clean = false;
			}
			if(pastis || clean){
				args[i] = "";
				null_count+=1;
			}
			if(element.matches("/^-.*-$/g")){
				pastis=true;
				String arg = args[i+1].matches("/^:/g") ? "\\" + args[i+1] : args[i+1];
				preresult+=(":"+arg);
			}else{
				pastis=false;
			}
			result[i] = preresult;
		}
		String[] replacement_array = new String[args.length-null_count];
		int replace_now_index=0;
		for(String element : args){
			if(!element.equalsIgnoreCase("")){
				replacement_array[replace_now_index] = element;
				replace_now_index+=1;
			}
		}
		this.argument = replacement_array; //在這裡更改!
		return result;
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
		StaticMethods.invokeOverrideEvent(this);
		
		//3 lines here are used to call overrided onCommand method with data from CommandHandler
		if( !isCanceled() ){
			for(CustomCommandExecutor commandexe : PluginMain.commandlist){
		  		if(commandexe.getName().equals(argument[0])){
					commandexe.onCommand(username , prefix , from , argument , option);
					return;
				}
			}
		}
	}
	
}




