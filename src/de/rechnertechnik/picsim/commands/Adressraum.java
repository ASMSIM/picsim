package de.rechnertechnik.picsim.commands;

/**
 * Stellt den Adressraum eines PIC Befehls dar
 * 
 * @author michael
 * 
 */
public class Adressraum {

	private Integer from; // Von
	private Integer to; // Bis Hexadresse

	public Adressraum(Integer from, Integer to) {
		this.from = from;
		this.to = to;
	}

	public Integer getFrom() {
		return from;
	}

	public Integer getTo() {
		return to;
	}

}
