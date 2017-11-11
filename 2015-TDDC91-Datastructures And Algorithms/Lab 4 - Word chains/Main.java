import java.io.FileInputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class Main
{
	final static private int WordLength = 4;
	static public long startTime;
	public static void main (String args[]) throws IOException
	{
		startTime = System.nanoTime();  
		System.setIn(new FileInputStream("word4.txt"));
		BufferedReader stdin =
				new BufferedReader(new InputStreamReader(System.in));
		WordList.Read(WordLength, stdin);
		LongestChain lc = new LongestChain(WordLength);
		System.setIn(new FileInputStream("testord.txt"));
		stdin = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String line = stdin.readLine();
			if (line == null) break;
			String tokens[] = line.split(" ");
			if (tokens.length == 1) {
				lc.BreadthFirst(tokens[0]);
			} else if (tokens.length == 2) {
				WordRec wr = lc.BreadthFirst(tokens[0], tokens[1]);
				if (wr == null) {
					System.out.println(tokens[0] + " " +
							tokens[1] + ": ingen lösning");
				} else {
					System.out.println(tokens[0] + " " +
							tokens[1] + ": "+ wr.ChainLength()
							+ " ord");
					wr.PrintChain();
				}
			} else{
				System.out.println("felaktig fråga: '" + line + "'");
				System.out.println("syntax för frågor: slutord");
				System.out.println("eller:             startord slutord");
			}
		}
		long estimatedTime = System.nanoTime() - startTime;
		System.out.println(estimatedTime/1000000);
//		
	}
}