public void setBitPort(Integer bitNr, Integer adresse){
		Integer tris = get_RAM_Value(adresse+0x80);
		Integer port = get_RAM_Value(adresse);
		
		boolean trisBit =  PIC_Befehle.getBit(tris, bitNr);
		Integer setValue = port | (int)Math.pow(2, bitNr);
		
		//Nur verändern wenn Tris richtig gesetzt ist
		if(trisBit){
			setSpeicherzellenWert(adresse, setValue , false);
		}
	}