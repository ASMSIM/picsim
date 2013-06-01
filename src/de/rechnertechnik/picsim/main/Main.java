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
import de.rechnertechnik.picsim.speicher.Speicher;

public class Main {

	private static Parser parser;
	private static Prozessor prozessor;
	private static PIC_Logger logger;
	private static BefehlAdressraumZuordnung commandTable;
	private static Programmspeicher programmSpeicher;
	private static Speicher ram;

	public static void main(String[] args) {

		
		System.out.println("#############################");
		System.out.println("######## PICSIMULATOR #######");
		System.out.println("#############################");

		// Init
		GUI gui = new GUI();
		PIC_Logger.initLogger("logfile.txt");
		commandTable = new BefehlAdressraumZuordnung();
		parser = new Parser(); // TODO mit GUI öffnen
												// verknüpfen
		programmSpeicher = new Programmspeicher(parser); // Legt den
															// Programmspeicher
															// an und befüllt
															// diesen mit dem
															// Programmcode

		ram = new Speicher();
		// programmSpeicher.printDump();
		prozessor = new Prozessor(commandTable, programmSpeicher, ram, gui, parser);

		// GUI öffnen
		showGUI(gui, prozessor);
		gui.connectProzessor(prozessor,prozessor);	//Interfaceverknüpfung

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
