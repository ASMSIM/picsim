package de.rechnertechnik.picsim.commands;

public class PIC_Befehle {

	/**
	 * GOTO Befehl 0010 1KKK KKKK KKKK
	 */
	public static Integer asm_goto(String next_high, String next_low) {
		System.out.println("H: "+next_high+"h");
		System.out.println("L: "+next_low+"h");

		Integer high = Integer.parseInt(next_high,16);
		Integer low = Integer.parseInt(next_low,16);
		
//		System.out.println(Integer.toBinaryString(high));
		
		// K extrahieren
		high = high & 0x07; // K high extrahieren: 0010 1KKK
//		System.out.println(Integer.toBinaryString(high));
		high = high << 8;

		Integer k = high + low;
//		System.out.println(k);

		return k;
	}

}
