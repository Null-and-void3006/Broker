package gui.dataStorage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import net.miginfocom.swing.MigLayout;

public class StatesDialog extends JFrame{
	private static StatesDialog instance;
	
	private JPanel statesPane;
	
	public StatesDialog(){
		setSize(700, 400);
		this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, 
			    (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
		setLayout(new BorderLayout());
	}
	
	public void initPanes(){
				statesPane = new JPanel();
				statesPane.setLayout(new MigLayout());
				statesPane.setBackground(new Color(224, 224, 209));
				statesPane.setBorder(new MatteBorder(0, 2, 0, 2, Color.gray));
				statesPane.setFont(new Font("Segoe UI", Font.PLAIN, 16));
				statesPane.setPreferredSize(new Dimension(690, 490));
	}
	
	public static StatesDialog getInstance()
	{
		if (instance == null)
			instance = new StatesDialog();
		
		return instance;
	}

	public JPanel getStatesPane() {
		return statesPane;
	}

	public void setStatesPane(JPanel statesPane) {
		this.statesPane = statesPane;
	}

	public static void setInstance(StatesDialog instance) {
		StatesDialog.instance = instance;
	}
}
