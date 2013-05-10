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

import de.rechnertechnik.picsim.commands.Adressraum;
import de.rechnertechnik.picsim.commands.BefehlAdressraumZuordnung;
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
		BefehlAdressraumZuordnung cmd = new BefehlAdressraumZuordnung();

		cmd.getAssemblerCommand().put(ECommands.ADDWF, new Adressraum(0x0700, 0x07FF));
		cmd.getAssemblerCommand().put(ECommands.ANDWF, new Adressraum(0x0500, 0x05FF));
		cmd.getAssemblerCommand().put(ECommands.CLRF, new Adressraum(0x0180, 0x01FF));
		cmd.getAssemblerCommand().put(ECommands.CLRW, new Adressraum(0x0100, 0x017F));		
		cmd.getAssemblerCommand().put(ECommands.COMF, new Adressraum(0x0900, 0x09FF));
		cmd.getAssemblerCommand().put(ECommands.DECF, new Adressraum(0x0300, 0x03FF));
		cmd.getAssemblerCommand().put(ECommands.DECFSZ, new Adressraum(0x0B00, 0x0BFF));
		cmd.getAssemblerCommand().put(ECommands.INCF, new Adressraum(0x0A00, 0x0AFF));
		cmd.getAssemblerCommand().put(ECommands.INCFSZ, new Adressraum(0x0F00, 0x0FFF));
		cmd.getAssemblerCommand().put(ECommands.IORWF, new Adressraum(0x0400, 0x04FF));
		cmd.getAssemblerCommand().put(ECommands.MOVF, new Adressraum(0x0800, 0x08FF));
		cmd.getAssemblerCommand().put(ECommands.MOVWF, new Adressraum(0x0080, 0x00FF));
		cmd.getAssemblerCommand().put(ECommands.NOP, new Adressraum(0x0000, 0x0060));		// !!!!!
		cmd.getAssemblerCommand().put(ECommands.RLF, new Adressraum(0x0D00, 0x0DFF));	
		cmd.getAssemblerCommand().put(ECommands.RRF, new Adressraum(0x0C00, 0x0CFF));
		cmd.getAssemblerCommand().put(ECommands.SUBWF, new Adressraum(0x0200, 0x02FF));
		cmd.getAssemblerCommand().put(ECommands.SWAPF, new Adressraum(0x0E00, 0x0EFF));
		cmd.getAssemblerCommand().put(ECommands.XORWF, new Adressraum(0x0600, 0x06FF));
		
		cmd.getAssemblerCommand().put(ECommands.BCF, new Adressraum(0x1000, 0x13FF));
		cmd.getAssemblerCommand().put(ECommands.BSF, new Adressraum(0x1400, 0x17FF));
		cmd.getAssemblerCommand().put(ECommands.BTFSC, new Adressraum(0x1800, 0x1BFF));
		cmd.getAssemblerCommand().put(ECommands.BTFSS, new Adressraum(0x1C00, 0x1FFF));
		
		cmd.getAssemblerCommand().put(ECommands.ADDLW, new Adressraum(0x3E00, 0x3FFF));
		cmd.getAssemblerCommand().put(ECommands.ANDLW, new Adressraum(0x3900, 0x39FF));
		cmd.getAssemblerCommand().put(ECommands.CALL, new Adressraum(0x2000, 0x27FF));
		cmd.getAssemblerCommand().put(ECommands.CLRWDT, new Adressraum(0x0064, 0x0064)); 
		cmd.getAssemblerCommand().put(ECommands.GOTO, new Adressraum(0x2800, 0x2FFF));
		cmd.getAssemblerCommand().put(ECommands.IORLW, new Adressraum(0x3800, 0x38FF));
		cmd.getAssemblerCommand().put(ECommands.MOVLW, new Adressraum(0x3000, 0x33FF));
		cmd.getAssemblerCommand().put(ECommands.RETFIE, new Adressraum(0x0009, 0x0009));
		cmd.getAssemblerCommand().put(ECommands.RETLW, new Adressraum(0x3400, 0x37FF));
		cmd.getAssemblerCommand().put(ECommands.RETURN, new Adressraum(0x0008, 0x0008));
		cmd.getAssemblerCommand().put(ECommands.SLEEP, new Adressraum(0x0063, 0x0063));
		cmd.getAssemblerCommand().put(ECommands.SUBLW, new Adressraum(0x3C00, 0x3DFF));
		cmd.getAssemblerCommand().put(ECommands.XORLW, new Adressraum(0x3A00, 0x3AFF));
		
		// int in_befehl = 0x2817;

		int in_befehl = 0x0A84;

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
					System.out.println("ADDWF");
					//TODO ADDWF
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.ANDWF).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.ANDWF).getTo()){
					System.out.println("ANDWF");
					//TODO ANDWF
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.CLRF).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.CLRF).getTo()){
					System.out.println("CLRF");
					//TODO CLRF
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.CLRW).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.CLRW).getTo()){
					System.out.println("CLRW");
					//TODO CLRW
				}
			}

			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.COMF).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.COMF).getTo()){
					System.out.println("COMF");	
					//TODO COMF
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.DECF).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.DECF).getTo()){
					System.out.println("DECF");
					//TODO DEC
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.DECFSZ).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.DECFSZ).getTo()){
					System.out.println("DECFSZ");
					//TODO DECFSZ
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.INCF).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.INCF).getTo()){
					System.out.println("INCF");
					//TODO INCF
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.INCFSZ).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.INCFSZ).getTo()){
					System.out.println("INCFSZ");
					//TODO INCFSZ
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.IORWF).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.IORWF).getTo()){
					System.out.println("IORWF");
					//TODO IORWF
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.MOVF).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.MOVF).getTo()){
					System.out.println("MOVF");
					//TODO MOVF
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.MOVWF).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.MOVWF).getTo()){
					System.out.println("MOVWF");
					//TODO MOVWF
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.NOP).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.NOP).getTo()){
					System.out.println("NOP");
					//TODO NOP
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.RLF).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.RLF).getTo()){
					System.out.println("RLF");
					//TODO RLF
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.RRF).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.RRF).getTo()){
					System.out.println("RRF");
					//TODO RRF
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.SUBWF).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.SUBWF).getTo()){
					System.out.println("SUBWF");
					//TODO SUBWF
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.SWAPF).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.SWAPF).getTo()){
					System.out.println("SWAPF");
					//TODO SWAPF
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.XORWF).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.XORWF).getTo()){
					System.out.println("XORWF");
					//TODO XORWF
				}
			}
	
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.BCF).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.BCF).getTo()){
					System.out.println("BCF");
					//TODO BCF
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.BSF).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.BSF).getTo()){
					System.out.println("BSF");
					//TODO BSF
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.BTFSC).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.BTFSC).getTo()){
					System.out.println("BTFSC");
					//TODO BTFSC
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.BTFSS).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.BTFSS).getTo()){
					System.out.println("BTFSS");
					//TODO BTFSS
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.ADDLW).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.ADDLW).getTo()){
					System.out.println("ADDLW");
					//TODO ADDLW
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.ANDLW).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.ANDLW).getTo()){
					System.out.println("ANDLW");
					//TODO ANDLW
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.CALL).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.CALL).getTo()){
					System.out.println("CALL");
					//TODO CALL
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.CLRWDT).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.CLRWDT).getTo()){
					System.out.println("CLRWDT");
					//TODO CLRWDT
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.IORLW).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.IORLW).getTo()){
					System.out.println("IORLW");
					//TODO IORLW
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.MOVLW).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.MOVLW).getTo()){
					System.out.println("MOVLW");
					//TODO MOVLW
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.RETFIE).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.RETFIE).getTo()){
					System.out.println("RETFIE");
					//TODO RETFIE
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.RETLW).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.RETLW).getTo()){
					System.out.println("RETLW");
					//TODO RETLW
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.RETURN).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.RETURN).getTo()){
					System.out.println("RETURN");
					//TODO RETURN
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.SLEEP).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.SLEEP).getTo()){
					System.out.println("SLEEP");
					//TODO SLEEP
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.SUBLW).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.SUBLW).getTo()){
					System.out.println("SUBLW");
					//TODO SUBLW
				}
			}
			
			if(in_befehl >= cmd.getAssemblerCommand().get(ECommands.XORLW).getFrom()){
				if(in_befehl <= cmd.getAssemblerCommand().get(ECommands.XORLW).getTo()){
					System.out.println("XORLW");
					//TODO XORLW
				}
			}
		
			in_befehl++;
//		}


	}

}
