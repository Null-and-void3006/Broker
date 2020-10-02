package actions.connection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import gui.SelectDatabaseDialog;
import model.database.Database;

public class ConnectToDatabaseAction  implements ActionListener{
	private SelectDatabaseDialog dialog;
	
	public ConnectToDatabaseAction(SelectDatabaseDialog dialog) {
		this.dialog = dialog;
	}
	
	
	private static Connection conn = null;
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("Action implemented successfully");
		
		String ip = dialog.getIpField().getText();
		String db = dialog.getDatabaseField().getText();
		String user = dialog.getUsernameField().getText();
		String pass = dialog.getPasswordField().getText();
		
		
		conn = Database.establishParametrizedConnection(ip, db, user, pass);
		
		if(conn ==  null)
			System.out.println("There was an error");
		else
			System.out.println("Connection successful");
		
		
		
		this.dialog.dispose();
	}

}
