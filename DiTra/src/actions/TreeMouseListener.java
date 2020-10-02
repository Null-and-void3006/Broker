package actions;

import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import actions.dataStorage.FetchNextBlockAction;
import controller.MainController;
import gui.MainFrame;
import gui.dataStorage.DataStorageView;
import gui.dataStorage.DataTable;
import gui.dataStorage.EntityTab;
import model.Entity;
import model.InfNode;

public class TreeMouseListener extends MouseAdapter {

	@Override
	public void mouseClicked(MouseEvent e) {
		JTree tree = MainFrame.getInstance().getTree();
		
		if (e.isShiftDown() && e.getClickCount() == 1) {
            InfNode node = (InfNode) tree.getLastSelectedPathComponent();
            if (node == null || !(node instanceof Entity)) return; 
            
            //ukoliko tab ne postoji metoda getEntityTab() ga kreira, u suprotnom vraca postojeci
            EntityTab entityTab = DataStorageView.getInstance().getEntityTab((Entity)node);
            
            // Otvara se tab i posatavlja potrebne tabele
            DataStorageView.getInstance().getTabPane().add(entityTab.getEntity().getName(), entityTab);     
            DataStorageView.getInstance().getTabPane().setSelectedComponent(entityTab);            
            DataStorageView.getInstance().refreshTabPane();
            
          
            DataTable table;
			try {
				   table = FetchNextBlockAction.getTableWithData((Entity)node);
				   DataStorageView.getInstance().refreshTab(entityTab, table);  
		           DataStorageView.getInstance().updateParamsLabels();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

           
        }
	}
}
