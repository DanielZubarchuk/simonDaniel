package guiPractice8.simonGame;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import guiPractice8.component.Component;

public class Progress extends Component implements ProgressInterfaceDaniel {

	private String round;
	private String sequence;
	private boolean gameOver;
	
	public Progress() {
		super(60, 60, 120, 50);
	}

	@Override
	public void update(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		FontMetrics fm = g.getFontMetrics();
		
		if(gameOver){
			g.setColor(new Color(255, 80, 90));
			g.fillRect(0, 0, 120, 50);
			g.setColor(Color.white);
			String go = "GAME OVER! You lost!";
			g.drawString(go, (120 - fm.stringWidth(go))/2, 20);
			g.drawString(sequence, (120 - fm.stringWidth(sequence))/2, 40);

		}else{
			g.setColor(new Color(220,255,230));
			g.fillRect(0, 0, 120, 50);
			g.setColor(Color.black);
			g.drawRect(0, 0, 120 - 1, 50 - 1);
			if(round !=null && sequence != null){

				g.drawString(round, (120 - fm.stringWidth(round))/2, 20);
				g.drawString(sequence, (120 - fm.stringWidth(sequence))/2, 40);
			}
		}
	}
	
	@Override
	public void gameOver() {
		gameOver = true;
		update();
	}

	@Override
	public void setRound(int r) {
		round = "Round# " + r;
		update();

	}

	@Override
	public void setSequenceSize(int s) {
		sequence = "Sequence length "  + s;
		update();

	}
}
