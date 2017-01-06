package guiPractice8.simonGame;

import guiPractice8.component.Visible;

public interface ProgressInterfaceDaniel extends Visible {
	void gameOver();

	void setRound(int roundNumber);

	void setSequenceLength(int size);
}
