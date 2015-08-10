package irc.bot;

interface ICommand {
	String getName();
	void execute();
}
