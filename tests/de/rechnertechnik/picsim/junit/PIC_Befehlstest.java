package de.rechnertechnik.picsim.junit;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.rechnertechnik.picsim.commands.BefehlAdressraumZuordnung;
import de.rechnertechnik.picsim.commands.PIC_Befehle;
import de.rechnertechnik.picsim.gui.GUI;
import de.rechnertechnik.picsim.gui.IProzessor;
import de.rechnertechnik.picsim.logger.PIC_Logger;
import de.rechnertechnik.picsim.parser.Parser;
import de.rechnertechnik.picsim.prozessor.MemoryOutOfRangeException;
import de.rechnertechnik.picsim.prozessor.Programmspeicher;
import de.rechnertechnik.picsim.prozessor.Prozessor;
import de.rechnertechnik.picsim.prozessor.Speicher;
import de.rechnertechnik.picsim.prozessor.Speicherzelle;

public class PIC_Befehlstest {

	private static Parser parser;
	private static Prozessor prozessor;
	private static BefehlAdressraumZuordnung commandTable;
	private static Programmspeicher programmSpeicher;
	private static Speicher ram;
	private static PIC_Logger logger;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		

		
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

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getOpcodeFromToBitTest() {
		
		Integer command = 0x2817;
		
		Integer result = PIC_Befehle.getOpcodeFromToBit(command, 0, 10);
		System.out.println(result);
		assertTrue("Befehl 0x2817 erwarte k=23 ...true", result==23);
		
		command = 0x303f;
		result = PIC_Befehle.getOpcodeFromToBit(command, 0, 7);
		System.out.println(Integer.toHexString(result));
		assertTrue("Befehl 0x3f erwarte k=3f ...true", result==0x3f);
	}

	public void asm_swapf() {
		
		//Akkutest
		Speicherzelle zelle = new Speicherzelle(0x1f);
		
		PIC_Befehle.asm_swapf(0x0600, prozessor);
		assertEquals(0xF1, (int)prozessor.getW());
		
		
		//Registertest
		zelle.setWert(0x1F);
		
		PIC_Befehle.asm_swapf(0x0680, prozessor);
		assertEquals(0xF1, (int)zelle.getValue());
		
		
	}
	

}
