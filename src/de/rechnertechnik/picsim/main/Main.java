package de.rechnertechnik.picsim.main;

import de.rechnertechnik.picsim.commands.*;
import de.rechnertechnik.picsim.logger.PIC_Logger;
import de.rechnertechnik.picsim.parser.Parser;
import de.rechnertechnik.picsim.prozessor.Prozessor;
import de.rechnertechnik.picsim.prozessor.Speicher;

public class Main {

	private static Parser parser;
	private static Prozessor prozessor;
	private static PIC_Logger logger;
	private static CommandTable commandTable;
	private static Speicher memory;
	
	
	
	
	public static void main(String[] args) {

		System.out.println("#############################");
		System.out.println("######## PICSIMULATOR #######");
		System.out.println("#############################");

		//Init
		PIC_Logger.initLogger("logfile.txt");
		commandTable = new CommandTable();
		parser = new Parser("res/BA_Test.LST");
		memory = new Speicher(parser);					//Legt den Speicher an
		prozessor = new Prozessor(commandTable, memory);
		
		
		

	}
}
