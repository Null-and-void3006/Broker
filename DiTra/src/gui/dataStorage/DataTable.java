package gui.dataStorage;

import java.awt.Color;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;


public class DataTable extends JTable implements TableModel{
	
	public DataTable() {
		super();
		int width = DataStorageView.getInstance().getTabPane().getSize().width - 30;
		int height = DataStorageView.getInstance().getTabPane().getSize().height - 30;
		this.setPreferredScrollableViewportSize(new Dimension(width, height));
		this.setRowHeight(40);
		this.setFillsViewportHeight(true);
	}
	
	public DataTable(Object[][] a, Object[]b) {
		super(a, b);
		int width = DataStorageView.getInstance().getTabPane().getSize().width - 30;
		int height = DataStorageView.getInstance().getTabPane().getSize().height - 30;
		this.setPreferredScrollableViewportSize(new Dimension(width, height));
		this.setRowHeight(40);
		this.setFillsViewportHeight(true);

	}
	
	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		this.addTableModelListener(arg0);
		
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		this.removeTableModelListener(arg0);
		
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
	}
	
	public Component prepareRenderer(TableCellRenderer r, int row, int column) {
		Component c = super.prepareRenderer(r, row, column);
		if(row%2 == 0) c.setBackground(Color.WHITE);
		else c.setBackground(Color.LIGHT_GRAY);
		
		if(isCellSelected(row, column))
			c.setBackground(new Color(232, 237, 242));
		
		return c;
	}

}
