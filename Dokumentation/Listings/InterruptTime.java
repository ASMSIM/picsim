private void checkTimer0Interrupt(Prozessor cpu, Integer INTCON) {
		boolean T0IF = getBitValue(INTCON, 2);		
		//Is Interrupt?
		if(T0IF){
			//Timerinterrupt
			// Interrupt hat stattgefunden
			PIC_Logger.logger.info("TMR0 Interrupt hat stattgefunden");
			interruptHasOccured(cpu);						
		}	
	}