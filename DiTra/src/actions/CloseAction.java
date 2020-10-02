package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import gui.ClosableFrame;

public class CloseAction implements ActionListener {
	private ClosableFrame window;
	
	public CloseAction(ClosableFrame window) {
		this.window = window;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		window.closeSelf();
	}

}
