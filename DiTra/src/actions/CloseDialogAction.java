package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.ImportPathDialog;

public class CloseDialogAction implements ActionListener {
  
	ImportPathDialog dialog;
	
	public CloseDialogAction(ImportPathDialog dialog)
	{
		this.dialog=dialog;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		this.dialog.dispose();
	}

}
