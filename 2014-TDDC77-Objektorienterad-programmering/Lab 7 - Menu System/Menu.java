import java.util.ArrayList;
import java.util.Scanner;

public class Menu implements MenuItem{
	private String title;
	private Scanner scan = new Scanner(System.in);
	private ArrayList<MenuItem> items = new ArrayList<MenuItem>();
	/**
	* Skapar en tom meny med den givna rubriken.
	* @param title S�tts till titeln p� menyn.
	*/
	public Menu(String title){
		this.title = title;
	}
	/**
	* L�gger till ett menyval till menyn.
	* @param item l�ggs till i menyn.
	*/
	public void add(MenuItem item){
		items.add(item);
	}
	public String getTitle(){
		return title;
	}
	/**
	* Exekverar menyn enligt loopen definierad i punkterna (1) till (4).
	* (1) Skriver ut menyns rubrik med stora bokst�verunderstruket med �=�.
	* D�refter f�ljer en numrerad lista �ver alla menyelement i denna
	* meny, numrerade fr�n 0.
	*
	* (2) Anv�ndaren f�r sedan v�lja ett av alterntiven genom att ange
	* talet framf�r menyvalet. Vad h�nder om man inte anger ett
	* giltigt tal? Anv�ndarens menyval exekveras.
	* (3) Om menyval 0 valts s� returnerar metoden. 0 motsvarar
	* allts� alltid av avsluta/tillbaka/�terg�.
	*
	* (4) g� till (1)
	*/
	public void execute(){
		System.out.println(title.toUpperCase());
		for(int i = 0; i<title.length(); i++){
			System.out.print("=");
		}
		System.out.println();
		for(int i = 0; i<items.size(); i++){
			System.out.println(i + ". " + items.get(i).getTitle());
		}
		int choice  = scan.nextInt();
		if (choice == 0){
			return;
		}
		items.get(choice).execute();
		execute();
	}
}