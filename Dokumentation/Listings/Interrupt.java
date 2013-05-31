//Check Interrupt in Prozessor Klasse
interruptHandler.checkInterrupt(this);

//in der Interrupt Klasse
public void checkInterrupt(Prozessor cpu) {
		
		Integer INTCON = cpu.get_RAM_Value(0x0b);
		boolean GIE = ( ( INTCON & 0x80) == 0x80 )?true:false;
		boolean INTE = ( ( INTCON & 0x10) == 0x10 )?true:false;
		
		//Global Interrupt enabled
		if(GIE){

			//RB0 Interrupt enabled
			if(INTE){
				PIC_Logger.logger.info("RB0 Interrupt check...");
				
				Integer PortB = cpu.get_RAM_Value(0x06);
				boolean RB0 = ( ( PortB & 0x01) == 0x01 )?true:false;
				
				//TODO rising oder faling edge
				Integer OPTION_REG  = cpu.get_RAM_Value(0x81);
				boolean INTEDG  = ( ( OPTION_REG & 0x40) == 0x40 )?true:false;	//true = rising, false = faling edge
				
					if(oldValue == false && RB0 == true && INTEDG){
						externerInterruptRB0(cpu, INTCON, RB0);
					}
					
					else if(oldValue == true && RB0 == false && !INTEDG){
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