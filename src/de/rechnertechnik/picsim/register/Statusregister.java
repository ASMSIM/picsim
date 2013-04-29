package de.rechnertechnik.picsim.register;

public class Statusregister extends Register {

	
	
	public Statusregister(Integer wert) {
		super(wert);
	}

	
	public boolean getZero(){
		return getBit(2);
	}
	public boolean getCarry(){
		return getBit(0);
	}

	public void setZero(){
		this.wert = this.wert | 4;
	}


}
