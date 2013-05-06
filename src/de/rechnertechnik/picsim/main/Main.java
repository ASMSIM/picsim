package de.rechnertechnik.picsim.main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import de.rechnertechnik.picsim.commands.*;
import de.rechnertechnik.picsim.gui.GUI;
import de.rechnertechnik.picsim.gui.IProzessor;
import de.rechnertechnik.picsim.logger.PIC_Logger;
import de.rechnertechnik.picsim.parser.Parser;
import de.rechnertechnik.picsim.prozessor.Programmspeicher;
import de.rechnertechnik.picsim.prozessor.Prozessor;
import de.rechnertechnik.picsim.prozessor.Speicher;

public class Main {

	private static Parser parser;
	private static Prozessor prozessor;
	private static PIC_Logger logger;
	private static CommandTable commandTable;
	private static Programmspeicher programmSpeicher;
	private static Speicher ram;

	public static void main(String[] args) {

		System.out.println("#############################");
		System.out.println("######## PICSIMULATOR #######");
		System.out.println("#############################");

		GUI gui = new GUI();
		
		// Init
		PIC_Logger.initLogger("logfile.txt");
		commandTable = new CommandTable();
		parser = new Parser("res/BA_Test.LST");
		programmSpeicher = new Programmspeicher(parser); // Legt den
															// Programm-Speicher
															// an
		ram = new Speicher();
		programmSpeicher.printDump();
		prozessor = new Prozessor(commandTable, programmSpeicher, ram, gui , parser);

		// GUI öffnen
		showGUI(gui, prozessor);
		gui.connectProzessor(prozessor);

	}

	/**
	 * Init the GUI with Size and Position
	 */
	private static void showGUI(GUI gui, IProzessor prozessor) {
	

		// Zentrierung / Breite & Hoehe des Fensters
		Dimension main_window_size = new Dimension(1000, 700);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int top = (screenSize.height - main_window_size.height) / 2;
		int left = (screenSize.width - main_window_size.width) / 2;

		gui.setSize(main_window_size);
		gui.setLocation(left, top);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);
	}
}
