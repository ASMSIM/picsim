package de.rechnertechnik.picsim.prozessor;

import de.rechnertechnik.picsim.commands.PIC_Befehle;
import de.rechnertechnik.picsim.logger.PIC_Logger;


/**
 * Enthält die Funktionen des Timer/Counter Modules
 * 
 * @author michael
 *
 */
public class Timer_Counter {

	
	private Integer tmr0_prescale[] = { 2, 4, 8, 16, 32, 64, 128, 256 };
	private Integer wdt_prescale[] = { 1, 2, 4, 8, 16, 32, 64, 128 };
	
	
	/**
	 * T0CS: TMR0 Clock Source Select bit
				1 = Transition on RA4/T0CKI pin
				0 = Internal instruction cycle clock (CLKOUT)
	 */
	private boolean T0CS = true;
	
	/**
	 * T0SE: TMR0 Source Edge Select bit
				1 = Increment on high-to-low transition on RA4/T0CKI pin
				0 = Increment on low-to-high transition on RA4/T0CKI pin
	 */
	private boolean T0SE = true;
	
	
	/**
	 * PSA: Prescaler Assignment bit
				1 = Prescaler is assigned to the WDT
				0 = Prescaler is assigned to the Timer0 module
	 * 
	 */
	private boolean PSA = true;
	private Integer PS_VALUE = 0;				//Scalervalue von 0 - 7
	
	private Integer prescaler = 0;				
	
	private boolean oldVal= false;			//Speicher für Flankenerkennung
	private boolean portAChanged = false;		//Änderung erst bei nächstem Simulatorstep
	
	private Prozessor cpu;
	
	public Timer_Counter(Prozessor cpu) {
		this.cpu = cpu;
	}
	
	/**
	 * Optionen neu setzen
	 * 
	 * @param optenRegisterValue
	 */
	public void modifyOptions(Integer optenRegisterValue) {
		T0CS = getBitValue(optenRegisterValue, 5);
		T0SE = getBitValue(optenRegisterValue, 4);
		PSA = getBitValue(optenRegisterValue, 3);
		PS_VALUE = PIC_Befehle.getOpcodeFromToBit(optenRegisterValue, 0, 2);
		
		cpu.showPrescalerOnGui(tmr0_prescale[PS_VALUE]);
		String logstring = "[TIMER/COUNTER]: ";
		PIC_Logger.logger.info(logstring+"T0CS="+T0CS+"; T0SE="+T0SE+"; PSA="+PSA+"; PS2:PS0="+PS_VALUE);
	}
	
	
	
	
	/**
	 * Setzt den Prescalerwert nach dem Datenblatt
	 * 
	 * von 0 - 7
	 * 
	 * @param optionValue
	 */
	public void setPrescaler(Integer optionValue){
		PS_VALUE = optionValue;
	}

	
	/**
	 * Wird vom Prozessor nach jedem Cycle aufgerufen
	 * 
	 * Es wird nur hochgezählt, falls die passenden Bits gesetzt sind
	 */
	public void time() {
		
		//Wenn TC als Timer gesetzt
		if(!T0CS){
			increment();
		}
		
		if(portAChanged){
			portAChanged = false;
			
			//TC als Counter gesetzt
			if(T0CS){
				PIC_Logger.logger.info("TC als Counter");
				
				Integer PortA = cpu.get_RAM_Value(0x05);
				boolean RA4 = getBitValue(PortA, 4);
				
				//HIGH-LOW
				if(T0SE && oldVal==true && RA4 == false){
					PIC_Logger.logger.info("[COUNTER]: Detected HIGH-LOW");
					increment();
				}
				//LOW-HIGH
				else if(!T0SE && oldVal==false && RA4 == true){
					PIC_Logger.logger.info("[COUNTER]: Detected LOW-HIGH");
					increment();
				}
				
				//NOTHING
				else{
					PIC_Logger.logger.info("Nothing detected");
				}
				
				oldVal = RA4;
				}
			
		}
		
	}
	
	/**
	 * Für Externen Takt benutzt
	 * @param cpu
	 */
	public void changedPortA(Prozessor cpu){
		PIC_Logger.logger.info("CHANGEDPORTA");
		portAChanged = true;
	}
	
	
	
	
	
	/**
	 * Überprüft prescaler Konditionen
	 */
	private void increment() {
		
		//Prescaler erhält zuordnung zum Timer
		if(!PSA){
			PIC_Logger.logger.info("[TIMER/COUNTER]: Timer+Prescaler");
			prescaler++;
		
			//Prescaler Overflow?
			if(prescaler == tmr0_prescale[PS_VALUE]){
				prescaler = 0;	//Clear Prescaler
				
				Integer oldTMR0 = cpu.get_RAM_Value(0x01);
				cpu.setSpeicherzellenWert(0x01, oldTMR0+1, false);
				
			}
		}
		
		//Keine Zuordnung zum Timer des Prescalers
		else{
			PIC_Logger.logger.info("[TIMER/COUNTER]: Timer ohne Prescaler");
			Integer oldTMR0 = cpu.get_RAM_Value(0x01);
			cpu.setSpeicherzellenWert(0x01, oldTMR0+1, false);
		}
		
	}
	
	
	
	private static boolean getBitValue(Integer value, Integer bitNr) {
		return  ((value & ( 1 << bitNr) ) == (1 << bitNr)) ? true : false;
	}
	
	
	
}
