package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import actions.TreeMouseListener;
import gui.dataStorage.DataStorageView;


public class MainFrame extends ClosableFrame {
	private static MainFrame instance = null;
	
	private DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
	private JTree tree;
	private JScrollPane treeScrollPane;
	
	private MainMenuBar mainMenuBar;
		
	private MainFrame() {
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setTitle("DiTra");
		setLayout(new BorderLayout());
		
		initMenuBar();
		initTree();
		initStatusBar();
		
		initDataView();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void initTree() {
		tree = new JTree(root);
		tree.setRootVisible(false);
		tree.addMouseListener(new TreeMouseListener());
		tree.setCellRenderer(new MainTreeRenderer());
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setToggleClickCount(2);
		
		treeScrollPane = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	            							   JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		treeScrollPane.setBorder(new EmptyBorder(5, 5, 0, 0));
		treeScrollPane.setPreferredSize(new Dimension(350, MAXIMIZED_VERT));
		treeScrollPane.setViewportView(tree);
		
		add(treeScrollPane, BorderLayout.WEST);
	}
	
	private void initDataView() {		 
		 add(DataStorageView.getInstance(), BorderLayout.CENTER);
	}
	
	private void initMenuBar() {
		this.mainMenuBar = MainMenuBar.getInstance();
		setJMenuBar(mainMenuBar);
	}
	
	private void initStatusBar() {
		JPanel statusPanel = new JPanel();
		statusPanel.setBackground(new Color(232, 237, 242));
		statusPanel.setBorder(null);
		add(statusPanel, BorderLayout.SOUTH);
		statusPanel.setBorder(new MatteBorder(2, 0, 0, 0, Color.GRAY));
		statusPanel.setPreferredSize(new Dimension(getWidth(), 35));
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		JLabel statusLabel = new JLabel(" Status bar");
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		statusPanel.add(statusLabel);
	}

	public void setWorkspace(DefaultMutableTreeNode workspace) {
		root.add(workspace);
	}
	
	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}
	
	public JTree getTree() {
		return tree;
	}

	public MainMenuBar getMainMenuBar(){
		return mainMenuBar;
	}
	
	public DefaultMutableTreeNode getRoot() {
		return root;
	}
}
