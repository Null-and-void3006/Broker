package controller;

import java.util.ArrayList;

import javax.swing.tree.DefaultTreeModel;

import gui.MainFrame;
import gui.dataStorage.DataStorageView;
import gui.dataStorage.StatesDialog;
import model.Attribut;
import model.Entity;
import model.InfResource;
import state.StateManager;

public class MainController {
	
	private static MainController instance = null;	
	private MainFrame mainFrame;
	private String location;
	
	private StateManager stateManager;
	
	// Lista ucitanih inf. resursa
	// U nju se stavljaju novi ucitani inf resursi preko OpenResourceAction-a
	private  ArrayList<InfResource> infResources;
	
	private MainController(){	
		TreeController.getInstance();
		MenuBarController.getInstance();
		JTabbedPaneController.getInstance();
		//MetaSchemaEditorController.getInstance();
		//EditorController.getInstance();  postaviti tek kad se kreira instanca editora
		
		mainFrame = MainFrame.getInstance();
		infResources = new ArrayList<>();
		
		// StateManager
		//stateManager = new StateManager(DataStorageView.getInstance().getStatesPane());
		
	}
	
	public void start() {
		mainFrame.setWorkspace(TreeController.getInstance().getWorkspace());
		mainFrame.setVisible(true);
		// createHardcodedTree();
	}
	
//	private void createHardcodedTree() {	
//		Entity student = new Entity("Student");
//		Attribut jmbg = new Attribut("JMBG", 0, null, false, false,0);
//		Attribut pol = new Attribut("Pol", 0, null, false, false,0);
//		Attribut ime = new Attribut("Ime", 0, null, false, false,0);
//		student.add(jmbg);
//		student.add(pol);
//		student.add(ime);
//		
//		Entity predmet = new Entity("Predmet");
//		Attribut naziv = new Attribut("Naziv", 0, null, false, false,0);
//		Attribut sifra = new Attribut("Sifra", 0, null, false, false,0);
//		predmet.add(naziv);
//		predmet.add(sifra); 
//	    
//		InfResource fakultet = new InfResource("Fakultet", "Opis", "", "", null);
//		fakultet.add(student);
//		fakultet.add(predmet);
//		addInfResource(fakultet);
//	}
	
	public void addInfResource(InfResource infRes){
		infResources.add(infRes);
		TreeController.getInstance().getWorkspace().add(infRes);
		reloadTree();
	}
	
	public void reloadTree() {
		DefaultTreeModel model = (DefaultTreeModel) mainFrame.getTree().getModel();
		model.reload();
		for (int i = 0; i < mainFrame.getTree().getRowCount(); i++) {
			mainFrame.getTree().expandRow(i);
		}
	}
	
	public static MainController getInstance() {
		if (instance == null) {
			instance = new MainController();
		}
		return instance;
	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public String getLocation() {
		return location;
	}

	public ArrayList<InfResource> getInfResources() {
		return infResources;
	}

	public StateManager getStateManager() {
		return stateManager;
	}
}
