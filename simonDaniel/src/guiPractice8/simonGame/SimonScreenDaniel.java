package guiPractice8.simonGame;

import java.awt.Color;

import java.util.ArrayList;
import java.util.List;

import guiPractice8.component.Action;
import guiPractice8.component.Button;
import guiPractice8.component.TextLabel;
import guiPractice8.component.Visible;
import guiPractice8.component.ClickableScreen;

public class SimonScreenDaniel extends ClickableScreen implements Runnable {
	private TextLabel label;
	private ButtonInterfaceDaniel[] buttons;
	private ProgressInterfaceDaniel progress;
	private ArrayList<MoveInterfaceDaniel> sequence; 
	private int roundNumber;
	private boolean acceptingInput;
	private int sequenceIndex;
	private int lastSelected;

	public SimonScreenDaniel(int width, int height) {
		super(width, height);
		Thread screen = new Thread(this);
		screen.start();
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		Color[] colors = {Color.red, Color.blue, new Color(240,160,70), new Color(20,255,140), Color.yellow, new Color(180,90,210)};
		String[] names = {"RED", "BLUE", "ORANGE", "GREEN", "YELLOW", "PURPLE"};
		int buttonCount = 6;
		buttons = new ButtonInterfaceDaniel[buttonCount];
		for(int i = 0; i < buttonCount; i++ ){
			buttons[i] = getAButton();
			buttons[i].setName(names[i]);
			buttons[i].setColor(colors[i]);
			buttons[i].setX(160 + (int)(100*Math.cos(i*2*Math.PI/(buttonCount))));
			buttons[i].setY(200 - (int)(100*Math.sin(i*2*Math.PI/(buttonCount))));
			final ButtonInterfaceDaniel b = buttons[i];
			System.out.println(b+" has x = "+b.getX()+", y ="+b.getY());
			b.dim();
			buttons[i].setAction(new Action() {

				public void act() {

						Thread buttonPress = new Thread(new Runnable() {
							
							public void run() {
								b.highlight();
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								b.dim();
								
							}
						});
						buttonPress.start();
						

						if(acceptingInput && sequence.get(sequenceIndex).getButton() == b){
							sequenceIndex++;
						}else if(acceptingInput){
							gameOver();
							return;
						}
						if(sequenceIndex == sequence.size()){
							Thread nextRound = new Thread(SimonScreenDaniel.this);
							nextRound.start();
						}
					}

			});
			viewObjects.add(buttons[i]);
		}
		progress = getProgress();
		label = new TextLabel(130,230,300,40,"Let's play Simon!");
		sequence = new ArrayList<MoveInterfaceDaniel>();
		//add 2 moves to start
		lastSelected = -1;
		sequence.add(randomMove());
		sequence.add(randomMove());
		roundNumber = 0;

		viewObjects.add(progress);
		viewObjects.add(label);
	}

	public void gameOver() {
		progress.gameOver();
	}

	public void nextRound() {
		acceptingInput = false;
		roundNumber ++;
		progress.setRound(roundNumber);
		sequence.add(randomMove());
		progress.setSequenceLength(sequence.size());
		changeText("Simon's turn.");
		label.setText("");
		showSequence();
		changeText("Your turn.");
		label.setText("");
		acceptingInput = true;
		sequenceIndex = 0;
	}


	private MoveInterfaceDaniel randomMove() {
		int select = (int) (Math.random()*buttons.length);
		while(select == lastSelected){
			select = (int) (Math.random()*buttons.length);
		}
		lastSelected = select;
		return new getMove(buttons[select]);
	}

	private ProgressInterfaceDaniel getProgress() {
		return new Progress();
	}

	private ButtonInterfaceDaniel getAButton() {
		return new Button();
	}

	private void changeText(String string) {
		try{
			label.setText(string);
			Thread.sleep(1000);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void run() {
		changeText("");
		nextRound();
	}


	private void showSequence() {
		ButtonInterfaceDaniel b = null;
		for(MoveInterfaceDaniel m: sequence){
			if(b!=null)b.dim();
			b = m.getButton();
			b.highlight();
			try {
				Thread.sleep((long)(2000*(2.0/(roundNumber+2))));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		b.dim();
	}
}	