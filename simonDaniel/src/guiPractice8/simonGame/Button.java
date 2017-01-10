package guiPractice8.simonGame;

import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;

import guiPractice8.component.Action;
import guiPractice8.component.Component;

public class Button extends Component implements ButtonInterfaceDaniel {

	private Action action;
	private Color color;
	private Color displayColor;
	private boolean highlight;
	
	public Button() {
		super(0, 0, 40, 40);
	}

	@Override
	public void highlight() {
		if(color != null) 
			displayColor = color;
		highlight = true;
		update();
	}

	@Override
	public void dim() {
		displayColor = Color.cyan;
		highlight = false;
		update();
	}

	@Override
	public void setAction(Action action) {
		this.action = action;
	}

	@Override
	public void act() {
		action.act();
		
	}

	@Override
	public boolean isHovered(int x, int y) {
		return Math.sqrt(Math.pow(x - (getX() + 40/2), 2) + 
				Math.pow(y - (getY() + 40/2), 2)) < 40/2;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
		displayColor = color;
		update();
	}

	@Override
	public void update(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(displayColor != null) 
			g.setColor(displayColor);
		else g.setColor(Color.gray);
		
		g.fillOval(0, 0, 40, 40);
		g.setColor(Color.black);
		g.drawOval(0, 0, 40 - 1, 40 - 1);
		
		if(highlight){
			g.setColor(Color.white);
			Polygon p = new Polygon();
			int s = (int)(5/8.0 * 40);
			int t = (int)(1.0/5*40)+4;
			p.addPoint(s - 4, t - 4);
			p.addPoint(s + 7, t - 2);
			p.addPoint(s + 10, t);
			p.addPoint(s + 14, t + 10);
			p.addPoint(s + 12, t + 14);
			p.addPoint(s + 8, t + 3);
			g.fill(p);
		}
	}


	private String name;
	public void setName(String s){
		this.name = s;
	}
	
	public String toString(){
		return name;
	}
}