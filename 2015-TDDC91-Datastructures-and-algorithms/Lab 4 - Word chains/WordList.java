import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;
import java.io.*;

// Klassen WordList innehåller en ordlista och en datastruktur som håller
// reda på använda ord.

class WordList
{
	static public double startTime = System.nanoTime();
	static ArrayList<String> wordList;
	static public HashMap<String, ArrayList<String>> list; 
	static public HashSet<String> used; 
	// ordlista// databas med använda ord
	static int wordLength;
	static int size; // antal ord i ordlistan
	// Read läser in en ordlista från strömmen input. Alla ord ska ha
	// wordLength bokstäver.
	static public void Read(int wordLength_, BufferedReader input)
			throws IOException
	{
		wordLength = wordLength_;
		size = 0;
		list = new HashMap<String, ArrayList<String>>();
		wordList = new ArrayList<String>();
		while (true) {
			String s = input.readLine();
			if (s.equals("#")) break;
			if (s.length() != wordLength)
				System.out.println("Rad " + size +
						" i filen innehåller inte " +
						wordLength + " tecken.");

			wordList.add(s);
			size++;
		}
		for(int i = 0;i<wordList.size();i++){
			list.put(wordList.get(i), null);
		}
		
		used = new HashSet<String>();
	}
	static public ArrayList<String> getChildren(String s){
		ArrayList<String> children = list.get(s);
		if (children == null){
			children = new ArrayList<String>();
			for (String string : wordList) {
				int counter = 0;
				for (int j = 0; j < wordLength; j++) {
					if (s.charAt(j) == string.charAt(j)){
						counter++;
					}
				}
				if (counter == 3){
					children.add(string);
				}
				
			}
			list.put(s, children);
			return children;
		}
		else return children;
	}
	// WordAt returnerar ordet med angivet index i ordlistan.
	static public String WordAt(int index)
	{
		//if (index >= 0 && index < size) return (String) list.get(index);
		return null;
	}

	// Contains slår upp w i ordlistan och returnerar ordet om det finns med.
	// Annars returneras null.
	static public String Contains(String w)
	{
		if (list.containsKey(w)) return w; else return null;
	}

	// MarkAsUsedIfUnused kollar om w är använt tidigare och returneras i så
	// fall false. Annars markeras w som använt och true returneras.
	static public boolean MarkAsUsedIfUnused(String w)
	{
		if (used.contains(w)){
			return false;
		}
		else{
			used.add(w);
			return true;
		}
	}
	// EraseUsed gör så att inga ord anses använda längre.
	static public void EraseUsed()
	{
		used.clear();
	}

}