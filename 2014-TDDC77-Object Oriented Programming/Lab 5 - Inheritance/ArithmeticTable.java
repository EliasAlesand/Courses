abstract class ArithmeticTable {
	int rows;
	int columns;
	int space;
	int startAt;
	String symbol ="";
	abstract int evaluate(int x, int y);
	
	public void Print(){
		System.out.format("%1$"+ space + "s",symbol+"|");
		for (int i= startAt; i <= columns; i++){
			System.out.format("%1$"+ space + "s", i);
		}
		System.out.println();
		for(int i = 0; i<space-1; i++){
			System.out.print("-");
		}
		System.out.print("+");
		for (int i =0; i<=(columns+1-startAt)*space -1; i++){
			System.out.print("-");
		}
		System.out.println();
		
		for(int i =0; i<= rows;i++){
			System.out.format("%1$"+ space + "s",i + "|");
			for(int j = startAt; j<= columns;j++){
				System.out.format("%1$"+ space + "s",evaluate(i,j));
			}
			System.out.println();
		}
	}
}
	
