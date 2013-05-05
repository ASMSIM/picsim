package de.rechnertechnik.picsim.commands;

import de.rechnertechnik.picsim.prozessor.Prozessor;
import de.rechnertechnik.picsim.stringhex.StringHex;

public class PIC_Befehle {

	/**
	 * GOTO Befehl 0010 1KKK KKKK KKKK
	 */
	public static Integer asm_goto(Integer next_Befehl) {

		String befehl = StringHex.intToHex(next_Befehl);
		String high = befehl.substring(0, 2);
		String low = befehl.substring(2, 4);

		Integer high_int = Integer.parseInt(high, 16);
		Integer low_int = Integer.parseInt(low, 16);

		System.out.println("Befehl: " + befehl);
		System.out.println("High: " + high);
		System.out.println("Low: " + low);

		// K extrahieren
		high_int = high_int & 0x07; // K high extrahieren: 0010 1KKK
		high_int = high_int << 8;

		Integer k = high_int + low_int;
		System.out.println(k);

		return k;
	}

	public static void asm_movlw(Integer befehl, Prozessor cpu) {
		cpu.getW().setWert(getOpcodeFromToBit(befehl, 0, 7)); 
	}

	
	
	
	// TODO implementieren!
	public static Integer getOpcodeFromToBit(Integer opcode, int fromBit, int toBit) {

//		String befehl = StringHex.intToHex(opcode);
//		String high = befehl.substring(0, 2);
//		String low = befehl.substring(2, 4);
//
//		Integer high_int = Integer.parseInt(high, 16);
//		Integer low_int = Integer.parseInt(low, 16);
//
//		System.out.println("Befehl: " + befehl);
//		System.out.println("High: " + high);
//		System.out.println("Low: " + low);

		// Groesser kleiner ;)
		if(fromBit > toBit){
			int tmp = toBit;
			toBit = fromBit;
			fromBit = tmp;
		}
		
		int operand = 0;
		
		for(int i = fromBit; i <= toBit; i++) {
			operand +=  Math.pow(2, i);
		}

		System.out.println(operand);
		
		int k = opcode & 0x3fff;
		k = opcode & operand;
		
		System.out.println("K "+k);
		
		return k;
	}

}
