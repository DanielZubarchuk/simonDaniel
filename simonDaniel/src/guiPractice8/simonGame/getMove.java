package guiPractice8.simonGame;

public class getMove implements MoveInterfaceDaniel {

	private ButtonInterfaceDaniel b; 
	
	public getMove(ButtonInterfaceDaniel b) {
		this.b = b;
	}

	public ButtonInterfaceDaniel getButton() {
		return b;
	}

}
