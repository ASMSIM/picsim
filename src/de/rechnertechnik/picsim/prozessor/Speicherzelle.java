package de.rechnertechnik.picsim.prozessor;

public class Speicherzelle {

	private Integer speicherzelle;

	public Speicherzelle(Integer value) throws MemoryOutOfRangeException {
		checkValid(value);
		this.speicherzelle = new Integer(value);
	}

	private void checkValid(Integer value) throws MemoryOutOfRangeException {
		if(value > 65535 || value < 0) {
			throw new MemoryOutOfRangeException();
		}
	}

	public Integer getValue() {
		return this.speicherzelle;
	}

	public void setWert(Integer value) throws MemoryOutOfRangeException {
		checkValid(value);
		this.speicherzelle = value;
	}

	public boolean getBit(Integer bitNr) {

		if((this.getValue() & (1 << bitNr)) != 0) {
			// The bit was set
			return true;
		}
		else {
			return false;
		}
	}

	public void incRegister() {
		this.speicherzelle++;
		if(this.speicherzelle == 256) {
			this.speicherzelle = 0; // Überlauf
		}
	}

}
