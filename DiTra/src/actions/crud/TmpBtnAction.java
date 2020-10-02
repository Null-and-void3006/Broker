package actions.crud;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class TmpBtnAction implements ActionListener{
	private static JDialog dialog;
	
	public TmpBtnAction(JDialog dialog)
	{
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.dialog.dispose();
		
	}

}
