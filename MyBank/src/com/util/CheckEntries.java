package com.util;

import java.util.regex.Pattern;

public class CheckEntries {
	public boolean CheckCard(String text){
		final String regexcheck="^[0-9]{16}";
		boolean b=Pattern.matches(regexcheck, text);
		return b;
	}
	public boolean CheckPin(String text){
		final String regexcheck="^[0-9]{4}";
		boolean b=Pattern.matches(regexcheck, text);
		return b;
	}
	public boolean CheckPAN(String text){
		final String regexcheck="^[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}";//[A-Z]{5}\\d{4}[A-Z]{1}
		boolean b=Pattern.matches(regexcheck, text);
		return b;
	}
	public boolean CheckAadhaar(String text){
		final String regexcheck="^[0-9]{4}[0-9]{4}[0-9]{4}";
		boolean b=Pattern.matches(regexcheck, text);
		return b;
	}
	public boolean CheckEmail(String text)
	{
		final String regexcheck="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		boolean b=Pattern.matches(regexcheck, text);
		return b;	
	}
	public boolean CheckHomePin(String text){
		final String regexcheck="^[0-9]{6}";
		boolean b=Pattern.matches(regexcheck, text);
		return b;
	}

}
