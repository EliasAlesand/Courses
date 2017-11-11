import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Set;
import java.io.FileReader;
/**
* Denna klass modellerar en ordlista (dictionary). En ordlista
* associerar termer med betydelser. En term kan mappas till flera betydelser.
* B�de term och betydelse representeras med klassen Word.
*/
public class Dictionary {
	HashMap<Word, Set<Word>> map = new HashMap<Word, Set<Word>>();
	/**
	* L�gger till termen t till ordlistan med betydelserna m. Om termen
	* redan �r tillagd med angiven betydelse h�nder ingenting.
	*/
	public void add(Word t, Set<Word> m){
		map.put(t, m);
	}
	/**
	* L�gger till termen t till ordlistan med betydelsen m. Om termen
	* redan �r tillagd med angiven betydelse h�nder ingenting.
	*/
	public void add(Word t, Word m)
	{
		Set<Word> meaning = new HashSet<Word>();
		meaning.add(m);
		map.put(t, meaning);
	}
	/**
	* Bekv�mare s�tt att anropa add f�r 2 str�ngar �n
	* add(new Word(w1), new Word(w2)).
	*/
	public void add(String t, String m){
		String[] values = m.split(",");
		Set<Word> valueSet = new HashSet<Word>();
		for (String string : values){
			valueSet.add(new Word(string));
		}
		map.put(new Word(t), valueSet);
	}
	/**
	* Returnerar en icke-null m�ngd med ordlistans alla
	* termer.
	*/

	public Set<Word> terms(){
		return map.keySet();
	}
	/**
	* Sl�r upp och returnerar en m�ngd av betydelser till t, eller
	* null om t inte finns i ordlistan.
	*/
	public Set<Word> lookup(Word t){
		Set<Word> set = new HashSet<Word>();
		Word[] keyArray = new Word[terms().size()];
		terms().toArray(keyArray);
		for (Word word : keyArray){
			if (word.equals(t)){
				set = map.get(word);
			}
		}
		return set;
	}
	/**
	* Skapar och returnerar en ny ordlista p� det motsatta spr�ket, dvs, alla
	* betydelser blir termer och alla termer blir betydelser. T.ex. en
	* svensk-engelsk ordlista blir efter invertering engelsk-svensk.
	*/
	public Dictionary inverse(){
		Dictionary inverseDict = new Dictionary();
		Word[] keyArray = new Word[terms().size()];
		terms().toArray(keyArray);
		for (Word key : keyArray){
			for(Word value : map.get(key)){
				HashSet<Word> keySet = new HashSet<Word>();
				keySet.add(key);
				inverseDict.add(value, keySet);
			}
		}
		return inverseDict;
	}
	/**
	* L�ser in orden fr�n den givna str�mmen och l�gger dessa i
	* ordlistan.
	* @param is Str�mmen d�r orden h�mtas fr�n
	*/
	public void load(InputStream is) throws IOException{
		InputStreamReader r = new InputStreamReader(is);
		BufferedReader reader = new BufferedReader(r);
		String line;
		String key;
		String[] value;
		String[] stringArray = new String[2];
		Word[] values;
		while((line = reader.readLine()) != null) {
			stringArray = line.split("\t");
			key = stringArray[0];
			value = stringArray[1].split(",");
			values = new Word[value.length];
			for(int i = 0; i < value.length; i++){
				values[i] = new Word(value[i]);
			}
			map.put(new Word(key), new HashSet<Word>(Arrays.asList(values)));
		}
	}
	/**
	* Spara ordlistan p� den givna str�mmen os.
	* @param os Str�mmen d�r ordlistan sparas
	*/
	public void save(OutputStream os) throws IOException{
		OutputStreamWriter writer = new OutputStreamWriter(os);
		for(Word key: map.keySet()){
			writer.write((key+ "\t"));
			for (Word value : map.get(key)){
				writer.write(value + ",");
			}
			writer.write("\n");
		}
		writer.close();
	}
}
