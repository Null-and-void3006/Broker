package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import actions.BrowsePathAction;
import actions.CloseAction;
import actions.CloseDialogAction;

public class ImportPathDialog extends JDialog {
	
	
		private static ImportPathDialog instance = null;
	
		private JLabel pathLabel = new JLabel("File Path:");
		private JButton pathBrowseButton = new JButton("Browse");
		private JTextField pathField = new JTextField();
		private JButton okBtn = new JButton("OK");
		private JButton cancelBtn = new JButton("Cancel");
		private JPanel centerPanel = new JPanel(new GridBagLayout());
		private JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		private JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		public ImportPathDialog(){
			setSize(635, 300);
			this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, 
				    (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
			setLayout(new BorderLayout());
			setModal(true);
			initCenter();
			initTop();
			initBottom();
		}
		
		private void initCenter(){		
	        GridBagConstraints constraints = new GridBagConstraints();

	        constraints = getConstraints(0, 0);
	        centerPanel.add(pathLabel, constraints);
	        
	        constraints = getConstraints(1, 0);
	        pathField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	        pathField.setPreferredSize(new Dimension(395, 30));
	        centerPanel.add(pathField, constraints);
	        
	        constraints = getConstraints(2, 0);
	        pathBrowseButton.setPreferredSize(new Dimension(80, 30));
	        pathBrowseButton.addActionListener(new BrowsePathAction(centerPanel, pathField, okBtn));
	        centerPanel.add(pathBrowseButton, constraints);
	        
	        this.add(centerPanel, BorderLayout.CENTER);
		}
		
		public void initTop() {
			JLabel welcomeLabel = new JLabel("Select JSON file for import");
			welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
			welcomeLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5));
			topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
			topPanel.setBackground(Color.WHITE);
			topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
			topPanel.add(welcomeLabel, Alignment.LEADING);
			topPanel.setPreferredSize(new Dimension(0, 50));
			add(topPanel, BorderLayout.NORTH);
		}
		
		private void initBottom() {
			bottomPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			okBtn.setEnabled(false);
			okBtn.setPreferredSize(new Dimension(80, 30));
			okBtn.addActionListener(new CloseDialogAction(this));
			bottomPanel.add(okBtn);
			
			cancelBtn.setPreferredSize(new Dimension(80, 30));
			cancelBtn.addActionListener(new CloseDialogAction(this));
			bottomPanel.add(cancelBtn);
			
			this.add(bottomPanel, BorderLayout.SOUTH);
		}
		
		protected GridBagConstraints getConstraints(int x, int y){
	        GridBagConstraints c = new GridBagConstraints();
	        c.gridx = x;
	        c.gridy = y;
	        c.insets = new Insets(10, 10, 0, 0);
	        c.anchor = GridBagConstraints.WEST;
	        c.fill = GridBagConstraints.BOTH;
	        return c;
	    }

		public JButton getPathBrowseButton() {
			return pathBrowseButton;
		}

		public JTextField getPathField() {
			return pathField;
		}

		public JButton getOkBtn() {
			return okBtn;
		}

		public JButton getCancelBtn() {
			return cancelBtn;
		}
		
		public static ImportPathDialog getInstance() {
			if (instance == null) {
				instance = new ImportPathDialog();
			}
			return instance;
		}
		
	}


