package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainMenuBar extends JMenuBar {
	private static MainMenuBar instance = null;

	private JMenu fileMenu;
	private JMenu aboutMenu;
	private JMenu helpMenu;

	private JMenuItem openResourceItem;
	private JMenuItem aboutItem;
	private JMenuItem helpItem;

	private MainMenuBar() {
		setBackground(Color.white);

		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);

		aboutMenu = new JMenu("About");
		aboutMenu.setMnemonic(KeyEvent.VK_A);

		helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);

		initFileMenu();
		initAboutMenu();
		initHelpMenu();

		add(fileMenu);
		add(aboutMenu);
		add(helpMenu);
	}

	private void initFileMenu() {
		openResourceItem = new JMenuItem("  Open Resource...");
		openResourceItem.setPreferredSize(new Dimension(200, 30));
		openResourceItem.setIcon(getScaledImage("mainframe_icons/addInfResource.png"));
		fileMenu.add(openResourceItem);
	}

	private void initAboutMenu() {
		aboutItem = new JMenuItem("  Show About");
		aboutItem.setPreferredSize(new Dimension(160, 30));
		aboutItem.setIcon(getScaledImage("mainframe_icons/about.png"));
		aboutMenu.add(aboutItem);
	}

	private void initHelpMenu() {
		helpItem = new JMenuItem("  Show Help");
		helpItem.setPreferredSize(new Dimension(160, 30));
		helpItem.setIcon(getScaledImage("mainframe_icons/help.png"));
		helpMenu.add(helpItem);
	}

	private ImageIcon getScaledImage(String imageSource) {
		ImageIcon icon = new ImageIcon(imageSource);
		Image image = icon.getImage();
		return new ImageIcon(image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
	}

	public static MainMenuBar getInstance() {
		if (instance == null) {
			instance = new MainMenuBar();
		}
		return instance;
	}

	public JMenu getFileMenu() {
		return fileMenu;
	}

	public JMenu getAboutMenu() {
		return aboutMenu;
	}

	public JMenu getHelpMenu() {
		return helpMenu;
	}

	public JMenuItem getAboutItem() {
		return aboutItem;
	}

	public JMenuItem getHelpItem() {
		return helpItem;
	}

	public JMenuItem getOpenResourceItem() {
		return openResourceItem;
	}
}
