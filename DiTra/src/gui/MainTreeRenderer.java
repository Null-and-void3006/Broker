package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import model.Attribut;
import model.Entity;
import model.InfNode;
import model.InfResource;
import model.Workspace;

public class MainTreeRenderer extends DefaultTreeCellRenderer {
	  private JLabel label;

	  MainTreeRenderer() {
          label = new JLabel();
      }

      public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
                                                    boolean leaf, int row, boolean hasFocus) {
          label.setBackground(new Color(153, 204, 255));
          label.setOpaque(selected);
          DefaultTreeCellRenderer defaultRenderer = new DefaultTreeCellRenderer();
          
          InfNode node = null;

          if (value instanceof InfNode) {
        	  node = (InfNode) value;
          } else {
        	  return defaultRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
          }
          
          if (node instanceof Workspace) {
              label.setIcon(getScaledImage("mainframe_icons/workspace.png"));
          } else if (node instanceof InfResource){
        	  label.setIcon(getScaledImage("mainframe_icons/infResource.png"));
          } else if (node instanceof Entity){
        	  label.setIcon(getScaledImage("mainframe_icons/entity.png"));
          } else if (node instanceof Attribut){
        	  label.setIcon(getScaledImage("mainframe_icons/attribute.png"));
          } else {
        	  return defaultRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
          }
          label.setText(value.toString());
          
          return label;
      }
      
      private ImageIcon getScaledImage(String imageSource) {
  		ImageIcon icon = new ImageIcon(imageSource);
  		Image image = icon.getImage();
  		return new ImageIcon(image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH));
  	}
}
