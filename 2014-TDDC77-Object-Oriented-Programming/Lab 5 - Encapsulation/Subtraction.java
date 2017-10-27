public class Subtraction implements Operation {
	public int evaluate(int x, int y) {
		return x-y;
	}
	public char symbol() {
		return '-';
	}
	public int width(int rows, int cols) {
		if (Integer.toString(-cols).length() +1 > Integer.toString(rows).length() +1){
			return Integer.toString(-cols).length() +1;
		}
		else{
			return Integer.toString(rows).length() +1;
		}
	}
	public int starAt() {
		return 0;
	}
}	
