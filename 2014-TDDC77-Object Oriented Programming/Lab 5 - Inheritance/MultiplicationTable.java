public class MultiplicationTable extends ArithmeticTable {
	public MultiplicationTable(int rows, int columns){
		this.rows = rows;
		this.columns = columns;
		symbol = "*";
		space = Integer.toString(rows*columns).length() +1;
		startAt = 0;
		Print();
	}

	int evaluate(int x, int y) {
		return x*y;
	}
}
