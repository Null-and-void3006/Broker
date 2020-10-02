package gui.dataStorage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Entity;
import observer.Observer;

public class EntityTab extends JPanel implements Observer{
	private Entity e;
	private DataTable table;
	
	public EntityTab(Entity e) {
		super();
		this.e = e;
		table = new DataTable();
	}

	public Entity getEntity() {
		return e;
	}

	public DataTable getTable() {
		return table;
	}

	@Override
	public void update() {
				
	}
	
	public void refreshTable(DataTable tb) {
		if (tb == null) return;
		
		this.removeAll();
		table = tb;
		
		int x = ((Rectangle)this.getBounds()).x + 10;
		int y = ((Rectangle)this.getBounds()).y + 10;
		int w = ((Rectangle)this.getBounds()).width - 10;
		int h = ((Rectangle)this.getBounds()).height - 10;
		table.setBounds(x, y, w, h);
		JScrollPane sp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		int height =  (int) (2.03f*(Toolkit.getDefaultToolkit().getScreenSize().height)/5);
		sp.setPreferredSize(new Dimension((int) sp.getPreferredSize().getWidth(), height));
		
		this.add(sp, BorderLayout.EAST);
	}
	
	public static Dimension getPrefferedDimension() {
		JPanel jp = new JPanel();
		Dimension d = jp.getPreferredSize();
		jp = null;
		return d;
	}
}
