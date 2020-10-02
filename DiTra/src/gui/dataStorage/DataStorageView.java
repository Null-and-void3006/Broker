package gui.dataStorage;

import net.miginfocom.swing.MigLayout;
import state.State;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import actions.DataViewPaneActionListener;
import actions.crud.AddRowAction;
import actions.crud.DeleteRowAction;
import actions.crud.EditRowAction;
import actions.dataStorage.FetchNextBlockAction;
import actions.dataStorage.MakeTreeAction;
import controller.MainController;
import model.Entity;

public class DataStorageView extends JPanel{
	private static DataStorageView instance = null;
	
	private JTabbedPane tabPane;
	private JTabbedPane relationTabPane;
	private JPanel statesPane;
	private JPanel panParams;
	private JPanel panToolbar;
	
	private ArrayList<EntityTab> entityTabs;
	
	private JTextField txtBlockSize;
	private JTextField txtFileSize;
	private JTextField txtRecordSize;
	private JTextField txtRecordNum;
	private JTextField txtBlockNum;
	private JTextField txtAttributeCount;
	
	private DataStorageView(){
		super();
		this.setLayout(new MigLayout());
		initPanes();
	}
	
	public void initPanes(){
		entityTabs = new ArrayList<>();
		
		int width = (int) (3.55f*(Toolkit.getDefaultToolkit().getScreenSize().width)/5);
		int height =  (int) (3.55f*(Toolkit.getDefaultToolkit().getScreenSize().height)/5);
		
		// Toolbars
		initPanParams();
		updateParamsLabels();
		initPanToolbar();
		
		// Za entity
		tabPane = new JTabbedPane();
		tabPane.setBackground(new Color(224, 224, 209));
		tabPane.setBorder(new MatteBorder(0, 2, 0, 2, Color.gray));
		tabPane.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tabPane.setPreferredSize(new Dimension(width, height));
		
		 
			// Za entitije u relaciji
			relationTabPane = new JTabbedPane();
			relationTabPane.setBackground(new Color(224, 224, 209));
			relationTabPane.setBorder(new MatteBorder(0, 2, 0, 2, Color.gray));
			relationTabPane.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			relationTabPane.setPreferredSize(new Dimension(width, height));
		
			
			
		// Za entitije u relaciji
		statesPane = new JPanel();
		statesPane.setLayout(new MigLayout());
		statesPane.setBackground(new Color(224, 224, 209));
		statesPane.setBorder(new MatteBorder(0, 2, 0, 2, Color.gray));
		statesPane.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		statesPane.setPreferredSize(new Dimension(width, height));

		// Dodavanje Toolbara i  Panea
		this.add(panParams, "wrap");
		this.add(panToolbar, "wrap");
		this.add(tabPane, "wrap");
		//this.add(statesPane, "wrap");
		this.add(relationTabPane, "wrap");
	}
	
	private void initPanParams(){
    	panParams=new JPanel(new MigLayout());
		
		//velicina bloka - moze da se menja
		panParams.add(new JLabel("f (block factor):"));
		txtBlockSize=new JTextField();
		txtBlockSize.setColumns(5);		
		panParams.add(txtBlockSize);
		JButton btnChangeBS=new JButton("Change f");
		btnChangeBS.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				//TODO: Update block size
				}
			}
		);
		panParams.add(btnChangeBS);
		
		// Button za new block
		JButton btnFetch=new JButton("Fetch next block");
		btnFetch.addActionListener(new FetchNextBlockAction());
		panParams.add(btnFetch);	
		
		// Count Atttribute
		panParams.add(new JLabel(""));
		panParams.add(new JLabel(""));
		panParams.add(new JLabel("Count Attribute:"));
		txtAttributeCount=new JTextField();
		txtAttributeCount.setColumns(7);
		txtAttributeCount.setEnabled(false);		
		panParams.add(txtAttributeCount, "wrap");
		
		//velicina datoteke
		panParams.add(new JLabel("File size:"));
		txtFileSize=new JTextField();
		txtFileSize.setColumns(7);
		txtFileSize.setEnabled(false);		
		panParams.add(txtFileSize);
		
		//velicina linije u datoteci  
		panParams.add(new JLabel("Record size(B):"));
		txtRecordSize=new JTextField();
		txtRecordSize.setColumns(7);
		txtRecordSize.setEnabled(false);		
		panParams.add(txtRecordSize);
		
		//broj recorda u datoteci  
		panParams.add(new JLabel("Record num:"));
		txtRecordNum=new JTextField();
		txtRecordNum.setColumns(7);
		txtRecordNum.setEnabled(false);		
		panParams.add(txtRecordNum);
		
		//broj blokova u datoteci  
		panParams.add(new JLabel("Block num:"));
		txtBlockNum=new JTextField();
		txtBlockNum.setColumns(7);
		txtBlockNum.setEnabled(false);
		panParams.add(txtBlockNum);
		
		panParams.setBackground(new Color(153,204,255));
    }
	
	private Entity getSelectedEntity(){
		// TabPane?
		if (getTabPane() == null) return null;
		
		// Da li je selektovan neki tabPane?
		EntityTab selectedTab = (EntityTab)getTabPane().getSelectedComponent();	
		if (selectedTab == null) return null;
		
		// Koji Entity je selektovan?
		return selectedTab.getEntity();
	}
	

	public void updateParamsLabels() {
			txtBlockSize.setText("20");
			txtFileSize.setText("0");
			txtRecordSize.setText("0");
			txtRecordNum.setText("0");
			txtBlockNum.setText("0");
			txtAttributeCount.setText("0");
		
	}
	
	private void initPanToolbar(){
    	panToolbar=new JPanel(new MigLayout());
		
    	// ADD
		JButton btnAdd=new JButton("Add");
		/*btnAdd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				MainController.getInstance().getStateManager().setAddState();
			}
		});*/
		btnAdd.addActionListener(new AddRowAction());
		panToolbar.add(btnAdd);		
		
    	// EDIT
		JButton btnEdit=new JButton("Edit");
		btnEdit.addActionListener(new EditRowAction());
		panToolbar.add(btnEdit);	
		
    	// DELETE
		JButton btnDelete=new JButton("Delete");
		btnDelete.addActionListener(new DeleteRowAction());
		panToolbar.add(btnDelete);	
		
		/*
    	// RELATIONS
		JButton btnShowRelations=new JButton("Show Relations");
		btnShowRelations.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				MainController.getInstance().getStateManager().setShowRelationsState();
			}
		});
		panToolbar.add(btnShowRelations);	 */
		
		/*
		// Submit
		JButton btnSubmit = new JButton("Submit state");
		btnSubmit.setBackground(Color.GRAY);
		btnSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				State currState = MainController.getInstance().getStateManager().getCurrentState();
				if (currState != null) {
					currState.doAction();
					statesPane.repaint();
					MainController.getInstance().getMainFrame().validate();
				}
			}
		});
		panToolbar.add(new JLabel("     "));
		panToolbar.add(btnSubmit);
		
		*/
		
		
    }
	
	public EntityTab getEntityTab(Entity entity) {
		for (EntityTab eTab: entityTabs){
			if (entity == eTab.getEntity()){
				return eTab;
			}
		}
		
		EntityTab eTab = new EntityTab(entity);
		entityTabs.add(eTab);
		return eTab;
	}
	
	//postavlja dugme za zatvaranje na sve tabove 
	public void refreshTabPane() {
		for(int i = 0; i < tabPane.getTabCount(); i++) {
			tabPane.setTabComponentAt(i, new ButtonTabComponent(tabPane));
		}
		
		
		//Delete last shown relation
		if (tabPane.getTabCount() == 0)
			refreshRelationTabs(null);
			
	}
	
	
	public void refreshTab(EntityTab eTab, DataTable table) {
		// Postavljanje promenjene table u tab; Postavlja se SAMO ona u tabu
		if (eTab !=null) {
			System.out.println("USO");
			
			eTab.refreshTable(table);
			tabPane.repaint();
		}
		
		// Dodati/promeniti i tabove za entitete koji su u relaciji sa njim
		refreshRelationTabs(eTab);
	}
	
	
	
	public void refreshRelationTabs(EntityTab mainETab) {
        relationTabPane.removeAll();
        
        
        if (mainETab == null){
        	return;
        }
        
    	int relNum = mainETab.getEntity().getRelations().size();    
    	for(int relIndx = 0; relIndx < relNum; relIndx++) { //prolazi kroz listu relacija i pravi EntityTabove za svaki refferingEntity
    		EntityTab eTab = new EntityTab(mainETab.getEntity().getRelations().get(relIndx).getToEntity());
    		DataTable table = null;
			try {
				table = FetchNextBlockAction.getTableWithData(eTab.getEntity());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		eTab.refreshTable(table);
			relationTabPane.addTab(eTab.getEntity().getName(), eTab);
    	}
	}
	
	
	public void clearRelationTabPane()
	{
		this.relationTabPane.removeAll();
	}

	public static DataStorageView getInstance() {
		if (instance == null) {
			instance = new DataStorageView();
		}
		return instance;
	}

	public JTabbedPane getTabPane() {
		return tabPane;
	}

	public JTabbedPane getRelationTabPane() {
		return relationTabPane;
	}

	public ArrayList<EntityTab> getEntityTabs() {
		return entityTabs;
	}

	public JTextField getTxtBlockSize() {
		return txtBlockSize;
	}

	public JTextField getTxtFileSize() {
		return txtFileSize;
	}

	public JTextField getTxtRecordSize() {
		return txtRecordSize;
	}

	public JTextField getTxtRecordNum() {
		return txtRecordNum;
	}

	public JTextField getTxtBlockNum() {
		return txtBlockNum;
	}

	public JPanel getStatesPane() {
		return statesPane;
	}	
	
}
