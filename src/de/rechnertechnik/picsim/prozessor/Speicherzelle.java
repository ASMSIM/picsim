package de.rechnertechnik.picsim.prozessor;

public class Speicherzelle {

	private Integer speicherzelle;

	public Speicherzelle(Integer value) throws MemoryOutOfRangeException {
		checkValid(value);
		this.speicherzelle = new Integer(value);
	}


	private void checkValid(Integer value) throws MemoryOutOfRangeException {
		if (value > 255 || value < 0) {
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
	

}
