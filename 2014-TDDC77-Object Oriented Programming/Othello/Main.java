import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Board board = new Board(8);
		AbstractPlayer[] players = new AbstractPlayer[2];
		/*players[0] = new AbstractPlayer(board){
			public void makeMove(){
				setX(scan.nextInt());
				setY(scan.nextInt());
			}
		};*/
		players[0] = new AbstractPlayer(board){
			public void makeMove(){
				ArrayList<Integer> possibleX = new ArrayList<Integer>();
				ArrayList<Integer> possibleY = new ArrayList<Integer>();
				for(int i = 0; i<board.size;i++){
					for(int j = 0; j<board.size;j++){
						if (board.checkAllDirections(j+1, i+1, false) == true){
							possibleX.add(j+1);
							possibleY.add(i+1);
						}
					}
				}
				Random random = new Random();
				int randomPlace = random.nextInt(possibleX.size()) +1;
				setX(possibleX.get(randomPlace-1));
				setY(possibleY.get(randomPlace-1));
			}
		};
		players[1] = new AbstractPlayer(board){
			public void makeMove(){
				ArrayList<Integer> possibleX = new ArrayList<Integer>();
				ArrayList<Integer> possibleY = new ArrayList<Integer>();
				for(int i = 0; i<board.size;i++){
					for(int j = 0; j<board.size;j++){
						if (board.checkAllDirections(j+1, i+1, false) == true){
							possibleX.add(j+1);
							possibleY.add(i+1);
						}
					}
				}
				Random random = new Random();
				int randomPlace = random.nextInt(possibleX.size()) +1;
				setX(possibleX.get(randomPlace-1));
				setY(possibleY.get(randomPlace-1));
			}
		};
		
		while (board.gameOver() == false){
			if (board.getPlayer() == 1){
				players[0].makeMove();
				board.NewTurn(players[0].getX(), players[0].getY());
			}
			else {
				players[1].makeMove();
				board.NewTurn(players[1].getX(), players[1].getY());
			}
		}
	}
}
