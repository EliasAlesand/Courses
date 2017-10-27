/**
* Denna klass representerar ett ord, vilket består av en
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
	* Jämför detta ord med det specificerade objektet. Resultatet är
	* true om och endast om obj också är ett ord (Word) och har
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
	* Returnerar hashkoden för detta ord beräknat på ordets text.
	*/
	public int hashCode(){
		return text.hashCode();
	}
	/**
	* Returnerar texten för detta ord.
	*/
	public String toString(){
		return text;
	}
}
