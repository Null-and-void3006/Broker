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
import actions.connection.CloseDatabaseDialogAction;
import actions.connection.ConnectToDefaultDatabaseAction;

public class SelectDatabaseDialog extends JDialog{
	private static SelectDatabaseDialog instance = null;
	
	private JLabel ipLabel = new JLabel("IP:");
	private JLabel databaseLabel = new JLabel("Database:");
	private JLabel usernameLabel = new JLabel("Username:");
	private JLabel passwordLabel = new JLabel("Password:");
	
	private JTextField ipField = new JTextField();
	private JTextField databaseField = new JTextField();
	private JTextField usernameField = new JTextField();
	private JTextField passwordField = new JTextField();
	
	
	private JButton okBtn = new JButton("OK");
	private JButton cancelBtn = new JButton("Cancel");
	private JButton defaultDatabaseBtn = new JButton("Connect to default database");
			
	private JPanel centerPanel = new JPanel(new GridBagLayout());
	private JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	
	
	protected GridBagConstraints getConstraints(int x, int y){
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        c.insets = new Insets(10, 10, 0, 0);
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.BOTH;
        return c;
    }
	
	
	
	
	
	//MAIN METHOD
	public SelectDatabaseDialog(){
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

        //adding ip label and ip field
        constraints = getConstraints(0, 0);
        centerPanel.add(ipLabel, constraints);
        constraints = getConstraints(1, 0);
        ipField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ipField.setPreferredSize(new Dimension(395, 30));
        centerPanel.add(ipField, constraints);
        
        //adding database label and field
        constraints = getConstraints(0, 1);
        centerPanel.add(databaseLabel, constraints);
        constraints = getConstraints(1, 1);
        databaseField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        databaseField.setPreferredSize(new Dimension(395, 30));
        centerPanel.add(databaseField, constraints);
        
        //adding username label and field
        constraints = getConstraints(0, 2);
        centerPanel.add(usernameLabel, constraints);
        constraints = getConstraints(1, 2);
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        usernameField.setPreferredSize(new Dimension(395, 30));
        centerPanel.add(usernameField, constraints);
        
        //adding pasword label and field
        constraints = getConstraints(0, 3);
        centerPanel.add(passwordLabel, constraints);
        constraints = getConstraints(1, 3);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordField.setPreferredSize(new Dimension(395, 30));
        centerPanel.add(passwordField, constraints);
        
      
        
        this.add(centerPanel, BorderLayout.CENTER);
	}
	
	public void initTop() {
		JLabel welcomeLabel = new JLabel("Insert database credentials for connection");
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
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints = getConstraints(0, 0);
		
		bottomPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		okBtn.setEnabled(false);
		okBtn.setPreferredSize(new Dimension(80, 30));
		okBtn.addActionListener(new CloseDatabaseDialogAction(this));
		bottomPanel.add(okBtn, constraints);
		
		constraints = getConstraints(0, 1);
		cancelBtn.setPreferredSize(new Dimension(80, 30));
		cancelBtn.addActionListener(new CloseDatabaseDialogAction(this));
		bottomPanel.add(cancelBtn, constraints);
		
		
		constraints = getConstraints(1, 0);
		defaultDatabaseBtn.setPreferredSize(new Dimension(300, 30));
		defaultDatabaseBtn.addActionListener(new ConnectToDefaultDatabaseAction(this));
		bottomPanel.add(defaultDatabaseBtn, constraints);
		
		this.add(bottomPanel, BorderLayout.SOUTH);
	}
	
	
	
	
	
	public static SelectDatabaseDialog getInstance() {
		if (instance == null) {
			instance = new SelectDatabaseDialog();
		}
		return instance;
	}





	public JLabel getIpLabel() {
		return ipLabel;
	}





	public void setIpLabel(JLabel ipLabel) {
		this.ipLabel = ipLabel;
	}





	public JLabel getDatabaseLabel() {
		return databaseLabel;
	}





	public void setDatabaseLabel(JLabel databaseLabel) {
		this.databaseLabel = databaseLabel;
	}





	public JLabel getUsernameLabel() {
		return usernameLabel;
	}





	public void setUsernameLabel(JLabel usernameLabel) {
		this.usernameLabel = usernameLabel;
	}





	public JLabel getPasswordLabel() {
		return passwordLabel;
	}





	public void setPasswordLabel(JLabel passwordLabel) {
		this.passwordLabel = passwordLabel;
	}





	public JTextField getIpField() {
		return ipField;
	}





	public void setIpField(JTextField ipField) {
		this.ipField = ipField;
	}





	public JTextField getDatabaseField() {
		return databaseField;
	}





	public void setDatabaseField(JTextField databaseField) {
		this.databaseField = databaseField;
	}





	public JTextField getUsernameField() {
		return usernameField;
	}





	public void setUsernameField(JTextField usernameField) {
		this.usernameField = usernameField;
	}





	public JTextField getPasswordField() {
		return passwordField;
	}





	public void setPasswordField(JTextField passwordField) {
		this.passwordField = passwordField;
	}





	public JButton getOkBtn() {
		return okBtn;
	}





	public void setOkBtn(JButton okBtn) {
		this.okBtn = okBtn;
	}





	public JButton getCancelBtn() {
		return cancelBtn;
	}





	public void setCancelBtn(JButton cancelBtn) {
		this.cancelBtn = cancelBtn;
	}





	public JButton getDefaultDatabaseBtn() {
		return defaultDatabaseBtn;
	}





	public void setDefaultDatabaseBtn(JButton defaultDatabaseBtn) {
		this.defaultDatabaseBtn = defaultDatabaseBtn;
	}





	public JPanel getCenterPanel() {
		return centerPanel;
	}





	public void setCenterPanel(JPanel centerPanel) {
		this.centerPanel = centerPanel;
	}





	public JPanel getTopPanel() {
		return topPanel;
	}





	public void setTopPanel(JPanel topPanel) {
		this.topPanel = topPanel;
	}





	public JPanel getBottomPanel() {
		return bottomPanel;
	}





	public void setBottomPanel(JPanel bottomPanel) {
		this.bottomPanel = bottomPanel;
	}





	public static void setInstance(SelectDatabaseDialog instance) {
		SelectDatabaseDialog.instance = instance;
	}
	
}
