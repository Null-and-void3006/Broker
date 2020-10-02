package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.MainController;
import gui.HelpFrame;

public class HelpAction implements ActionListener {	
	public HelpAction(){
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		HelpFrame.getInstance().setVisible(true);
	}

}
