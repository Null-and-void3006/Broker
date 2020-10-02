package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.MainController;
import gui.AboutFrame;

public class AboutAction implements ActionListener {
	
	public AboutAction(){
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		AboutFrame.getInstance().setVisible(true);
	}

}
