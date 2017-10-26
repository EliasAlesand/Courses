import java.util.ArrayList;
import java.util.Scanner;

public class Menu implements MenuItem{
	private String title;
	private Scanner scan = new Scanner(System.in);
	private ArrayList<MenuItem> items = new ArrayList<MenuItem>();
	/**
	* Skapar en tom meny med den givna rubriken.
	* @param title Sätts till titeln på menyn.
	*/
	public Menu(String title){
		this.title = title;
	}
	/**
	* Lägger till ett menyval till menyn.
	* @param item läggs till i menyn.
	*/
	public void add(MenuItem item){
		items.add(item);
	}
	public String getTitle(){
		return title;
	}
	/**
	* Exekverar menyn enligt loopen definierad i punkterna (1) till (4).
	* (1) Skriver ut menyns rubrik med stora bokstäverunderstruket med ’=’.
	* Därefter följer en numrerad lista över alla menyelement i denna
	* meny, numrerade från 0.
	*
	* (2) Användaren får sedan välja ett av alterntiven genom att ange
	* talet framför menyvalet. Vad händer om man inte anger ett
	* giltigt tal? Användarens menyval exekveras.
	* (3) Om menyval 0 valts så returnerar metoden. 0 motsvarar
	* alltså alltid av avsluta/tillbaka/återgå.
	*
	* (4) gå till (1)
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