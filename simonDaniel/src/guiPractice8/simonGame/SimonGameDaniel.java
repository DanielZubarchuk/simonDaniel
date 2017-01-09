package guiPractice8.simonGame;

import guiPackage.Screen;

import guiPackage.guiApplication;

public class SimonGameDaniel extends guiApplication{

	public static SimonGameDaniel gameScreen;
	
	public SimonGameDaniel(int width, int height) {
		super();
	}

	public static void main(String[] args) {
		SimonGameDaniel game = new SimonGameDaniel(800,500);
		Thread app = new Thread(game);
		app.start();
	}

	@Override
	protected void initScreen() {
		gameScreen = new SimonGameDaniel(getWidth(), getHeight());
		setScreen(gameScreen);
		
	}

}
