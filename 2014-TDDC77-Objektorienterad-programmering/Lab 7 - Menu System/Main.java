import java.util.Scanner;

public class Main {
	static Scanner scan = new Scanner(System.in);
	/**
	 * Nya menyval l�ggs till genom att anv�nda anonyma klasser
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
		Menu nyVara = new Menu("L�gg till ny vara");
		nyVara.add(new AbstractMenuItem("Tillbaka") {
			public void execute() {
			}
			});
		nyVara.add(new AbstractMenuItem("Bok") {
			public void execute() {
				System.out.println("L�GG TILL BOK");
				System.out.println("=============");
				System.out.println("Varunummer: 6");
				System.out.println("Titel: Emil i L�nneberga");
				System.out.println("Pris: 45");
				System.out.println("Milj�m�rkt: n");
			}
			});
		nyVara.add(new AbstractMenuItem("Film") {
			public void execute() {
			}
			});
		nyVara.add(new AbstractMenuItem("Kl�der") {
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
