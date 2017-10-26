import java.util.Scanner;


public class LinCalcMethods {
	public static void main(String[]args){
		Scanner scan = new Scanner(System.in);
		String expr = scan.next();
		System.out.println(evaluate(expr));
	}
	public static double evaluate(String expr){
		return calc(toPostfix(lex(expr)));
	}
	public static double calc(String[] postfix)
	{
		//Stack stack = new Stack();
		//stack.setLength(3);
		for (int i = 0; i < postfix.length; i++){
			if (isNumber(postfix[i])){
				continue;
			}
			else{
				double x = 0, y = 0;
				boolean xIsSet = false, yIsSet = false;
				
				int counter = 0;
				
				while (xIsSet == false || yIsSet == false){
					counter++;
					if (yIsSet == false){
						if (isNumber(postfix[i-counter])){
							y = Double.parseDouble(postfix[i-counter]);
							yIsSet = true;
							postfix[i-counter] = "t";
						}
					}
					else{
						if (isNumber(postfix[i-counter])){
							x = Double.parseDouble(postfix[i-counter]);
							xIsSet = true;
							postfix[i-counter] = "t";
						}
					}
				}
				
				if (memberOf(new char[] {'+'}, postfix[i]))
					postfix[i-counter] = Double.toString(x+y);
				if (memberOf(new char[] {'-'}, postfix[i]))
					postfix[i-counter] = Double.toString(x-y);
				if (memberOf(new char[] {'*'}, postfix[i]))
					postfix[i-counter] = Double.toString(x*y);
				if (memberOf(new char[] {'/'}, postfix[i]))
					postfix[i-counter] = Double.toString(x/y);
				
			}
		}
		return  Double.parseDouble(postfix[0]);
	}
	public static String[] toPostfix(String[] lexArray)
	{
		/*Metoden tar in en array som �r uppdelad i operatorer och tal i formen infix
		och konverterar den till ett postfix-uttryck
				
		Vi anv�nder en stack som lagrar operatorer i det 
		"tempor�ra sp�ret" enligt j�rnv�gsalgoritmen*/
		Stack stack = new Stack();
		stack.setLength(lexArray.length);
		String[] postfix = new String[lexArray.length];
		int arrayPosition = 0;
		for (int i = 0; i<lexArray.length; i++){
			if (isNumber(lexArray[i])){
				postfix[arrayPosition] = lexArray[i];
				arrayPosition++;
			}
			else if (stack.isEmpty() == true){
				stack.push(lexArray[i]);
			}
			else if (memberOf(new char[]{'('}, lexArray[i])){
				stack.push(lexArray[i]);
			}
			else if (memberOf(new char[]{')'}, lexArray[i])){
				while (memberOf(new char[]{'('}, stack.topOfStack()) == false){
					postfix[arrayPosition] = stack.pop();
					arrayPosition++;
				}
				stack.pop();
			}
			else if(memberOf(new char[]{'('}, stack.topOfStack())){
				stack.push(lexArray[i]);
			}
			else if(hasHigherPriority(stack.topOfStack(), lexArray[i])){
				while (hasHigherPriority(stack.topOfStack(), lexArray[i])){
					postfix[arrayPosition] = stack.pop();
					arrayPosition++;
					if (stack.isEmpty() == true){
						break;
					}
				}
				stack.push(lexArray[i]);
			}
			else {
				stack.push(lexArray[i]);
			}
		}
		/*N�r hela uttrycket har k�rts igenom loopen kan det finnas element kvar i stacken.
		Dessa pushas till postfix-uttrycket tills stacken �r tom
		Vi m�ste skapa en tempor�r int f�r hur m�nga g�nger loopen ska k�ras
		d� stack.getLength kommer att �ndras mitt i loopen*/
		if (stack.getLength() > 0){
			int temp = stack.getLength();
			for(int i = 0; i< temp; i++){
				postfix[arrayPosition] = stack.pop();
				arrayPosition++;
			}
		}
		
		/*Precis som i metoden "lex" kan det finnas tomma element i slutet av arrayen
		d� parenteser tas bort fr�n infix till postfix.
		Vi g�r likadant h�r f�r att f� bort dem tomma elementen.*/
		int actualSize =0;
		for (int i =0; i < postfix.length; i++){
			if (postfix[i] == null)
			{
				break;
			}
			actualSize++;
		}
		String[] completeArray = new String[actualSize];
		for(int i = 0; i< actualSize; i++){
			completeArray[i] = postfix[i];
		}
		return completeArray;
	}
	public static String[] lex(String expr){
		
		/*Metoden tar en str�ng och delar upp den i operatorer och tal.
		Karakt�rer som l�ses in l�ggs alltid in p� platsen haveRead
		vilket g�r att n�r haveRead blir st�rre betyder det att
		det f�rra elementet �r f�rdigt och nya karakt�rer ska l�ggas i ett nytt
		
		Eftersom vi behandlar un�ra minus framf�r en parentes p� s� s�tt
		att vi byter ut det mot "-1 *" kan tokens bli l�ngre �n
		det ursprungliga uttrycket. D�rf�r s�tter vi storleken p� tokens till
		uttryckets l�ngd * 2 vilket inte kan uppn�s. Detta g�r att det kommer att finnas
		tomma element i slutet av arrayen, men det behandlas senare.*/
		String[] tokens = new String[expr.length()*2];
		int haveRead = 0;
		for(int i = 0; i < expr.length(); i++){
		tokens[i] = "";
		}
		for(int i = 0; i < expr.length(); i++){
			if (isOperator(expr.charAt(i)) == true){
				if (memberOf(new char[]{'-'}, Character.toString(expr.charAt(i)))){
					if (i == 0){
						if (memberOf(new char[]{'('}, Character.toString(expr.charAt(i+1))) == true){
							tokens[haveRead] = "-1";
							haveRead++;
							tokens[haveRead] = "*";
							haveRead++;
						}
						else{
							tokens[haveRead] = Character.toString(expr.charAt(i));
						}
					}
					else if (memberOf(new char[]{'+', '-', '*', '/', '('}, Character.toString(expr.charAt(i-1))) == true){
						if (memberOf(new char[]{'('}, Character.toString(expr.charAt(i+1))) == true){
							tokens[haveRead] = "-1";
							haveRead++;
							tokens[haveRead] = "*";
							haveRead++;
						}
						else{
							tokens[haveRead] = Character.toString(expr.charAt(i));
						}
					}
					else{
						tokens[haveRead] = Character.toString(expr.charAt(i));
						haveRead +=1;
					}
				}
				else{
					tokens[haveRead] = Character.toString(expr.charAt(i));
					haveRead +=1;
				}
			}
			else{
				tokens[haveRead] += Character.toString(expr.charAt(i));
				if (i < expr.length()-1){
					if (isOperator(expr.charAt(i+1))){
						haveRead +=1;
					}
				}
			}
		}
		
		/*Eftersom tokens[] kan ha flera tomma element vill vi ta bort dem s� de inte skapar problem senare
		Vi kollar f�rst hur m�nga element som inte �r tomma 
		genom att r�kna upp tills vi n�r ett tomt element.
		Sedan skapas en ny array som endast matchar de fyllda elementen*/
		int actualSize =0;
		
		for (int i =0; i < tokens.length; i++){
			if (tokens[i] == null || tokens[i] == "")
			{
				break;
			}
			actualSize++;
		}
		String[] completeArray = new String[actualSize];
		for(int i = 0; i< actualSize; i++){
			completeArray[i] = tokens[i];
		}
		return completeArray;
	}
	public static boolean hasHigherPriority(String a, String b){
		/*Metoden tar in tv� str�ngar som alltid kommer att vara tv� operatorer.
		a representerar operatorn i stacken och b �r operatorn som just l�stes i den lexade arrayen*/
		int prioA = 0;
		int prioB = 0;
		if ((a.charAt(0) == '+') || a.charAt(0) == '-'){
			prioA = 1;
		}
		if (a.charAt(0) == '*' || a.charAt(0) == '/'){
			prioA = 2;
		}
		
		if ((b.charAt(0) == '+') || b.charAt(0) == '-'){
			prioB = 1;
		}
		if ((b.charAt(0) == '*') || b.charAt(0) == '/'){
			prioB = 2;
		}
		if (prioA >= prioB){
			return true;
		}
		else{
			return false;
		}
	}
	public static boolean isNumber(String string){
		/*Metoden tar in en str�ng och avg�r om det �r en siffra eller inte.
		I det h�r programmet kommer en str�ng med fler �n en karakt�r alltid att vara en siffra*/
		if (string.length() > 1){
			return true;
		}
		else if (isOperator(string.charAt(0)) || string == "t"){
			return false;
		}
		else{
			return true;
		}
	}
	public static boolean isOperator(char character){
		//Metoden avg�r om en karakt�r �r en operator genom att anv�nda sig av memberOf-klassen som vi skapade i labb 2
		if (memberOf(new char[]{'+', '-', '*', '/', '(', ')'},Character.toString(character))){
			return true;
		}
		else
		{
			return false;
		}
	}
	public static boolean memberOf(char[] characters, String string)
	{
		/*Metoden tar in en array av karakt�rer och en str�ng
		och j�mf�r sedan varje element i arrayen med varje karakt�r i 
		str�ngen och returnerar true om de n�gon g�ng matchar varandra*/
				
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



