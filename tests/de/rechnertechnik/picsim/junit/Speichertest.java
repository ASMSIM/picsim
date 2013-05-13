package de.rechnertechnik.picsim.junit;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.rechnertechnik.picsim.prozessor.MemoryOutOfRangeException;
import de.rechnertechnik.picsim.prozessor.Speicher;
import de.rechnertechnik.picsim.prozessor.Speicherzelle;
import de.rechnertechnik.picsim.prozessor.Speicherzelle.bits;

public class Speichertest {

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
	public void testSpeicherVerwaltung() {
		Speicher speicher = new Speicher();

		int anz = speicher.getAnzZellen();

		//Kompletter Speicher erstellt?
		if (anz != Speicher.SPEICHERGROESSE)
			assertFalse(true);

		//Speicher richtig initialisiert?
		for (int i = 0; i < Speicher.SPEICHERGROESSE; i++) {
			if (speicher.getZelle(i).getValue() != 0) {
				assertFalse(true);
			}
		}
		
		
		try {
			speicher.setZelle(0, 15);
			speicher.setZelle(1, 40);
			
			if(speicher.getZelle(0).getValue() != 15){
				assertFalse(true);
			}
			if(speicher.getZelle(1).getValue() != 40){
				assertFalse(true);
			}


		} catch (MemoryOutOfRangeException e) {
			e.printStackTrace();
			assertFalse(true);			
		}
		
		
		
		

	}
	
	@Test
	public void testSetBit() throws MemoryOutOfRangeException{
		Speicherzelle zelle = new Speicherzelle(0xFF);
		zelle.clearBit(0);
		assertEquals((int)0xFE, (int)zelle.getValue());
		zelle.clearBit(7);
		assertEquals((int)0x7E, (int)zelle.getValue());
		zelle.clearBit(1);
		assertEquals((int)0x7C, (int)zelle.getValue());
		
		zelle.setWert(0xff);
		zelle.clearBit(bits.Z);
		assertEquals((int)0xFB,(int)zelle.getValue());
		zelle.setBit(bits.Z);
		assertEquals((int)0xFF,(int)zelle.getValue());
		
	}


}
