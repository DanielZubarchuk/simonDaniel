package guiPractice8.simonGame;

import java.awt.Color;

import java.util.ArrayList;
import java.util.List;

import guiPractice8.component.ClickableScreen;
import guiPractice8.component.Action;
import guiPractice8.component.TextLabel;
import guiPractice8.component.Visible;

public class SimonScreenDaniel extends ClickableScreen implements Runnable {

	private TextLabel label;
	private ButtonInterfaceDaniel[] button;
	private ProgressInterfaceDaniel progress;
	private ArrayList<MoveInterfaceDaniel> sequence;
	
	private int roundNumber;
	private boolean acceptingInput;
	private int sequenceIndex;
	private int lastSelectedButton;
	
	public SimonScreenDaniel(int width, int height) {
		super(width, height);
		Thread app = new Thread(this);
		app.start();
	}

	private void playSequence() {
		ButtonInterfaceDaniel b = null;
		for(int i=0;i<sequence.size();i++){
			if(b!=null)b.dim();
			b = sequence.get(i).getButton();
			b.highlight();
			try {
				Thread.sleep((long)(2000*(2.0/(roundNumber+2))));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		b.dim();
	}

	@Override
	public void run() {
		label.setText("");
		nextRound();
	}

	public void nextRound() {
		acceptingInput =false;
		roundNumber ++;
		sequence.add(randomMove());
		progress.setRound(roundNumber);
		progress.setSequenceSize(sequence.size());
		
		changeText("Simon's Turn");
		label.setText("");
		playSequence();
		changeText("Your Turn");
		acceptingInput = true;
		sequenceIndex = 0;
	}
	
	private void changeText(String string) {
		try{
			label.setText(string);
			Thread.sleep(200);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	public MoveInterfaceDaniel randomMove() {
		ButtonInterfaceDaniel b = null;
		int randNum = (int)(Math.random()*button.length);
		while(randNum==lastSelectedButton){
			randNum = (int)(Math.random()*button.length);
		}
		b = button[randNum];
		lastSelectedButton = randNum;
		return new Move(b);
	}


	public ProgressInterfaceDaniel getProgress() {
		return new Progress();
	}

	public void addButtons(List<Visible> viewObjects) {
		int numOfButtons = 7;
		button = new ButtonInterfaceDaniel[numOfButtons];
		
		Color[] colors= {Color.orange, Color.red, Color.black, Color.yellow, 
				Color.green, Color.magenta, Color.darkGray};
		
		for(int i= 0; i < numOfButtons; i++){
			button[i] = getAButton();
			button[i].setColor(colors[i]);
			button[i].setX(260 + (int)(60*Math.cos(i * 2 * Math.PI/(numOfButtons))));
			button[i].setY(200 - (int)(60*Math.sin(i * 2 * Math.PI/(numOfButtons))));
			final ButtonInterfaceDaniel b = button[i];
			b.dim();
			b.setAction(new Action(){
				public void act() {
					Thread blink = new Thread(new Runnable(){
						public void run() {
							b.highlight();
							try {
								Thread.sleep(400);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							b.dim();
						}
							
					});
					blink.start();
					
					if(acceptingInput && sequence.get(sequenceIndex).getButton() == b){
						sequenceIndex++;
					}else if(acceptingInput){
						progress.gameOver();
						return;
					}
					
					if(sequenceIndex == sequence.size()){
						Thread nextRound = new Thread(SimonScreenDaniel.this);
						nextRound.start();
					}
				}
				
			});
			viewObjects.add(button[i]);
		}
		
	}

	private ButtonInterfaceDaniel getAButton() {
		return new Button();
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		sequence = new ArrayList<MoveInterfaceDaniel>();
		addButtons(viewObjects);
		progress = getProgress();
		label = new TextLabel(220, 310, 300, 40, "Let's play Simon!");
		
		lastSelectedButton = -1;
		sequence.add(randomMove());
		sequence.add(randomMove());
		roundNumber = 0;
		viewObjects.add(progress);
		viewObjects.add(label);
	}

}