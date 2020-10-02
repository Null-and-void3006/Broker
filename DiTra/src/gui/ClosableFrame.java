package gui;

import javax.swing.JFrame;

public abstract class ClosableFrame extends JFrame{

	public void closeSelf() {
		this.setVisible(false);
	}
}
