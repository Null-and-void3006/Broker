package actions.dataStorage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import gui.dataStorage.DataStorageView;
import gui.dataStorage.EntityTab;
import model.Entity;

public class MakeTreeAction implements ActionListener{
	
	private Entity entity; // za koji entity treba da se makeTree
	
	public void MakeTreeAction() {
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TabPane?
		if (DataStorageView.getInstance().getTabPane() == null) return;
		
		// Da li je selektovan neki tabPane?
		EntityTab entityTab = (EntityTab)DataStorageView.getInstance().getTabPane().getSelectedComponent();	
		if (entityTab == null) return;
		
		// Koji Entity je selektovan?
		entity = entityTab.getEntity();
	}

}
