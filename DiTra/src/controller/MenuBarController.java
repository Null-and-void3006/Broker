package controller;

import actions.AboutAction;
import actions.CloseAction;
import actions.HelpAction;
import actions.connection.OpenResourceAction;
//import actions.metaSchemaEditor.PopupEditAction;
import gui.*;

public class MenuBarController {

	private static MenuBarController instance = null;

	private MainMenuBar menuBar;

	private MenuBarController() {
		this.menuBar = MainFrame.getInstance().getMainMenuBar();
		setListeners();
	}

	private void setListeners() {
		setMenuBarLIsteners();
		setAboutAndHelpFrameListeners();
	}

	private void setMenuBarLIsteners() {
		// About menu
		menuBar.getAboutItem().addActionListener(new AboutAction());

		// Help menu
		menuBar.getHelpItem().addActionListener(new HelpAction());

		// Open Resource menu
		menuBar.getOpenResourceItem().addActionListener(new OpenResourceAction());
	}

	private void setAboutAndHelpFrameListeners() {
		// About frame
		AboutFrame.getInstance().getCancelButton().addActionListener(new CloseAction(AboutFrame.getInstance()));

		// Help frame
		HelpFrame.getInstance().getCancelButton().addActionListener(new CloseAction(HelpFrame.getInstance()));
	}

	public static MenuBarController getInstance() {
		if (instance == null) {
			instance = new MenuBarController();
		}
		return instance;
	}
}
