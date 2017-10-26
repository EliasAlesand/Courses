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
* Både term och betydelse representeras med klassen Word.
*/
public class Dictionary {
	HashMap<Word, Set<Word>> map = new HashMap<Word, Set<Word>>();
	/**
	* Lägger till termen t till ordlistan med betydelserna m. Om termen
	* redan är tillagd med angiven betydelse händer ingenting.
	*/
	public void add(Word t, Set<Word> m){
		map.put(t, m);
	}
	/**
	* Lägger till termen t till ordlistan med betydelsen m. Om termen
	* redan är tillagd med angiven betydelse händer ingenting.
	*/
	public void add(Word t, Word m)
	{
		Set<Word> meaning = new HashSet<Word>();
		meaning.add(m);
		map.put(t, meaning);
	}
	/**
	* Bekvämare sätt att anropa add för 2 strängar än
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
	* Returnerar en icke-null mängd med ordlistans alla
	* termer.
	*/

	public Set<Word> terms(){
		return map.keySet();
	}
	/**
	* Slår upp och returnerar en mängd av betydelser till t, eller
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
	* Skapar och returnerar en ny ordlista på det motsatta språket, dvs, alla
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
	* Läser in orden från den givna strömmen och lägger dessa i
	* ordlistan.
	* @param is Strömmen där orden hämtas från
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
	* Spara ordlistan på den givna strömmen os.
	* @param os Strömmen där ordlistan sparas
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
