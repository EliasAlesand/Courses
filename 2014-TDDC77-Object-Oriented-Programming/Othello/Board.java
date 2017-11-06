public class Board {
	boolean gameOver = false;
	int size;
	Node[] nodes;
	private int playerTurn=1;
	public Board(int size){
		this.size = size;
		nodes = new Node[size*size];
		for(int i=0; i<nodes.length; i++){
			nodes[i] = new Node();
		}
		StartSetup();
		Print();
	}
	private void StartSetup(){
		GetNode(size/2,size/2).SetState(1);
		GetNode(size/2,size/2+1).SetState(2);
		GetNode(size/2+1,size/2).SetState(2);
		GetNode(size/2+1,size/2+1).SetState(1);
	}
	private int checkWinner(){
		int x=0, y=0;
		for(Node n:nodes){
			if (n.GetState() == 1){
				x++;
			}
			if (n.GetState() == 2){
				y++;
			}
		}
		gameOver = true;
		System.out.println("X: " +x);
		System.out.println("O: " +y);
		if (x>y){
			System.out.println("Player X won");
			return 1;
		}
		else if (y>x){
			System.out.println("Player O won");
			return 2;
		}
		else{ 
			System.out.println("Draw");
			return 0;
		}
	}
	
	public boolean gameOver(){
		return gameOver;
	}
	private int checkEmptyNodes(){
		int emptyNodes=size*size;
		for(Node n:nodes){
			if (n.GetState() != 0){
				emptyNodes--;
			}
		}
		return emptyNodes;
	}
	public void changePlayer(){
		if (playerTurn == 1)
			playerTurn = 2;
		else playerTurn = 1;
	}
	public boolean anyValidMove(){
		for(int i = 0; i<size;i++){
			for (int j = 0; j<size; j++){
				if (canSet(i+1,j+1, false)==true){
					return true;
				}
			}
		}
		return false;
	}
	public boolean NewTurn(int x, int y){
		if (canSet(x,y, true) == true){
			GetNode(x,y).SetState(playerTurn);
			changePlayer();
			if (anyValidMove() == false){
				if (checkEmptyNodes() >0){
					changePlayer();
					Print();
					if (playerTurn == 1){
						System.out.println("Player O could not move");
					}
					else System.out.println("Player X could not move");
					return false;
				}
			}
			Print();
			if (checkEmptyNodes() <=0){
				checkWinner();
			}
			return true;
		}
		else return false;
	}
	private void Print(){
		int n = 0;
		for (int i =0; i<size;i++){
			System.out.print(size-i+" ");
			for (int j =0; j <size; j++){
				System.out.print(nodes[n].GetChar()+" ");
				n++;
			}
			System.out.println();
			
		}
		System.out.print("  ");
		for(int i=0;i<size;i++){
			System.out.print(i+1+" ");
		}
		System.out.println();
		if (playerTurn ==1)
			System.out.println("X turn");
		else System.out.println("O turn");
	}
	private Node GetNode(int x, int y){
		return nodes[size*size-y*size+x-1];
	}
	public int getPlayer(){
		return playerTurn;
	}
	private boolean canSet(int x,int y, boolean switchState){
		if (GetNode(x,y).IsEmpty() == false){
			return false;
		}
		else if (checkAllDirections(x,y, switchState) == true)
			return true;
		else return false;
	}
	public boolean checkAllDirections(int x,int y, boolean switchState){
		boolean canPlace = false;
		if (checkDirection(x,y,0, 1,playerTurn,switchState) == true)
			canPlace = true;
		if (checkDirection(x,y,0, -1,playerTurn,switchState) == true)
			canPlace = true;
		if (checkDirection(x,y,1, 0,playerTurn,switchState) == true)
			canPlace = true;
		if (checkDirection(x,y,-1, 0,playerTurn,switchState) == true)
			canPlace = true;
		if (checkDirection(x,y,-1, 1,playerTurn,switchState) == true)
			canPlace = true;
		if (checkDirection(x,y,-1, -1,playerTurn,switchState) == true)
			canPlace = true;
		if (checkDirection(x,y,1, 1,playerTurn,switchState) == true)
			canPlace = true;
		if (checkDirection(x,y,1, -1,playerTurn, switchState) == true)
			canPlace = true;
		return canPlace;
	}
	private boolean checkDirection(int x,int y,int xDir, int yDir, int state, boolean switchState){
		int thisX = x + xDir;
		int thisY = y + yDir;
		int checkedNodes = 0;
		boolean foundSameState = false;
		try{
		if (GetNode(thisX, thisY).state == state){
			return false;
		}
		while(foundSameState == false && GetNode(thisX,thisY).state !=0){
			if (thisX >= size+1 || thisX <= -1 || thisY >= size+1 || thisY<=-1){
				break;
			}
			if (GetNode(thisX,thisY).state == state){
				foundSameState = true;
			}
			checkedNodes++;
			thisX+=xDir;
			thisY+=yDir;
		}
		if (foundSameState == true){
			if (switchState == true){
				for(int i = 1; i<checkedNodes;i++){
					GetNode(thisX-xDir-xDir*i, thisY-yDir-yDir*i).SwitchState();
				}
			}
			return true;
		}
		return false;
		}
		catch (ArrayIndexOutOfBoundsException e){
			return false;
		}
	}
}
