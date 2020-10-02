package controller;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import gui.MainFrame;
import model.Workspace;

public class TreeController {
	private static TreeController instance = null;	
	
	private Workspace workspace = new Workspace();
	private JTree tree;
	
	private TreeController(){
		this.tree = MainFrame.getInstance().getTree();
		setListeners();
	}
	
	private void setListeners(){
	//	tree.getCellEditor().addCellEditorListener(new NameChangedListener());
	}
	
	public static TreeController getInstance() {
		if (instance == null) {
			instance = new TreeController();
		}
		return instance;
	}
	
	public DefaultMutableTreeNode getWorkspace() {
		return workspace;
	}
}
