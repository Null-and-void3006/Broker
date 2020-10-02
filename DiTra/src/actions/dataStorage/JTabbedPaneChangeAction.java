package actions.dataStorage;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import gui.dataStorage.DataStorageView;
import gui.dataStorage.EntityTab;

public class JTabbedPaneChangeAction implements ChangeListener{

	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		EntityTab eTab = (EntityTab)DataStorageView.getInstance().getTabPane().getSelectedComponent();
		if (eTab == null)
			return;
		DataStorageView.getInstance().refreshTab(eTab, eTab.getTable());
		DataStorageView.getInstance().updateParamsLabels();
	}

}
