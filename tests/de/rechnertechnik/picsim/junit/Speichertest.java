package de.rechnertechnik.picsim.junit;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.rechnertechnik.picsim.speicher.Speicher;
import de.rechnertechnik.picsim.speicher.Speicherzelle;
import de.rechnertechnik.picsim.speicher.Speicherzelle.bits;

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
			if(speicher.getValueFromCell(i) != 0) {
				assertFalse(true);
			}
			
			
			
		}
		
		
			speicher.writeValueToCell(0, 15);
			speicher.writeValueToCell(1, 40);
			
			if(speicher.getValueFromCell(0) != 15){
				assertFalse(true);
			}
			if(speicher.getValueFromCell(1) != 40){
				assertFalse(true);
			}


		
		

	}
	
	@Test
	public void testSetBit() {
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
