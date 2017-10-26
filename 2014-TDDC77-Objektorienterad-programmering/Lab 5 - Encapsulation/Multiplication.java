public class Multiplication implements Operation {
	public int evaluate(int x, int y) {
		return x*y;
	}
	public char symbol() {
		return '*';
	}
	public int width(int rows, int cols) {
		return Integer.toString(rows*cols).length() +1;
	}
	public int starAt() {
		return 0;
	}
}	
