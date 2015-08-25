package ysitd.ircbot.java.api;
	
class Permision implements Cancelable{
	
	boolean cancel;
	
	@Override
	public boolean isCanceled() {
		return cancel;
	}
	
	@Override
	public void setCanceled(Boolean is) {
		cancel=is;
	}
	
}
