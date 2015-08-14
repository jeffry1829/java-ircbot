package irc.bot;

interface Cancelable {
	boolean isCanceled();
	void setCanceled(Boolean is);
}
