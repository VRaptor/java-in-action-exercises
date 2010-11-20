package jia.begins.examples.basics.ventseslav.marinov;

public class Exercise3 {

	
	public static void main(String[] args) {
		int sum = 2;
		for (int i = 1; i < 1000; i++) {
			if (i%3==0 && i%5==0 ) {	
				sum+=i;
			}
		}
		System.out.println("The sum of all natural number which are smaller than 1000 and divisible by 3 and 5 without remainder is: " + sum);
	}

}
