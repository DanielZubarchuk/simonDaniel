package guiPractice8.simonGame;

import java.awt.Color;

import java.util.ArrayList;
import java.util.List;

import guiPractice8.component.Action;
import guiPractice8.component.ClickableScreen;
import guiPractice8.component.TextLabel;
import guiPractice8.component.Visible;

public class SimonScreenDaniel extends ClickableScreen implements Runnable{

	private ArrayList<MoveInterfaceDaniel> moves;
	private ProgressInterfaceDaniel progress;
	private TextLabel label;
	private TextLabel timeLabel;
	private double timeLeft;
	private int roundNumber;
	boolean acceptingInput;
	private int sequenceIndex;
	private int lastSelectedButton;
	private ButtonInterfaceDaniel[] buttons;
	
	public SimonScreenDaniel(int width, int height) {
		super(width, height);
		//timeLeft = 30.0;
		Thread play = new Thread(this);
		play.start();
	}

	@Override
	public void run() {
		label.setText("");
	    nextRound();
	}

	private void nextRound() {
		acceptingInput = false;
		roundNumber ++;
		moves.add(randomMove());
		progress.setRound(roundNumber);
		progress.setSequenceLength(moves.size());
		changeText("Simon's turn.");
		label.setText("");
		playSequence();
		changeText("Your turn.");
		label.setText("");
		acceptingInput = true;
		sequenceIndex = 0;
	}

	private void playSequence() {
		ButtonInterfaceDaniel b = null;
		for(MoveInterfaceDaniel moves: moves){
			if(b!=null){
				b.dim();
			}
			b = moves.getButton();
			b.highlight();
			try {
				Thread.sleep((long)(2000*(2.0/(roundNumber+2))));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		b.dim();
		
	}

	private void changeText(String string) {
		try{
			label.setText(string);
			Thread.sleep(1000);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {

		addButtons();
		progress = getProgress();
		label = new TextLabel(130,230,300,40,"Let's play Simon!");
		moves = new ArrayList<MoveInterfaceDaniel>();
		//add 2 moves to start
		lastSelectedButton = -1;
		moves.add(randomMove());
		moves.add(randomMove());
		roundNumber = 0;
		viewObjects.add(progress);
		viewObjects.add(label);
		
	}

	private MoveInterfaceDaniel randomMove() {
		ButtonInterfaceDaniel bttn = null;
		return new getMove(bttn);
	}

	/**
	Placeholder until partner finishes implementation of ProgressInterface
	*/
	private ProgressInterfaceDaniel getProgress() {
		// TODO Auto-generated method stub
		return new Progress();
	}

	private void addButtons() {
		// helper method
		int numberOfButtons = 5;
		buttons = new ButtonInterfaceDaniel[numberOfButtons];
		Color[] color = {Color.blue, Color.orange, Color.pink, Color.blue, Color.green};
		for(int i = 0; i < numberOfButtons;i++){
			buttons[i] = getAButton();
			final ButtonInterfaceDaniel b = getAButton();
			buttons[i].setColor(color[i]);
			buttons[i].setX(160 + (int)(100*Math.cos(i*2*Math.PI/(numberOfButtons))));
			buttons[i].setY(200 - (int)(100*Math.sin(i*2*Math.PI/(numberOfButtons))));
			buttons[i].setAction(new Action(){

				public void act(){
					Thread blink = new Thread(new Runnable(){

						public void run(){
						
							b.highlight();
							try {
								Thread.sleep(800);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							b.dim();
						}
						});
					blink.start();
					
					if(b == moves.get(sequenceIndex).getButton()){
						sequenceIndex++;
					}else{
						gameOver();
						return;
					}
					if(sequenceIndex == moves.size()){
						Thread nextRound = new Thread(SimonScreenDaniel.this);
						nextRound.start();
					}
				}

				});
		}
		
	}

	protected void gameOver() {
		progress.gameOver();
	}

	private ButtonInterfaceDaniel getAButton() {
		return null;
		// helper method
		
	}

}
