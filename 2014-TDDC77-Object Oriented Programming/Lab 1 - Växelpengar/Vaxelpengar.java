import java.util.Scanner;
public class Vaxelpengar{
    public static void main(String[] args){
	Scanner in = new Scanner(System.in);
	
	double pris;
	do{
	    System.out.println("Ange pris (kr):");
	    pris = in.nextDouble();
	    if (pris <0){
		System.out.println("Priset får inte vara negativt.");
	    }

	}while(pris < 0);

	double jagBetalar;
	do{
	    System.out.println("Hur mycket betalar du? (kr)");
	    jagBetalar = in.nextDouble();
	    if (jagBetalar < pris){
		System.out.println("Du har inte råd.");
	    }
	}while (jagBetalar < pris);
	
	double pengarKvar = jagBetalar - pris;
	int[] pengaVärden ={500,100,50,20,10,5,1};
	int[] antalPengar = new int[7];
	
	for (int i=0;i<pengaVärden.length;i++){
	    antalPengar[i] = (int)pengarKvar/pengaVärden[i];
	    pengarKvar = (pengarKvar%pengaVärden[i]);
        }
        
	//Lägger till en enkrona om det saknas tillräckligt många ören.
	if (pengarKvar >= 0.75){
	    antalPengar[antalPengar.length-1] +=1;
	}

	System.out.print("Du får tillbaka " 
+ antalPengar[0]+ " femhundralappar, "
+ antalPengar[1] + " hundralappar, " 
+ antalPengar[2] + " femtiolappar, " 
+ antalPengar[3] + " tjugolappar, " 
+ antalPengar[4] + " tior, " 
+ antalPengar[5] + " femmor, " 
+ antalPengar[6] + " enkronor och ");
	
	//Lägger till en femtioöring om antalet ören som saknas bör avrundas till 50, annars läggs ingen femtioöring till.
	if (pengarKvar >= 0.25 && pengarKvar < 0.75){
	    System.out.println("1 femtioöring.");
		}
	else{
	    System.out.println("0 femtioöringar.");
	}
    }
}
