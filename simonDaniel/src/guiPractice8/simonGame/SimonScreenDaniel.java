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
	//public ArrayList<ButtonInterfaceTamanna> button;
	private ButtonInterfaceDaniel[] button;
	public ArrayList<MoveInterfaceDaniel> moveList;
	public ArrayList<MoveInterfaceDaniel> sequenceOfMoves;
	public ProgressInterfaceDaniel progress;
	public TextLabel label;
	public int roundNumber;
	public int sequenceIndex;
	public static boolean acceptingInput;	
	
	private int lastSelectedButton;
	
	public SimonScreenDaniel(int width, int height) {
		super(width, height);
		Thread app = new Thread(this);
		app.start();
	}

	public void run() {
		if(label.equals(null)) label.setText("");
		nextRound();
	}

	public void nextRound() {
		acceptingInput =false;
		roundNumber ++;
		sequenceOfMoves.add(randomMove());
		//check
		progress.setRound(roundNumber);
		progress.setSequenceSize(sequenceOfMoves.size());
		
		changeText("Simon's Turn");
		label.setText("");
		playSequence();
		changeText("Your Turn");
		acceptingInput = true;
		sequenceIndex = 0;
	}

	private void playSequence() {
		ButtonInterfaceDaniel b = null;
		for(int i = 0; i < sequenceOfMoves.size(); i++){
			if(b != null) b.dim();
			
			b = sequenceOfMoves.get(sequenceIndex).getButton();
			b.highlight();
		
			//10 seconds time
			int sleepTime = 10000/roundNumber;
			if(sleepTime<=0)sleepTime=2;
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		b.dim();
	}

	private void changeText(String string) {
		try{
			label.setText(string);
			Thread.sleep(1000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	public void initAllObjects(ArrayList<Visible> viewObjects) {
		addButtons(viewObjects);
		progress = getProgress();
		label = new TextLabel(130,230,300,40,"Let's play Simon!");
		sequenceOfMoves = new ArrayList<MoveInterfaceDaniel>();
		//add 2 moves to start
		lastSelectedButton = -1;
		sequenceOfMoves.add(randomMove());
		sequenceOfMoves.add(randomMove());
		roundNumber = 0;
		viewObjects.add(progress);
		viewObjects.add(label);
	}

	public MoveInterfaceDaniel randomMove() {
		ButtonInterfaceDaniel b = null;
		int randNum = (int)(Math.random()*button.length);
		
		while(randNum == lastSelectedButton){
			randNum = (int)(Math.random()*button.length);
		}
		
		b = button[randNum];
		lastSelectedButton = randNum;
		return getMove(b);
	}

	public MoveInterfaceDaniel getMove(ButtonInterfaceDaniel b) {
		return null;
	}

	public ProgressInterfaceDaniel getProgress() {
		//Partner's code
		return null;
	}

	public void addButtons(ArrayList<Visible> viewObjects) {
		int numOfButtons = 4;
		Color[] colors= {Color.blue,Color.red, Color.yellow, Color.green};
		
		for(int i= 0; i < numOfButtons; i++){
			
			final ButtonInterfaceDaniel b = getAButton();
			b.setColor(colors[i]);
			b.setX(50);
			b.setY(50);
			
			b.setAction(new Action(){
				public void act() {
					if(acceptingInput){
						Thread blink = new Thread(new Runnable(){
							public void run() {
								b.highlight();
								try {
									Thread.sleep(800);
									b.dim();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							
						});
						blink.start();
					}
				}
				
			});
			
			if (b == sequenceOfMoves.get(sequenceIndex)){
				sequenceIndex++;
			}
			else{
				progress.gameOver();
			}
			if (sequenceIndex == sequenceOfMoves.size()){
				Thread nextRound = new Thread(SimonScreenDaniel.this);
				nextRound.start();
			}
			viewObjects.add(b);
		}
		
	}

	private ButtonInterfaceDaniel getAButton() {
		return null;
	}

	@Override
	public void initAllObjects(List<Visible> arg0) {
		// TODO Auto-generated method stub
		
	}
}

//package guiPractice8.simonGame;
//
//import java.awt.Color;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import guiPackage.components.Action;
//import guiPackage.components.TextLabel;
//import guiPackage.components.Visible;
//import guiPackage.sampleGames.ClickableScreen;
//
//public class SimonScreenDaniel extends ClickableScreen implements Runnable{
//
//	private ArrayList<MoveInterfaceDaniel> moves;
//	private ProgressInterfaceDaniel progress;
//	private TextLabel label;
//	private double timeLeft;
//	private int roundNumber;
//	boolean acceptingInput;
//	private int sequenceIndex;
//	private int lastSelectedButton;
//	private ButtonInterfaceDaniel[] buttons;
//	
//	public SimonScreenDaniel(int width, int height) {
//		super(width, height);
//		//timeLeft = 30.0;
//		Thread play = new Thread(this);
//		play.start();
//	}
//
//	@Override
//	public void run() {
//		label.setText("");
//	    nextRound();
//	}
//
//	private void nextRound() {
//		acceptingInput = false;
//		roundNumber ++;
//		moves.add(randomMove());
//		progress.setRound(roundNumber);
//		progress.setSequenceLength(moves.size());
//		changeText("Simon's turn.");
//		label.setText("");
//		playSequence();
//		changeText("Your turn.");
//		label.setText("");
//		acceptingInput = true;
//		sequenceIndex = 0;
//	}
//
//	private void playSequence() {
//		ButtonInterfaceDaniel b = null;
//		for(MoveInterfaceDaniel moves: moves){
//			if(b!=null){
//				b.dim();
//			}
//			b = moves.getButton();
//			b.highlight();
//			try {
//				Thread.sleep((long)(2000*(2.0/(roundNumber+2))));
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		b.dim();
//		
//	}
//
//	private void changeText(String string) {
//		try{
//			label.setText(string);
//			Thread.sleep(1000);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//	}
//
//	public void initAllObjects(List<Visible> viewObjects) {
//
//		addButtons();
//		progress = getProgress();
//		label = new TextLabel(130,230,300,40,"Let's play Simon!");
//		moves = new ArrayList<MoveInterfaceDaniel>();
//		//add 2 moves to start
//		lastSelectedButton = -1;
//		moves.add(randomMove());
//		moves.add(randomMove());
//		roundNumber = 0;
//		viewObjects.add(progress);
//		viewObjects.add(label);
//		
//	}
//
//	private MoveInterfaceDaniel randomMove() {
//		ButtonInterfaceDaniel bttn = null;
//		return new getMove(bttn);
//	}
//
//	/**
//	Placeholder until partner finishes implementation of ProgressInterface
//	*/
//	private ProgressInterfaceDaniel getProgress() {
//		// TODO Auto-generated method stub
//		return new Progress();
//	}
//
//	private void addButtons() {
//		// helper method
//		int numberOfButtons = 5;
//		buttons = new ButtonInterfaceDaniel[numberOfButtons];
//		Color[] color = {Color.blue, Color.orange, Color.pink, Color.blue, Color.green};
//		for(int i = 0; i < numberOfButtons;i++){
//			buttons[i] = getAButton();
//			final ButtonInterfaceDaniel b = getAButton();
//			buttons[i].setColor(color[i]);
//			buttons[i].setX(160 + (int)(100*Math.cos(i*2*Math.PI/(numberOfButtons))));
//			buttons[i].setY(200 - (int)(100*Math.sin(i*2*Math.PI/(numberOfButtons))));
//			buttons[i].setAction(new Action(){
//
//				public void act(){
//					Thread blink = new Thread(new Runnable(){
//
//						public void run(){
//						
//							b.highlight();
//							try {
//								Thread.sleep(800);
//							} catch (InterruptedException e) {
//								e.printStackTrace();
//							}
//							b.dim();
//						}
//						});
//					blink.start();
//					
//					if(b == moves.get(sequenceIndex).getButton()){
//						sequenceIndex++;
//					}else{
//						gameOver();
//						return;
//					}
//					if(sequenceIndex == moves.size()){
//						Thread nextRound = new Thread(SimonScreenDaniel.this);
//						nextRound.start();
//					}
//				}
//
//				});
//		}
//		
//	}
//
//	protected void gameOver() {
//		progress.gameOver();
//	}
//
//	private ButtonInterfaceDaniel getAButton() {
//		return null;
//		// helper method
//		
//	}
//
//	@Override
//	public void initAllObjects(ArrayList<Visible> arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//}