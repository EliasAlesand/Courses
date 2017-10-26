import java.util.Scanner;
public class FibonacciCalculator {

	static int counter = 0;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		long start=-1;
		while (start <0 || start != (int)start){
			System.out.println("Skriv ett positivt heltal som startv?rde.");
			start = scan.nextLong();
		}
		long steps = -1;
		while (steps <0 || steps != (int)steps){
			System.out.println("Hur m?nga steg mellan varje utskrift?");
			steps = scan.nextLong();
		}
		FibPrinter(start,steps);
		System.out.println(counter);
	}
	public static long FibIterative(long n)
	{
		
		long a = 1;
		long b = 1;
		for (long i = 0; i <n; i++){
			long tempA = a;
			a = b;
			b = tempA + b;
			counter ++;
		}
		return a;
	}
	public static long FibRecursive(long n){
		if (n == 0)
			return 1;
		if (n == 1)
			return 1;
		counter++;
		return FibRecursive(n-1) + FibRecursive(n-2);
	}
	public static void FibPrinter(long start, long step)
	{
		for(int i = 0; i <20;i++){
			System.out.println(FibRecursive(start + i* step));
		}
	}
}
