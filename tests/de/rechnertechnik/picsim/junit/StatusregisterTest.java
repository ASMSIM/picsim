package de.rechnertechnik.picsim.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.rechnertechnik.picsim.register.Statusregister;

public class StatusregisterTest {

	static Statusregister register;
	static int cnt;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		register = new Statusregister(0);
		cnt = 0;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		cnt = 0;
		register.setWert(0);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getZero() {
		if(register.getWert() == 0)
			assertTrue("Init OK", true);

		for(int i = 0; i < 256; i++) {
			register.setWert(i);
			if(register.getZero())
				cnt++;
		}
		if(cnt == 128)
			assertTrue("GetZero Ok", true);
		else {
			assertTrue(false);
		}
	}

	@Test
	public void getCarry() {

		for(int i = 0; i < 256; i++) {
			register.setWert(i);
			if(register.getCarry())
				cnt++;
		}
		if(cnt == 128)
			assertTrue("GetCarry Ok", true);
		else {
			assertTrue(false);
		}
	}

	
	
	
	
	
	
	
}
