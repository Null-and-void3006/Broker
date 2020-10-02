package app;
import java.awt.Font;

import javax.swing.UIManager;

import controller.MainController;
import java.sql.*;  

public class Main {
	
	public static void main(String[] args) {
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setFont();
		MainController.getInstance().start();
	}
	 
	 private static void setFont(){
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			//Just a random comment passing by.....
			
			UIManager.put("Button.font", new Font("Segoe UI", Font.PLAIN, 14));
			UIManager.put("ComboBox.font", new Font("Segoe UI", Font.PLAIN, 17));
			UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 17));
			UIManager.put("MenuBar.font", new Font("Segoe UI", Font.PLAIN, 17));
			UIManager.put("MenuItem.font", new Font("Segoe UI", Font.PLAIN, 17));
			UIManager.put("Menu.font", new Font("Segoe UI", Font.PLAIN, 17));
			UIManager.put("PopupMenu.font", new Font("Segoe UI", Font.PLAIN, 17));
			UIManager.put("TextField.font", new Font("Segoe UI", Font.PLAIN, 17));
			UIManager.put("ToolBar.font", new Font("Segoe UI", Font.PLAIN, 17));
			UIManager.put("Tree.font", new Font("Segoe UI", Font.PLAIN, 17));
			UIManager.getDefaults().put("Button.showMnemonics", Boolean.TRUE);
		}
}
