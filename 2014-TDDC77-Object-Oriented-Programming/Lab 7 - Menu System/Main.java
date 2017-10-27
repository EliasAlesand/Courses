import java.util.Scanner;

public class Main {
	static Scanner scan = new Scanner(System.in);
	/**
	 * Nya menyval läggs till genom att använda anonyma klasser
	 */
	public static void main(String[] args) {
		Menu testMenu = new Menu("Huvudmeny");
		testMenu.add(new AbstractMenuItem("Avsluta") {
		public void execute() {
		}
		});
		Menu varuLista = new Menu("Varulista");
		varuLista.add(new AbstractMenuItem("Tillbaka") {
			public void execute() {
			}
			});
		Menu nyVara = new Menu("Lägg till ny vara");
		nyVara.add(new AbstractMenuItem("Tillbaka") {
			public void execute() {
			}
			});
		nyVara.add(new AbstractMenuItem("Bok") {
			public void execute() {
				System.out.println("LÄGG TILL BOK");
				System.out.println("=============");
				System.out.println("Varunummer: 6");
				System.out.println("Titel: Emil i Lönneberga");
				System.out.println("Pris: 45");
				System.out.println("Miljömärkt: n");
			}
			});
		nyVara.add(new AbstractMenuItem("Film") {
			public void execute() {
			}
			});
		nyVara.add(new AbstractMenuItem("Kläder") {
			public void execute() {
			}
			});
		nyVara.add(new AbstractMenuItem("Mat") {
			public void execute() {
			}
			});
		testMenu.add(varuLista);
		testMenu.add(nyVara);
		testMenu.execute();
	}
}
