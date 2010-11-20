package jia.begins.examples.basics.ventseslav.marinov;

import java.math.BigInteger;

public class Exercise4 {

	public static BigInteger factorial(long number) {
		return fact(BigInteger.valueOf(number));
		}

	private static BigInteger fact(BigInteger n) {
		if (n.compareTo(BigInteger.ZERO) == 0)
		  return BigInteger.ONE;
		else
		  return n.multiply(fact(n.subtract(BigInteger.ONE)));
		}
	
	

	public static void main(String[] args) {
		long factorial = 100;
		char currentSum;
		int currentValue;
		int factorialSum = 0;
		
		System.out.println("Factorial of " + factorial + " is: " + factorial(factorial));
		
		String toString = factorial(factorial).toString();
		
		for (int i = 0; i < toString.length(); i++) {
			currentSum = toString.charAt(i);
			currentValue = Character.getNumericValue(currentSum);
			factorialSum = factorialSum + currentValue;
		}
		System.out.println("The factorial sum of " + factorial +"!" +" is: " + factorialSum);
		}

	}


