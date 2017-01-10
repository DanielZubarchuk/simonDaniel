package guiPractice8.simonGame;

import guiPractice8.simonGame.ButtonInterfaceDaniel;
import guiPractice8.simonGame.MoveInterfaceDaniel;

public class Move implements MoveInterfaceDaniel {

	private ButtonInterfaceDaniel b;
	
	public Move(ButtonInterfaceDaniel b){
		this.b = b;
	}
	
	@Override
	public ButtonInterfaceDaniel getButton() {
		return b;
	}

}
