# numeral-systems
A small framework to create different numeral systems like decimal, binary or any custom numeral system. Additional any numeral system can be converted into another. It is important that only numeral systems using positional notation are supported.

# Example
```java
public class NumeralSystemExample
{
    static Number n = new Number(NumeralSystem.DECIMAL, "23562226");

	public static void main( String[] args )
	{
		print(n);
		n = NumeralSystem.convertTo( NumeralSystem.BINARY, n );
		print(n);
		n = NumeralSystem.convertTo( NumeralSystem.HEXADECIMAL, n );
		print(n);
		n = NumeralSystem.convertTo( NumeralSystem.OCTAL, n );
		print(n);
		n = NumeralSystem.convertTo( NumeralSystem.TERNARY, n );
		print(n);
		n = NumeralSystem.convertTo( NumeralSystem.QUATERNARY, n );
		print(n);
	}

	public static void print(Number n)
	{
		System.out.println(n);
	}
}
```
