package actions.crud;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.crud.EditRowDialog;

public class EditRowAction implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		EditRowDialog.getInstance().setVisible(true);
	}

}
