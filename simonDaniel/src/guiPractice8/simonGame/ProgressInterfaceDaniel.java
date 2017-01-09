package guiPractice8.simonGame;

import guiPackage.components.Visible;

public interface ProgressInterfaceDaniel extends Visible {
	void gameOver();

	void setRound(int roundNumber);

	void setSequenceLength(int size);
}
