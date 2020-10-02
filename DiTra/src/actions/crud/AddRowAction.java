package actions.crud;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.crud.AddRowDialog;

public class AddRowAction implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		AddRowDialog.getInstance().setVisible(true);
	}

}
