package de.rechnertechnik.picsim.commands;

import java.util.HashMap;
import de.rechnertechnik.*; 


public class Commands {

	private ECommands command;
	private HashMap<ECommands, CommandRange> assemblerCommand = new HashMap<ECommands, CommandRange>();

	public Commands(ECommands command) {
		this.command = command;
	}

	public ECommands getCommand() {
		return command;
	}

	public void setCommand(ECommands command) {
		this.command = command;
	}

	public HashMap<ECommands, CommandRange> getAssemblerCommand() {
		return assemblerCommand;
	}

}
