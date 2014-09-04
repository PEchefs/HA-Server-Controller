package com.test.util;

import java.util.HashMap;
import java.util.Map;

public final class SwitchStructureHelperUtil {
	private static Map<String, String> swStruc = new HashMap<String, String>();
	private static Map<String, String> swStruc3 = new HashMap<String, String>();
	
	static{
		swStruc.put("0000", "0x00");
		swStruc.put("0001", "0x01");
		swStruc.put("0010", "0x02");
		swStruc.put("0011", "0x03");
		swStruc.put("0100", "0x04");
		swStruc.put("0101", "0x05");
		swStruc.put("0110", "0x06");
		swStruc.put("0111", "0x07");
		swStruc.put("1000", "0x08");
		swStruc.put("1001", "0x09");
		swStruc.put("1010", "0x0A");
		swStruc.put("1011", "0x0B");
		swStruc.put("1100", "0x0C");
		swStruc.put("1101", "0x0D");
		swStruc.put("1110", "0x0E");
		swStruc.put("1111", "0x0F");
		
	}
	
	static{
		swStruc3.put("000", "0x00");
		swStruc3.put("001", "0x01");
		swStruc3.put("010", "0x02");
		swStruc3.put("011", "0x03");
		swStruc3.put("100", "0x04");
		swStruc3.put("101", "0x05");
		swStruc3.put("110", "0x06");
		swStruc3.put("111", "0x07");
		swStruc3.put("000", "0x08");
		swStruc3.put("001", "0x09");
		swStruc3.put("010", "0x0A");
		swStruc3.put("011", "0x0B");
		swStruc3.put("100", "0x0C");
		swStruc3.put("101", "0x0D");
		swStruc3.put("110", "0x0E");
		swStruc3.put("111", "0x0F");
		
	}

	public static String getSwitchStructure(String str){
		
		System.out.println("map of switch structure is : " +swStruc.size());
		
		return swStruc.get(str);
		
	}

	public static String getSwitchStructureFor3(String trim) {
		System.out.println("map of switch structure is : " +swStruc.size());
		
		return swStruc3.get(trim);	
	}
}
