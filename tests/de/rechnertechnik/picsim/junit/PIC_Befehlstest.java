package de.rechnertechnik.picsim.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.rechnertechnik.picsim.commands.PIC_Befehle;

public class PIC_Befehlstest {

	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		
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

	@Test
	private void asm_movwf() {
		
	}
	
	

}
