package de.rechnertechnik.picsim.junit;

public class dummytest {

	public static void main(String[] args) {
		Integer i = new Integer(253);
		
		if((i & 0x01) == 1){
			System.out.println("CARRY");
		}
		
		System.out.println(Integer.toBinaryString(i));
		i = i >> 1;
		System.out.println(Integer.toBinaryString(i));
	}
}
