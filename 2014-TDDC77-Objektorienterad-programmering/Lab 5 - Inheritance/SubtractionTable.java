public class SubtractionTable extends ArithmeticTable {
	public SubtractionTable(int rows, int columns){
		this.rows = rows;
		this.columns = columns;
		symbol = "-";
		if (Integer.toString(-columns).length() +1 > Integer.toString(rows).length() +1){
			space = Integer.toString(-columns).length() +1;
		}
		else{
			space = Integer.toString(rows).length() +1;
		}
		startAt = 0;
		Print();
	}
	int evaluate(int x, int y) {
		return x-y;
	}
}
