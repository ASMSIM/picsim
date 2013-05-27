package de.rechnertechnik.picsim.junit;



import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;


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

}
