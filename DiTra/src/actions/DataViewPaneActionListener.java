package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import actions.dataStorage.FetchNextBlockAction;
import gui.dataStorage.DataStorageView;
import gui.dataStorage.DataTable;
import gui.dataStorage.EntityTab;
import model.Entity;
import model.Relation;

public class DataViewPaneActionListener implements MouseListener{
	EntityTab clickedEntityTab;
	
	
	public DataViewPaneActionListener()
	{
		this.clickedEntityTab = null;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//tab is our clicked tab which containt infomation on the current selected entity
		
		clickedEntityTab = (EntityTab)arg0.getComponent();
		
		Entity currEntity = clickedEntityTab.getEntity();
		
		//We need to iterate throgh its relations
		for (Relation relation : currEntity.getRelations())
		{
			// Otvara se tab i posatavlja potrebne tabele
			EntityTab newTab = new EntityTab(relation.getToEntity());
            DataStorageView.getInstance().getRelationTabPane().add(relation.getToEntity().getName(), new EntityTab(relation.getToEntity()));     
            DataStorageView.getInstance().getRelationTabPane().setSelectedComponent(newTab);            
            DataStorageView.getInstance().refreshTabPane();
            
          
            DataTable table;
			try {
				   table = FetchNextBlockAction.getTableWithData(relation.getToEntity());
				   DataStorageView.getInstance().refreshTab(newTab, table);  
		           DataStorageView.getInstance().updateParamsLabels();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
