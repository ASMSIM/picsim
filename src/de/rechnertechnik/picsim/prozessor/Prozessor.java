package de.rechnertechnik.picsim.prozessor;

import java.io.IOException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.rechnertechnik.picsim.commands.PIC_Befehle;
import de.rechnertechnik.picsim.commands.BefehlAdressraumZuordnung;
import de.rechnertechnik.picsim.commands.ECommands;
import de.rechnertechnik.picsim.gui.IGUI;
import de.rechnertechnik.picsim.gui.IProzessor;
import de.rechnertechnik.picsim.logger.PIC_Logger;
import de.rechnertechnik.picsim.parser.Parser;
import de.rechnertechnik.picsim.register.SpecialFunctionRegister;
import de.rechnertechnik.picsim.register.Statusregister;

public class Prozessor implements Runnable, IProzessor {

	private boolean stopProgram = false;
	private boolean breakpoint = false;
	private BefehlAdressraumZuordnung cmdTable;
	private Programmspeicher programmSpeicher;
	private Speicher ram;
	private IGUI gui;

	/**
	 * Programmcounter
	 */
	private Speicherzelle pc;
	private Speicherzelle status;
	private SpecialFunctionRegister w = new SpecialFunctionRegister(0);
	private Parser parser;
	private Stack<Integer> stack = new Stack<Integer>();
	
	
	

	public Prozessor(BefehlAdressraumZuordnung cmdTable, Programmspeicher programmSpeicher, Speicher ram, IGUI gui, Parser parser) {
		this.cmdTable = cmdTable;
		this.programmSpeicher = programmSpeicher;
		this.ram = ram;
		this.gui = gui;
		this.parser = parser;

		status = ram.getStatus(true);
		pc = ram.getPCL(true);

		// Init und Fokus auf 1. Zeile
		gui.showSourcecode(parser.getSourceLine(), parser.getCommand_source_line().get(0));

		Thread runProgram = new Thread(this);
		runProgram.start();
	}

	/**
	 * Enthält die Befehlsverarbeitunsschleife
	 */
	@Override
	public void run() {

		// INIT
		while(!stopProgram) {

			while(breakpoint) {
				try {
					Thread.sleep(200);
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
			}

			Integer akt_Befehl = nextCommand();

			PIC_Logger.logger.log(Level.INFO, "Next command: 0x"
					+ Integer.toHexString(akt_Befehl));

			
			//GOTO
			if(isIntegerCommand(ECommands.GOTO, akt_Befehl)) {
				System.out.println("GOTO");
				setPCL(PIC_Befehle.asm_goto(akt_Befehl));
			}

			//ADDWF
			else if(isIntegerCommand(ECommands.ADDWF, akt_Befehl)) {
				System.out.println("ADDWF");
				PIC_Logger.logger.info("-> ADDWF");
				PIC_Befehle.asm_addwf(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.ANDWF, akt_Befehl)) {
				System.out.println("ANDWF");
				PIC_Logger.logger.info("-> ANDWF");
				PIC_Befehle.asm_andwf(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.CLRF, akt_Befehl)) {
				System.out.println("CLRF");
				PIC_Logger.logger.info("-> CLRF");
				PIC_Befehle.asm_clrf(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.CLRW, akt_Befehl)) {
				System.out.println("CLRW");
				PIC_Logger.logger.info("-> CLRW");
				PIC_Befehle.asm_clrw(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.COMF, akt_Befehl)) {
				PIC_Logger.logger.info("-> COMF");
				PIC_Befehle.asm_comf(akt_Befehl, this);
				System.out.println("COMF");
			}

			else if(isIntegerCommand(ECommands.DECF, akt_Befehl)) {
				System.out.println("DECF");
				PIC_Logger.logger.info("-> DECF");
				PIC_Befehle.asm_decf(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.DECFSZ, akt_Befehl)) {
				System.out.println("DECFSZ");
				PIC_Logger.logger.info("-> DECFSZ");
				PIC_Befehle.asm_decfsz(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.INCF, akt_Befehl)) {
				System.out.println("INCF");
				PIC_Logger.logger.info("-> INCF");
				PIC_Befehle.asm_incf(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.INCFSZ, akt_Befehl)) {
				System.out.println("INCFSZ");
				PIC_Logger.logger.info("-> INCFSZ");
				PIC_Befehle.asm_incfsz(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.IORWF, akt_Befehl)) {
				System.out.println("IORWF");
				PIC_Logger.logger.info("-> IORWF");
				PIC_Befehle.asm_iorwf(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.MOVF, akt_Befehl)) {
				System.out.println("MOVF");
				PIC_Logger.logger.info("-> MOVF");
				PIC_Befehle.asm_movf(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.MOVWF, akt_Befehl)) {
				System.out.println("MOVWF");
				PIC_Befehle.asm_movwf(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.NOP, akt_Befehl)) {
				System.out.println("NOP");
				PIC_Logger.logger.info("-> NOP");
				PIC_Befehle.asm_nop(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.RLF, akt_Befehl)) {
				System.out.println("RLF");
				PIC_Logger.logger.info("-> RLF");
				PIC_Befehle.asm_rlf(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.RRF, akt_Befehl)) {
				System.out.println("RRF");
			}

			else if(isIntegerCommand(ECommands.SUBWF, akt_Befehl)) {
				System.out.println("SUBWF");
			}

			else if(isIntegerCommand(ECommands.SWAPF, akt_Befehl)) {
				System.out.println("SWAPF");
				PIC_Befehle.asm_swapf(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.XORWF, akt_Befehl)) {
				System.out.println("XORWF");
				PIC_Befehle.asm_xorwf(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.BCF, akt_Befehl)) {
				System.out.println("BCF");
				PIC_Befehle.asm_bcf(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.BSF, akt_Befehl)) {
				System.out.println("BSF");
				PIC_Befehle.asm_bsf(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.BTFSC, akt_Befehl)) {
				System.out.println("BTFSC");
				PIC_Befehle.asm_btfsc(akt_Befehl, this);
				
			}

			else if(isIntegerCommand(ECommands.BTFSS, akt_Befehl)) {
				System.out.println("BTFSS");
				PIC_Befehle.asm_btfss(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.ADDLW, akt_Befehl)) {
				System.out.println("ADDLW");
				PIC_Befehle.asm_addlw(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.ANDLW, akt_Befehl)) {
				System.out.println("ANDLW");
				PIC_Befehle.asm_andlw(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.CALL, akt_Befehl)) {
				System.out.println("CALL");
				PIC_Befehle.asm_call(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.CLRWDT, akt_Befehl)) {
				System.out.println("CLRWDT: TODO");
				PIC_Befehle.asm_clrwdt(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.IORLW, akt_Befehl)) {
				System.out.println("IORLW");
				PIC_Befehle.asm_iorlw(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.MOVLW, akt_Befehl)) {
				System.out.println("MOVLW");
				PIC_Befehle.asm_movlw(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.RETFIE, akt_Befehl)) {
				System.out.println("RETFIE: TODO");
			}

			else if(isIntegerCommand(ECommands.RETLW, akt_Befehl)) {
				System.out.println("RETLW");
				PIC_Befehle.asm_retlw(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.RETURN, akt_Befehl)) {
				System.out.println("RETURN");
				PIC_Befehle.asm_return(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.SLEEP, akt_Befehl)) {
				System.out.println("SLEEP: TODO");
				PIC_Befehle.asm_sleep(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.SUBLW, akt_Befehl)) {
				System.out.println("SUBLW");
				PIC_Befehle.asm_sublw(akt_Befehl, this);
			}

			else if(isIntegerCommand(ECommands.XORLW, akt_Befehl)) {
				System.out.println("XORLW");
				PIC_Befehle.asm_xorlw(akt_Befehl, this);
			}

			else {
				System.out.println("Befehl nicht implementiert !");
				this.stopProgram = true;
			}

			breakpoint = true;
		}

	}

	public void incPC() {
		setPCL(pc.getValue() + 1);
	}

	public Speicher getRam() {
		return ram;
	}

	public SpecialFunctionRegister getW() {
		return w;
	}

	public Speicherzelle getStatus() {
		return status;
	}

	public void setStatus(Integer value) {
		try {
			this.status.setWert(value);
		}
		catch(MemoryOutOfRangeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	public Stack<Integer> getStack() {
		return stack;
	}
	
	
	public Speicherzelle getPc() {
		return pc;
	}
	
	/**
	 * Sets the PCL
	 * 
	 * @param value
	 */
	private void setPCL(Integer value) {

		try {
			pc.setWert(value);
		}
		catch(MemoryOutOfRangeException e) {
			System.err.println("Upps Memory Overflow!");
			e.printStackTrace();
		}
	}

	/**
	 * Testet, ob einen Integerwert auf einen bestimmten Befehl. Liefert true,
	 * wenn der Wert im Befehl liegt
	 * 
	 * @param testing_command
	 * @param intCommand
	 * @return
	 */
	private boolean isIntegerCommand(ECommands testing_command, Integer intCommand) {
		if(intCommand >= cmdTable.getAssemblerCommand().get(testing_command).getFrom()) {
			if(intCommand <= cmdTable.getAssemblerCommand().get(testing_command).getTo()) {
				return true;
			}
		}
		return false;

	}


	/**
	 * Holt den naechsten Befehl, auf den der PCL zeigt
	 * @return
	 */
	private Integer nextCommand() {
		Integer next = programmSpeicher.getZelle(pc.getValue()).getValue();

		gui.setFocus(parser.getCommand_source_line().get(pc.getValue()));

		return next;
	}

	@Override
	public void nextStep() {
		breakpoint = false;
	}
}
