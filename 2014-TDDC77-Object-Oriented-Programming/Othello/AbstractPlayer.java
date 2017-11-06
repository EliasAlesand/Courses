import java.util.Scanner;

abstract class AbstractPlayer {
	Scanner scan = new Scanner(System.in);
	Board board;
	int x, y;
	public AbstractPlayer(Board board){
		this.board = board;
	}
	abstract void makeMove();
	public void setY(int y){
		this.y = y;
	}
	public void setX(int x){
		this.x = x;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
}
