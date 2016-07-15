/*
 * This file is part of the yannick drost accumulation that is divided into 
 * different modules.
 * 
 * Copyright (C) 2016 Yannick Drost, all rights reserved.
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package numeral;

import java.math.BigInteger;

/**
 * Represents any numeral system like decimal system, binary system and so on.
 * Every system is defined by its unique symbols and its base. All instances are
 * in positional notation so no roman system can be created.
 * 
 * @author Yannick Drost
 *
 */
public class NumeralSystem
{
	public final int base;
	
	/**
	 * The value of each symbol is given by its index.
	 */
	public final char[] symbols;
	
	
	private static final char[] 		
										SYMBOLS_BINARY = {'0','1'},
										SYMBOLS_TERNARY = {'0','1','2'},
										SYMBOLS_QUATERNARY = {'0','1','2','3'},
										SYMBOLS_QUINARY = {'0','1','2','3','4'},
										SYMBOLS_SENARY = {'0','1','2','3','4','5'},
										SYMBOLS_SEPTENARY = {'0','1','2','3','4','5','6'},
										SYMBOLS_OCTAL = {'0','1','2','3','4','5','6','7'},
										SYMBOLS_DECIMAL = {'0','1','2','3','4','5','6','7','8','9'},
										SYMBOLS_HEXADECIMAL = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'},
										SYMBOLS_ALPHABETICAL = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	
	public static final NumeralSystem 	DECIMAL = new NumeralSystem(SYMBOLS_DECIMAL),
										HEXADECIMAL = new NumeralSystem(SYMBOLS_HEXADECIMAL),
										BINARY = new NumeralSystem(SYMBOLS_BINARY),
										TERNARY = new NumeralSystem(SYMBOLS_TERNARY),
										QUATERNARY = new NumeralSystem(SYMBOLS_QUATERNARY),
										QUINARY = new NumeralSystem(SYMBOLS_QUINARY),
										SENARY = new NumeralSystem(SYMBOLS_SENARY),
										SEPTENARY = new NumeralSystem(SYMBOLS_SEPTENARY),
										OCTAL = new NumeralSystem(SYMBOLS_OCTAL),
										ALPHABETICAL = new NumeralSystem(SYMBOLS_ALPHABETICAL);
	
	/**
	 * Creates a new numeral system in positional notation using the specified
	 * symbols. The base of the system is given by the number of symbols.
	 * 
	 * @param symbols
	 */
	public NumeralSystem(char[] symbols)
	{
		this.base = symbols.length;
		this.symbols = symbols;
	}
	
	
	
	
	
	
	public int getBase()
	{
		return base;
	}
	
	public char[] getSymbols()
	{
		return symbols;
	}
	
	/**
	 * Returns the value of the specified symbol to calculate with. If this
	 * symbol is not in this numeral system it will return -1.
	 * 
	 * @param symbol
	 * @return
	 */
	public int getValueOf(char symbol)
	{
		for(int i = 0; i < symbols.length; i++)
			if(symbols[i] == symbol )
				return i;
		
		return -1;
	}
	
	
	
	/**
	 * Converts any number to the decimal system.
	 * 
	 * @param number
	 * @return
	 */
	static Number convertToDecimal(Number number)
	{
		Number n = new Number(NumeralSystem.DECIMAL);
		
		int result = 0;
		
		for(int i = 0; i < number.value.length( ); i++)
		{
			char symbol = number.value.charAt( i );
			int v = number.system.getValueOf( symbol );
			
			result += (v * ((int) Math.pow( number.system.base, number.value.length()-i-1 ) ) );
			// System.out.println(v+"*"+(number.system.base)+"^"+(number.value.length()-i-1)+"="+result);
		}
		
		n.setValue( ""+result );
				
		return n;
	}
	
	/**
	 * Converts a number of the decimal system to any other numeral system.
	 * 
	 * @param target
	 * @param number
	 * @return
	 */
	static Number convertDecimalTo(NumeralSystem target, Number number)
	{
		Number n = new Number(target);
		
		String value = "";
		
		BigInteger result = new BigInteger(number.value);
		BigInteger remainder = new BigInteger("0");
		BigInteger base = new BigInteger(""+target.base);
		
		while(result.compareTo( BigInteger.ZERO ) != 0)
		{
			remainder = result.mod( base );
			result = result.divide( base );
			
			value = target.symbols[remainder.intValue( )] + value;
		}
		
		n.value = value;
		
		return n;
	}
	
	/**
	 * Converts any number to the specified numeral system. 
	 * @param target
	 * @param number
	 * @return
	 */
	public static Number convertTo(NumeralSystem target, Number number)
	{
		Number dec;
		
		if(number.system.equals( DECIMAL ))
			dec = number;
		else
			dec = NumeralSystem.convertToDecimal( number );
		
		if(target.equals( DECIMAL ))
			return dec;
		
		return NumeralSystem.convertDecimalTo( target, dec );
	}
}
