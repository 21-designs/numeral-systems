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
 * Represents a number and its associated numeral system.
 * 
 * @author Yannick Drost
 *
 */
public class Number
{
	public final NumeralSystem system;
	
	public String value;
	
	/**
	 * Creates a new decimal number with no value.
	 */
	public Number()
	{
		this(NumeralSystem.DECIMAL);
	}
	
	/**
	 * Creates a new number of the specified numeral system but with no value.
	 * @param system
	 */
	public Number(NumeralSystem system)
	{
		this.system = system;
		this.value = "";
	}
	
	/**
	 * Creates a new number specified by its numeral system and a value.
	 * @param system
	 * @param value
	 */
	public Number(NumeralSystem system, String value)
	{
		this.system = system;
		this.value = value;
	}
		
	
	
	
	
	
	/**
	 * Add a new number to this one. It is possible to add numbers of different
	 * numeral systems but the result will be in a system equal to this one.
	 * 
	 * @param val
	 * @return
	 */
	public Number add(Number val)
	{	
		BigInteger dec1 = new BigInteger(NumeralSystem.convertToDecimal( this ).value);
		BigInteger dec2 = new BigInteger(NumeralSystem.convertToDecimal( val ).value);
		
		Number result = new Number(NumeralSystem.DECIMAL, dec1.add( dec2 ).toString( ));
		
		return NumeralSystem.convertDecimalTo( this.system, result );
	}
	
	/**
	 * Multiply a new number to this one. It is possible to multiply numbers of different
	 * numeral systems but the result will be in a system equal to this one.
	 * 
	 * @param val
	 * @return
	 */
	public Number multiply(Number val)
	{
		BigInteger dec1 = new BigInteger(NumeralSystem.convertToDecimal( this ).value);
		BigInteger dec2 = new BigInteger(NumeralSystem.convertToDecimal( val ).value);
		
		Number sumd = new Number(NumeralSystem.DECIMAL, dec1.multiply( dec2 ).toString( ));
		
		return NumeralSystem.convertDecimalTo( this.system, sumd );
	}
	
	
	
	

	
	
	
	public String getValue( )
	{
		return value;
	}

	public void setValue( String value )
	{
		this.value = value;
	}
	
	public NumeralSystem getNumeralSystem()
	{
		return system;
	}

	public NumeralSystem getSystem( )
	{
		return system;
	}
	
	public String toString()
	{
		return value + " b"+system.base;
	}
}
