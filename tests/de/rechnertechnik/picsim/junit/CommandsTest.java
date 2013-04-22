package de.rechnertechnik.picsim.junit;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.rechnertechnik.picsim.commands.CommandRange;
import de.rechnertechnik.picsim.commands.Commands;
import de.rechnertechnik.picsim.commands.ECommands;

public class CommandsTest {

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
	public void test() {
		Commands cmd = new Commands(ECommands.ADDWF);
		cmd.getAssemblerCommand().put(ECommands.GOTO, new CommandRange(0x2800, 0x2FFF));
		cmd.getAssemblerCommand().put(ECommands.ADDWF, new CommandRange(0x0700, 0x07FF));

		
		
		// int in_befehl = 0x2817;

		int in_befehl = 0x2917;

//		HashMap<ECommands, CommandRange> customers = cmd.getAssemblerCommand();
//		Set<ECommands> keys = customers.keySet();
//		System.out.println(cmd.getAssemblerCommand().get(ECommands.GOTO).getFrom());
//		System.out.println(cmd.getAssemblerCommand().get(ECommands.GOTO).getTo());

		
		/**
		 * Teste gesamten Adressraum
		 */
//		for(int i = 0; i < 0xffff; i++) {

				
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.GOTO).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.GOTO).getTo()){
					System.out.println("GOTO");
					System.out.printf("Befehl: %1$x\n",in_befehl);
					int param = (in_befehl & 0x07FF);
					System.out.printf("Param: %1$x\n",param);
					
					//TODO GOTO
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.ADDWF).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.ADDWF).getTo()){
					//TODO GOTO
				}
			}

			in_befehl++;
//		}


	}

}
