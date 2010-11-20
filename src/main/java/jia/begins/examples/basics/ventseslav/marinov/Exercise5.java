package jia.begins.examples.basics.ventseslav.marinov;

public class Exercise5 {

	public static void main(String[] args) {

        int toNine = (3*3)+(3*4)+(3*5);
        int toNineteen = (1*3)+(2*6)+(2*7)+(4*8)+(1*9);
        int toNinetynine = 3*(10*5+toNine)+4*(10*6+toNine)+1*(10*7+toNine);
        int toNinehundredNinetynine = 100*toNine+9*(100*7+99*3+toNine+toNineteen+toNinetynine);
        int thousand = 8;
        
        int sum = toNine+toNineteen+toNinetynine+toNinehundredNinetynine+thousand;
        
        System.out.println(sum);
		
	}

}
