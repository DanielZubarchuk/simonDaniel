package guiPractice8.simonGame;

import java.awt.Color;

import guiPractice8.component.Action;
import guiPractice8.component.Clickable;

public interface ButtonInterfaceDaniel extends Clickable {
	void setColor(Color color);

	void highlight();

	void dim();

	void setAction(Action action);

	void setName(String name);

	void setX(int i);

	void setY(int i);
}
