package actions.connection;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import gui.SelectDatabaseDialog;

import java.awt.event.ActionEvent;

public class CloseDatabaseDialogAction implements ActionListener{
	private SelectDatabaseDialog dialog;
	
	public CloseDatabaseDialogAction(SelectDatabaseDialog dialog) {
		this.dialog = dialog;
	}
		
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.dialog.dispose();
	}

}
