public class AdditionTable extends ArithmeticTable {
	public AdditionTable(int rows, int columns){
		this.rows = rows;
		this.columns = columns;
		symbol = "+";
		space = Integer.toString(rows+columns).length() +1;
		startAt = 0;
		Print();
	}
	int evaluate(int x, int y) {
		return x+y;
	}
}	
