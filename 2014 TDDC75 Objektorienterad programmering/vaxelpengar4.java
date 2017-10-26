import java.util.Scanner;
//andru411@student.liu.se �mne TDDC77: Laboration 1
public class vaxelpengar4{
    public static void main(String[] args){
	Scanner in = new Scanner(System.in);
	
	double pris;
	do{
	    System.out.println("Ange pris (kr):");
	    pris = in.nextDouble();
	    if (pris <0){
		System.out.println("Priset f�r inte vara negativt.");
	    }

	}while(pris < 0);

	double jagBetalar;
	do{
	    System.out.println("Hur mycket betalar du? (kr)");
	    jagBetalar = in.nextDouble();
	    if (jagBetalar < pris){
		System.out.println("Du har inte r�d.");
	    }
	}while (jagBetalar < pris);
	
	double pengarKvar = jagBetalar - pris;
	int[] pengaV�rden ={500,100,50,20,10,5,1};
	int[] antalPengar = new int[7];
	
	for (int i=0;i<pengaV�rden.length;i++){
	    antalPengar[i] = (int)pengarKvar/pengaV�rden[i];
	    pengarKvar = (pengarKvar%pengaV�rden[i]);
        }
        
	//L�gger till en enkrona om det saknas tillr�ckligt m�nga �ren.
	if (pengarKvar >= 0.75){
	    antalPengar[antalPengar.length-1] +=1;
	}

	System.out.print("Du f�r tillbaka " 
+ antalPengar[0]+ " femhundralappar, "
+ antalPengar[1] + " hundralappar, " 
+ antalPengar[2] + " femtiolappar, " 
+ antalPengar[3] + " tjugolappar, " 
+ antalPengar[4] + " tior, " 
+ antalPengar[5] + " femmor, " 
+ antalPengar[6] + " enkronor och ");
	
	//L�gger till en femtio�ring om antalet �ren som saknas b�r avrundas till 50, annars l�ggs ingen femtio�ring till.
	if (pengarKvar >= 0.25 && pengarKvar < 0.75){
	    System.out.println("1 femtio�ring.");
		}
	else{
	    System.out.println("0 femtio�ringar.");
	}
    }
}
