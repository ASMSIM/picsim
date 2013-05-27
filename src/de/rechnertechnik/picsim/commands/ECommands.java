package de.rechnertechnik.picsim.commands;

/**
 * Enthält alle Assembler Befehle  als Enumeration
 */
public enum ECommands {
	ADDWF, ANDWF, CLRF, CLRW, COMF, DECF, DECFSZ, INCF, INCFSZ, IORWF, MOVF, MOVWF, NOP, RLF, RRF, SUBWF, SWAPF, XORWF,

	BCF, BSF, BTFSC, BTFSS,

	ADDLW, ANDLW, CALL, CLRWDT, GOTO, IORLW, MOVLW, RETFIE, RETLW, RETURN, SLEEP, SUBLW, XORLW
}
