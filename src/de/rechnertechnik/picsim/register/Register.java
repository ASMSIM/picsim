package de.rechnertechnik.picsim.register;

public class Register {

	protected Integer wert;

	public Register(Integer wert) {
		this.wert = wert;
	}

	public Integer getWert() {
		return wert;
	}

	public void setWert(Integer wert) {
		// TODO
		// Ueberlauf
		// ueberpruefen
		this.wert = wert;

	}

	public boolean getBit(Integer bitNr) {

		if((this.getWert() & (1 << bitNr)) != 0) {
			// The bit was set
			return true;
		}
		else {
			return false;
		}
	}

	public void incRegister() {
		this.wert++;
		if(this.wert == 256) {
			this.wert = 0; // Überlauf
		}
	}

}
