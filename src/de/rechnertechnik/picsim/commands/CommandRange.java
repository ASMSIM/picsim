package de.rechnertechnik.picsim.commands;

public class CommandRange {

	private Integer from;
	private Integer to;
	
	
	public CommandRange(Integer from, Integer to) {
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
