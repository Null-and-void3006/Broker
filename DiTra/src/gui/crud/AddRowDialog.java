package gui.crud;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import actions.crud.SaveAddRowAction;
import actions.crud.TmpBtnAction;
import actions.dataStorage.FetchNextBlockAction;
import enums.AtributeType;
import gui.MainFrame;
import gui.dataStorage.DataStorageView;
import gui.dataStorage.DataTable;
import gui.dataStorage.EntityTab;
import model.Entity;
import model.database.Database;

public class AddRowDialog extends JDialog{
	private static AddRowDialog instance;


	private Entity entity; // Entity po kojem se pretrazuje
	private EntityTab entityTab; // Tab u koji se refreashuje sa tabelom
	private JTextField[] textFields; // U njima se nalaze inf po kojima treba
	// vrsiti pretragu, redom kako su
	// nabrojani atributi u entity-u
	private JLabel[] labels; //U njima se nalaze nazivi atributa koje treba dodati


	private JPanel panel = new JPanel(new GridBagLayout());

	private JButton addBtn = new JButton("Add");

	public AddRowDialog()
	{

		setSize(635, 500);
		this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2,
				(Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
		setLayout(new BorderLayout());
		setModal(true);

		System.out.println("Init ADD");
		// Resetovanje lavele za greske


		GridBagConstraints constraints = new GridBagConstraints();
		// Koji entity?
		EntityTab selected_tab = (EntityTab) DataStorageView.getInstance().getTabPane().getSelectedComponent();
		//In case no entity is selected, show message
		if (selected_tab == null)
		{
			constraints = getConstraints(0, 0);
			panel.add(new JLabel("No entity selected!"), constraints);
			JButton tmpBtn = new JButton("Close");
			tmpBtn.addActionListener(new TmpBtnAction(this));
			constraints = getConstraints(0, 1);
			panel.add(tmpBtn);
			this.add(panel);
			return;
		}
		entity = selected_tab.getEntity();

		addBtn.setPreferredSize(new Dimension(100, 30));

		this.setTitle(entity.getName());

		// if(entity == null || entity.getType() != EntityType.serial ||
		// entity.getType() != EntityType.sqlTable) return; //radimo samo za
		// serijske

		System.out.println(entity.getName());
		System.out.println("dosao");


		// Forma za dodavanje
		textFields = new JTextField[entity.getAttributes().size()];
		labels = new JLabel[entity.getAttributes().size()];

		this.setTitle("Add new record to " + entity.getName());

		for (int i = 0; i < entity.getAttributes().size(); i++) {
			constraints = getConstraints(0, i);
			labels[i] = new JLabel(entity.getAttributes().get(i).toString() + ": ");
			panel.add(labels[i], constraints);

			JTextField tf = new JTextField();
			textFields[i] = tf;
			tf.setMinimumSize(new Dimension(200, 30));
			tf.setColumns(5);
			if (i % 2 == 0) {
				constraints = getConstraints(1, i);
				panel.add(tf, constraints);
				constraints = getConstraints(1, i);
				panel.add(new JLabel("     "), constraints);
			} else {
				constraints = getConstraints(1, i);
				tf.setColumns(5);
				panel.add(tf, constraints);
			}
		}






		addBtn.addActionListener(new SaveAddRowAction());

		constraints = getConstraints(0, entity.getAttributes().size());
		panel.add(addBtn, constraints);


		this.add(panel);
	}

	protected GridBagConstraints getConstraints(int x, int y){
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = x;
		c.gridy = y;
		c.insets = new Insets(10, 10, 0, 0);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		return c;
	}

	public static AddRowDialog getInstance()
	{
		instance = new AddRowDialog();
		return instance;
	}

	public static AddRowDialog getCurrentInstance() {
		return instance;
	}

	public Entity getEntity() {
		return entity;
	}

	public EntityTab getEntityTab() {
		return entityTab;
	}

	public JTextField[] getTextFields() {
		return textFields;
	}

	public JLabel[] getLabels() {
		return labels;
	}

}
