package actions.connection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import controller.MainController;
import gui.ImportPathDialog;
import gui.SelectDatabaseDialog;

public class OpenResourceAction implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		SelectDatabaseDialog.getInstance().setVisible(true);
		
	}

}
