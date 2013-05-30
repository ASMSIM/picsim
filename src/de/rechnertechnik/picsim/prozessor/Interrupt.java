package de.rechnertechnik.picsim.prozessor;


public class Interrupt {

	private boolean oldValue = false;
	
	
	
	public void checkInterrupt(Prozessor cpu) {
		
		Integer INTCON = cpu.get_RAM_Value(0x0b);
		boolean GIE = ( ( INTCON & 0x80) == 0x80 )?true:false;
		boolean INTE = ( ( INTCON & 0x10) == 0x10 )?true:false;
		
		//Global Interrupt enabled
		if(GIE){
			System.out.println("Interrupt Global aktiv");
			System.out.println(Integer.toHexString(INTCON));
			//RB0 Interrupt enabled
			if(INTE){
				System.out.println("RB0 Interrupt check...");
				
				Integer PortB = cpu.get_RAM_Value(0x06);
				boolean RB0 = ( ( PortB & 0x01) == 0x01 )?true:false;
				
				//TODO rising oder faling edge
				Integer OPTION_REG  = cpu.get_RAM_Value(0x81);
				boolean INTEDG  = ( ( OPTION_REG & 0x40) == 0x40 )?true:false;	//true = rising, false = faling edge
				
					
					//Found rising edge
					if(oldValue == false && RB0 == true && INTEDG){
						System.out.println("Interrupt Rising");
						externerInterruptRB0(cpu, INTCON, RB0);
					}
					
					//Found falling edge
					else if(oldValue == true && RB0 == false && !INTEDG){
						System.out.println("Interrupt Falling");
						externerInterruptRB0(cpu, INTCON, RB0);
					}
					
					//No Interrupt
					else{
					}
				
					//Save old Value
					oldValue = RB0;
					
			}
			
			
		}
		
		
	}



	/**
	 * Wird aufgerufen, wenn ein externer Interrupt stattgefunden hat
	 * @param cpu
	 * @param INTCON
	 * @param RB0
	 */
	private void externerInterruptRB0(Prozessor cpu, Integer INTCON, boolean RB0) {
			//Interrupt hat stattgefunden
			System.out.println("RB0 Interrupt hat stattgefunden");
			cpu.setSpeicherzellenWert(0x0b, (INTCON & 0x7F ), false);	//Disable GIE
			cpu.setSpeicherzellenWert(0x0b, (INTCON | 0x02 ), false);	//Interrupt Flag setzen
			
			Integer pcl = cpu.getPCValue();	//Programmcounter holen
			cpu.getStack().push(pcl);		//PCL auf Stack
			
			cpu.setPCL(0x04);	//Springe zum Interruptvektor			
	}
	
	
	
}
