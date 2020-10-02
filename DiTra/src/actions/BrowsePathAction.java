package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class BrowsePathAction implements ActionListener {
	private JPanel parent;
	private JTextField pathField;
	private JButton button;
	
	public BrowsePathAction(JPanel parent, JTextField pathField, JButton button) {
		this.parent = parent;
		this.pathField = pathField;
		this.button = button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = chooser.showOpenDialog(parent);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	pathField.setText(chooser.getSelectedFile().getAbsolutePath());
	    	button.setEnabled(true);
	    }
	}
}
