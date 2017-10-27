import java.util.InputMismatchException;
import java.util.Scanner;
public class NumberPrinter {

	public static void main(String[] args) {
		float n = 0;
		Scanner scan = new Scanner(System.in);
		while (n < 1 || n != (int)n){
			
			System.out.print("Skriv ett positivt heltal: ");
			n = scan.nextFloat();
		}
		Printer(n);
	}
	private static void Printer(float lines){
		System.out.format("%1$16s","Decimal");
		System.out.format("%1$16s","Binary");
		System.out.format("%1$16s","Octal");
		System.out.format("%1$16s","Hexadecimal");
		System.out.println();
		for (int i = 1; i<= lines; i++){
			System.out.format("%1$16s",i);
			System.out.format("%1$16s",Integer.toBinaryString(i));
			System.out.format("%1$16s",Integer.toOctalString(i));
			System.out.format("%1$16s",Integer.toHexString(i));
			System.out.println();
		}
		
	}
}
