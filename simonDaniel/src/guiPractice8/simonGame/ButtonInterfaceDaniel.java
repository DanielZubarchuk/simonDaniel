package guiPractice8.simonGame;

import java.awt.Color;

import guiPractice8.component.Action;
import guiPractice8.component.Clickable;

public interface ButtonInterfaceDaniel extends Clickable {
	void setColor(Color color);

	void setX(int i);

	void setY(int i);
	
	void setAction(Action a);

	void highlight();

	void dim();
}
