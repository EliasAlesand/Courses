public class DivisionTable extends ArithmeticTable {
	public DivisionTable(int rows, int columns){
		this.rows = rows;
		this.columns = columns;
		symbol = "/";
		space = Integer.toString(rows).length() +1;
		startAt = 1;
		Print();
	}
	int evaluate(int x, int y) {
		return x/y;
	}
}