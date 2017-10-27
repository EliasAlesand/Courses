/**
* Denna klass representerar ett ord, vilket best�r av en
* teckensekvens kallad text.
*/

public class Word {
	String text;
	/**
	* Skapar ett nytt ord med den givna texten.
	*/
	public Word(String text){
		this.text = text;
	}
	/**
	* J�mf�r detta ord med det specificerade objektet. Resultatet �r
	* true om och endast om obj ocks� �r ett ord (Word) och har
	* samma text.
	*/
	public boolean equals(Word obj){
		if (text.equals(obj.toString())){
			return true;
		}
		else {
			return false;
		}
	}
	/**
	* Returnerar hashkoden f�r detta ord ber�knat p� ordets text.
	*/
	public int hashCode(){
		return text.hashCode();
	}
	/**
	* Returnerar texten f�r detta ord.
	*/
	public String toString(){
		return text;
	}
}
