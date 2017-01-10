package guiPractice8.simonGame; 

import guiPractice8.GUIApplication;

public class SimonGameDaniel extends GUIApplication {

	private static final long serialVersionUID = 4297926260023939521L;

	public SimonGameDaniel() {
		super();
	}

	@Override
	public void initScreen() {
		SimonScreenDaniel click = new SimonScreenDaniel(getWidth(),getHeight());
		setScreen(click);

	}

	public static void main(String[] args) {
		SimonGameDaniel game = new SimonGameDaniel();
		Thread app = new Thread(game);
		app.start();
	}

}
