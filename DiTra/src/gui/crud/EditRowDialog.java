package gui.crud;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import actions.crud.SaveEditRowAction;
import actions.crud.TmpBtnAction;
import gui.dataStorage.DataStorageView;
import gui.dataStorage.DataTable;
import gui.dataStorage.EntityTab;
import model.Entity;


public class EditRowDialog extends JDialog {
	private static EditRowDialog instance;

	private Entity entity; // Entity po kojem se pretrazuje
	private EntityTab entityTab; // Tab u koji se refreashuje sa tabelom
	private JTextField[] textFields; // U njima se nalaze inf po kojima treba
	// vrsiti pretragu, redom kako su
	// nabrojani atributi u entity-u
	private JLabel[] labels; //U njima se nalaze nazivi atributa koje treba dodati

	private JPanel panel = new JPanel(new GridBagLayout());

	private JButton editBtn = new JButton("Edit");

	public EditRowDialog() {

		setSize(635, 500);
		this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width) / 2 - getWidth() / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height) / 2 - getHeight() / 2);
		setLayout(new BorderLayout());
		setModal(true);

		System.out.println("Init Edit");

		// Resetovanje lavele za greske
		GridBagConstraints constraints = new GridBagConstraints();

		// Koji entity?
		entityTab = (EntityTab) DataStorageView.getInstance().getTabPane().getSelectedComponent();

		// In case no entity is selected, show message
		if (entityTab == null) {
			constraints = getConstraints(0, 0);
			panel.add(new JLabel("No entity selected!"), constraints);
			JButton tmpBtn = new JButton("Close");
			tmpBtn.addActionListener(new TmpBtnAction(this));
			constraints = getConstraints(0, 1);
			panel.add(tmpBtn);
			this.add(panel);
			return;
		}

		entity = entityTab.getEntity();
		editBtn.setPreferredSize(new Dimension(100, 30));
		editBtn.addActionListener(new SaveEditRowAction());

		// Koja tabela je selektovana?
		DataTable selected_table = entityTab.getTable();
		int count = selected_table.getSelectedRowCount();

		// If no line is selected
		if (count == 0) {
			constraints = getConstraints(0, 0);
			panel.add(new JLabel("No line selected!"), constraints);
			JButton tmpBtn = new JButton("Close");
			tmpBtn.addActionListener(new TmpBtnAction(this));
			constraints = getConstraints(0, 1);
			panel.add(tmpBtn);
			this.add(panel);
			return;
		}
		if (count > 1) {
			constraints = getConstraints(0, 0);
			panel.add(new JLabel("Multiple selected!"), constraints);
			JButton tmpBtn = new JButton("Close");
			tmpBtn.addActionListener(new TmpBtnAction(this));
			constraints = getConstraints(0, 1);
			panel.add(tmpBtn);
			this.add(panel);
			System.out.println("Pritisnuto vise redova, ne moze");
			return;
		}

		int row = selected_table.getSelectedRow();
		System.out.println("dosao");

		// Forma za detalje
		textFields = new JTextField[entity.getAttributes().size()];
		labels = new JLabel[entity.getAttributes().size()];

		this.setTitle("Edit line of record " + entity.getName());

		for (int i = 0; i < entity.getAttributes().size(); i++) {
			constraints = getConstraints(0, i);
			labels[i] = new JLabel(entity.getAttributes().get(i).toString() + ": ");
			panel.add(labels[i], constraints);

			JTextField tf = new JTextField();

			if(selected_table.getValueAt(row, i) != null) {
				tf.setText(selected_table.getValueAt(row, i).toString());
			}


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

		constraints = getConstraints(0, entity.getAttributes().size());
		panel.add(editBtn, constraints);

		this.add(panel);
	}

	protected GridBagConstraints getConstraints(int x, int y) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = x;
		c.gridy = y;
		c.insets = new Insets(10, 10, 0, 0);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		return c;
	}

	public static EditRowDialog getInstance() {
		instance = new EditRowDialog();
		return instance;
	}

	public static EditRowDialog getCurrentInstance() {
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
