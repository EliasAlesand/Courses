import java.util.Scanner;
public class GissaTal {
    public static void main(String[] args) {
	int secretNumber;
	Scanner scan = new Scanner(System.in);
	do{
       	    System.out.println("Spelare 1, Mata in ett heltal mellan 0 och 100");
	    secretNumber = scan.nextInt();
	}while (secretNumber < 0 || secretNumber >100);
	for (int i= 0; i< 40; i++)
        {	
		System.out.println();
        }
	System.out.println("Spelare 2, Gissa på ett heltal mellan 0-100");
	int guessedNumber;
	int numberOfGuesses = 0;
	do{
	    guessedNumber = scan.nextInt();
	    numberOfGuesses ++;
	    if (guessedNumber > secretNumber){
	        System.out.println("För stort! Gissa igen.");
	    }
	    else if (guessedNumber < secretNumber){
		System.out.println("För litet! Gissa igen.");
	    }
	}while (guessedNumber != secretNumber);
	System.out.println("Rätt! Numret var: " + secretNumber
			   + ". Du behövde " + numberOfGuesses + " gissningar.");
    }
}
