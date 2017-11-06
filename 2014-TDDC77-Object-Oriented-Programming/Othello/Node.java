public class Node {
	int state = 0;
	char symbol;
	public Node(){
		Update();
	}
	public void SwitchState(){
		if (state == 1)
			state = 2;
		else state = 1;
		Update();
	}
	public void SetState(int state){
		this.state = state;
		Update();
	}
	public int GetState(){
		return state;
	}
	public char GetChar(){
		return symbol;
	}
	private void Update(){
		switch(state){
		case 0:{
			symbol = '.';
		}break;
		case 1:{
			symbol = 'X';
		}break;
		case 2:{
			symbol = 'O';
		}break;
		}
	}
	public boolean IsEmpty(){
		if (state == 0)
			return true;
		else return false;
	}
}
