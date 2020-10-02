package actions.dataStorage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import controller.MainController;
import gui.dataStorage.DataStorageView;
import gui.dataStorage.DataTable;
import gui.dataStorage.EntityTab;
import model.Entity;
import model.database.Database;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FetchNextBlockAction implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {		
		// Da li je selektovan neki tabPane?
		EntityTab selectedTab = (EntityTab)DataStorageView.getInstance().getTabPane().getSelectedComponent();	
		if (selectedTab == null) return;
		
		// Koji Entity je selektovan?
		Entity selectedEntity = selectedTab.getEntity();
		
		// Postaviti novu tabelu u taj tab
		DataTable newTable;
		try {
			newTable = getTableWithData(selectedEntity);
			DataStorageView.getInstance().refreshTab(selectedTab, newTable);
			
			// Update count atributa
			selectedEntity.incCountAttribute();
			DataStorageView.getInstance().updateParamsLabels();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static DataTable getTableWithData(Entity entity) throws IOException {
		// Heder
		Object[] columns = new Object[entity.getAttributes().size()];
		for(int i = 0; i < entity.getAttributes().size(); i++) {
			columns[i] = entity.getAttributes().get(i).toString();
		}
		
		// Data
		
		//UIAbstractFile uiFile = entity.getFile();		

		Object[][] data = new Object[20][entity.getAttributes().size()];
 		
		// Ovde treba select * from limit block_factor
		data = Database.getData(entity);

		//data = uiFile.readFromFile(uiFile.getBLOCK_FACTOR(),entity);
 		DataTable table = new DataTable(data, columns);
		
		return table;
	}
	
	public static void addNewData(EntityTab eTab, Object[][] data) {
		DataTable table = eTab.getTable();
		Object[] columns = new Object[table.getColumnCount()];
		for(int i = 0; i < table.getColumnCount(); i++) {
			columns[i] = table.getColumnName(i);
		}
		
		DataTable newTable = new DataTable(data, columns);
		DataStorageView.getInstance().refreshTab(eTab, newTable);
	}
}
