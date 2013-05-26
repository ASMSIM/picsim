package de.rechnertechnik.picsim.prozessor;

public class Speicherzelle {
	
	public enum bits{
		Z,C,DC, RP0
	}

	private Integer speicherzelle;

	public Speicherzelle(Integer value){
//		checkValid(value);
		this.speicherzelle = new Integer(value);
	}

//	private void checkValid(Integer value) throws MemoryOutOfRangeException {
//		if(value > 65535 || value < 0) {
//			throw new MemoryOutOfRangeException();
//		}
//	}

	public Integer getValue() {
		return this.speicherzelle;
	}

	public void setWert(Integer value){
//		checkValid(value);
		this.speicherzelle = value;
	}

	
	public boolean getBit(bits bit)  {
		switch(bit) {
			case C:
				return getBit(0);
			case DC:
				return getBit(1);
			case Z:
				return getBit(2);
			case RP0:
				return getBit(5);
			default:
				System.out.println("Check getBit(), Bit Enum, enum not implemented!");
				break;
		}
		return false;
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

	public void setBit(Integer bitNr) {
		this.speicherzelle = this.speicherzelle | (int) Math.pow(2, bitNr);
	}

	
	public void setBit(bits bit) {
		switch(bit) {
			case C:
				setBit(0);
				break;
			case DC:
				setBit(1);
				break;
			case Z:
				setBit(2);
				break;
			case RP0:
				setBit(5);
				break;
			default:
				System.out.println("Check setBit(), Bit Enum, enum not implemented!");
				break;
		}
	}
	
	public void clearBit(bits bit) {
		switch(bit) {
			case C:
				clearBit(0);
				break;
			case DC:
				clearBit(1);
				break;
			case Z:
				clearBit(2);
				break;
			case RP0:
				clearBit(5);
				break;
			default:
				System.out.println("Check setBit(), Bit Enum, enum not implemented!");
				break;
		}
		
	}

	
	
	public void clearBit(Integer bitNr) {
		Integer setValue = new Integer(0xFF);

		// Minus !
		setValue = setValue - (int) Math.pow(2, bitNr);

		this.speicherzelle = this.speicherzelle & setValue;
	}

	public void incRegister() {
		this.speicherzelle++;
		if(this.speicherzelle == 256) {
			this.speicherzelle = 0; // Überlauf
		}
	}

}
