public class Stack {
	/* En stack �r en array som bara kan l�gga till element
	   samt ta bort element fr�n ena h�llet. (First in last out) 
	 */
	String[] stackArray;
	//stackSize h�ller reda p� hur m�nga element som ligger i stacken
	int stackSize = 0;
	public void setLength(int length){
		/* Metoden best�mmer hur m�nga element som ska rymmas i stacken.
		   Denna metod m�ste k�ras innan man kan anv�nda stacken.*/
		stackArray = new String[length];
	}
	public int getLength(){
		return stackSize;
	}
	public void push(String str){
		//Metoden l�gger p� en str�ng �verst i stacken
		stackArray[stackSize] = str;
		stackSize++;
	}
	public String pop(){
		//Metoden returnerar det �versta elementet i stacken och minskar storleken med 1
		stackSize --;
		return stackArray[stackSize];
	}
	public String topOfStack(){
		//Metoden returnerar elementet �verst i stacken
		return stackArray[stackSize -1];
	}
	public boolean isEmpty(){
		//Metoden kollar om inga element finns i stacken och returnerar true om den �r tom
		if (stackSize == 0){
			return true;
		}
		else{
			return false;
		}
	}
}
