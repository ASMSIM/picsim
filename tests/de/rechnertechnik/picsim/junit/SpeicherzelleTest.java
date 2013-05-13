package de.rechnertechnik.picsim.junit;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.rechnertechnik.picsim.prozessor.MemoryOutOfRangeException;
import de.rechnertechnik.picsim.prozessor.Speicherzelle;

public class SpeicherzelleTest {

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

	public static Integer myRandom(int low, int high) {

		int rand = 0;
		while (rand >= 0 && rand <= 255) {
			rand = (int) (Math.random() * (high - low) + low);
		}
		return rand;

	}

	@Test(expected = MemoryOutOfRangeException.class)
	public void testMemoryOutofRangeException()
			throws MemoryOutOfRangeException {

		Speicherzelle zelle;

		for (int x = 0; x < 100; x++) {

				zelle = new Speicherzelle(myRandom(-5000, 5000));
				assertFalse(true);

		}
		

	}
}
