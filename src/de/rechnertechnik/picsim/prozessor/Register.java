package de.rechnertechnik.picsim.prozessor;

public class Register {

	private static Integer wert;

	public static Integer getWert() {
		return wert;
	}

	public static void setWert(Integer wert) {
		//TODO
		//Ueberlauf
		//ueberpruefen
		Register.wert = wert;
	}
	
	
	
	
	
}
