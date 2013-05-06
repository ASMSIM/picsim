package de.rechnertechnik.picsim.junit;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.rechnertechnik.picsim.prozessor.MemoryOutOfRangeException;
import de.rechnertechnik.picsim.prozessor.Speicher;

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
	public void testSpeicher() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetZelle() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetZelle() {
		fail("Not yet implemented");
	}

}
