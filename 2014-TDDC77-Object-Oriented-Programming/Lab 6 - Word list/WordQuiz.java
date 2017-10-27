import java.awt.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class WordQuiz {
	
	static Scanner scan = new Scanner(System.in);
	public WordQuiz(){
	}
	/**
	 * Kör ett quiz med den givna ordlistan
	 * @param dict Ordlistan som quizet är baserat på
	 */
	public static void runQuiz(Dictionary dict){
		ArrayList<Word> keys = new ArrayList<Word>();
		keys.addAll(dict.terms());
		int counter = 0;
		while (keys.size() > 0){
			keys = checkAnswers(keys, dict);
			counter++;
		}
		System.out.println("Grattis, du klarade alla glosorna på " + counter + " försök.");
	}
	
	/**
	 * Låter användaren skriva meningen till alla ord och returnerar en
	 * ny lista med de felaktiga svaren.
	 * @param keys är de ord som användaren ska förhöras på.
	 * @param dict är ordlistan som nyckarna är tagna ur.
	 * @return returnerar en lista med felaktiga svar.
	 */
	public static ArrayList<Word> checkAnswers(ArrayList<Word> keys, Dictionary dict){
		ArrayList<Word> wrongAnswers = new ArrayList<Word>();
		for (Word word : keys){
			boolean isCorrect = false;
			System.out.print(word.toString() + " = ");
			String answer = scan.nextLine();
			Word[] values = new Word[dict.lookup(word).size()];
			dict.lookup(word).toArray(values);
			for (Word value : values){
				if (answer.equals(value.toString())){
					isCorrect = true;
				}
			}
			if (isCorrect == false){
				System.out.println("Fel!");
				wrongAnswers.add(word);
			}
		}
		if (wrongAnswers.size() > 0){
		System.out.println("Du missade " + wrongAnswers.size() + " ord. Jag förhör dig på dessa igen.");
		}
		return wrongAnswers;
	}
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Dictionary dict = new Dictionary();
		dict.load(new FileInputStream(args[0]));
		Dictionary inversed = dict.inverse();
		inversed.save(new FileOutputStream("inverseTest.txt"));
		runQuiz(dict);
		runQuiz(dict);
		runQuiz(inversed);
		inversed.save(new FileOutputStream("inverseTest2.txt"));
	}
}
