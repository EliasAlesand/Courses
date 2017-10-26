public interface Operation {
	public char symbol();
	public int width(int rows, int cols);
	public int evaluate(int a, int b);
	public int starAt();
}
