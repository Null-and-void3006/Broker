package controller;

import javax.swing.JTabbedPane;

import actions.dataStorage.JTabbedPaneChangeAction;
import gui.MainFrame;
import gui.MainMenuBar;
import gui.dataStorage.DataStorageView;

public class JTabbedPaneController {
	
	private static JTabbedPaneController instance = null;	
	private JTabbedPane tabPane;
	
	private JTabbedPaneController() {
		this.tabPane = DataStorageView.getInstance().getTabPane();
		setListeners();
	}
	
	private void setListeners() {
		this.tabPane.addChangeListener(new JTabbedPaneChangeAction());
	}
	
	public static JTabbedPaneController getInstance() {
		if (instance == null) {
			instance = new JTabbedPaneController();
		}
		return instance;
	}

}
