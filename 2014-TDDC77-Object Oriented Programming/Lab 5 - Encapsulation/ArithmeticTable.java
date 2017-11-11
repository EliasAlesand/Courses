public class ArithmeticTable {
	int rows;
	int columns;
	Operation operation;
	public ArithmeticTable(Operation op, int r, int c){
		rows = r;
		columns = c;
		operation = op;
		Print();
	}
	public void Print(){
		System.out.format("%1$"+ operation.width(rows, columns) + "s", operation.symbol() +"|");
		for (int i= operation.starAt() ; i <= columns; i++){
			System.out.format("%1$"+ operation.width(rows, columns) + "s", i);
		}
		System.out.println();
		for(int i = 0; i<operation.width(rows, columns)-1; i++){
			System.out.print("-");
		}
		System.out.print("+");
		for (int i =0; i<=(columns+1-operation.starAt())*operation.width(rows, columns) -1; i++){
			System.out.print("-");
		}
		System.out.println();
		
		for(int i =0; i<= rows;i++){
			System.out.format("%1$"+ operation.width(rows, columns) + "s",i + "|");
			for(int j = operation.starAt(); j<= columns;j++){
				System.out.format("%1$"+ operation.width(rows, columns) + "s",operation.evaluate(i, j));
			}
			System.out.println();
		}
	}
}
	
