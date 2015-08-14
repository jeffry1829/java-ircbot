package ysitd.ircbot.java.api;

interface Cancelable {
	boolean isCanceled();
	void setCanceled(Boolean is);
}
