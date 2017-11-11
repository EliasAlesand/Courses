import java.io.*;
import java.text.NumberFormat;

public class Textstatistik {
	public static void main(String[] args) throws IOException{
		NumberFormat numberFormat = NumberFormat.getNumberInstance();
		numberFormat.setMaximumFractionDigits(2);
		BufferedReader input = new BufferedReader(new FileReader(args[0]));
		int words = 0;
		int sentences = 0;
		int letters = 0;
		int punctuation = 0;
			
		int currentCharacter = ' ';
		int lastReadCharacter = ' ';
		while (true){
			currentCharacter = input.read();
			//Avbryter loopen om slutet av filen har n�tts
			//N�r letter blir -1 betyder det att readern har l�st igenom hela filen
			if (currentCharacter == -1){
				break;
			}
			
			//Unders�ker vad den senast l�sta bokstaven var och uppdaterar antal ord, meningar, bokst�ver och skiljetecken
			if (memberOf(new char[] {' '}, String.valueOf((char)lastReadCharacter)) == true){
				if (memberOf(new char[] {'.','!','?',',',' ','\'','-', ';',':', '\n'},String.valueOf((char)currentCharacter)) == false){
					words++;
				}	
			}
			if (memberOf(new char[] {'.','!','?'}, String.valueOf((char)lastReadCharacter)) == false){
				if (memberOf(new char[] {'.','!','?'},String.valueOf((char)currentCharacter)) == true){
					sentences++;
				}
			}
			
			
			if (memberOf(new char[] {'.','!','?',',',' ','\'','-', ';',':', '\n'},String.valueOf((char)currentCharacter)) == false){
				letters++;
			}
			
			if (memberOf(new char[] {'.','!','?',',',';',':','-'},String.valueOf((char)currentCharacter)) == true){
				punctuation++;
			}
			lastReadCharacter = currentCharacter;
		}
		System.out.println("Antal ord: " + words);
		System.out.println("Antal meningar: " + sentences);
		System.out.println("Antal bokst�ver: " + letters);
		System.out.println("Antal skiljetecken: " + punctuation);
		System.out.println("Medell�ngd p� ord: " + numberFormat.format((float)letters/(float)words));
	}
	public static boolean memberOf(char[] characters, String string)
	{
		for (int i = 0; i < string.length();i++){
			for (int j= 0; j < characters.length;j++){
				if (characters[j] == string.charAt(i)){
					return true;
				}
			}
		}
		return false;
	}
}