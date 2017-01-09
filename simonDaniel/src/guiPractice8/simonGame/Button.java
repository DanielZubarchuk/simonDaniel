package guiPractice8.simonGame;

import java.awt.Color;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;

import guiPractice8.component.Action;
import guiPractice8.component.Clickable;
import guiPractice8.component.TextLabel;

public class Button extends TextLabel implements Clickable{

	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;
	private Action action;
	private Color c;
	private Color displayColor;
	private boolean highlight;
	
	public Button() {
		super(0, 0, WIDTH,HEIGHT);
	}

	public boolean isHovered(int x, int y) {
		double distance = Math.sqrt(Math.pow(x - (getX() + WIDTH / 2), 2) + 
				Math.pow(y-(getY() + HEIGHT/2), 2));
		return distance < WIDTH/2;
	}

	public void act() {
		action.act();
	}

	public void setColor(Color color) {
		this.c = color;
		displayColor = c;
		update();
	}

	public void highlight() {
		if(c != null) displayColor = c;
		highlight = true;
		update();
	}

	public void dim() {
		displayColor = Color.gray;
		highlight = false;
		update();
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public void update(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(displayColor != null) g.setColor(displayColor);
		else g.setColor(Color.gray);
		g.fillOval(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.black);
		g.drawOval(0, 0, WIDTH-1, HEIGHT-1);
	}
	
	private String name;
	public void setName(String s){
		this.name = s;
	}
	
	public String toString(){
		return name;
	}

	
}
