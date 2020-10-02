package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AboutFrame extends ClosableFrame {
	private static AboutFrame instance = null;	
	
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private ImagePanel centerPanel = new ImagePanel();
    private JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	
	private JButton cancelButton = new JButton("Cancel");
	
	private AboutFrame(){
		this.setTitle("About The Team");
		setSize(new Dimension(650, 330));
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, 
			    (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
		
		bottomPanel.add(cancelButton);
		
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
       
        add(mainPanel);
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

    private class ImagePanel extends JPanel{
    	private BufferedImage image;
    		
	    private ImagePanel() {
	    
	    	this.setBackground(Color.WHITE);
	    	this.setBorder(BorderFactory.createRaisedBevelBorder());
	    	
	    	try {                
	    		image = ImageIO.read(new File("mainframe_icons/team.png"));
	    	} catch (IOException ex) {
	    		System.out.println("Problem with the team image!");
	    	}
	    }
	    
	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(image, 0, 0, this);           
	    }
    }
    	
	public static AboutFrame getInstance() {
		if (instance == null) {
			instance = new AboutFrame();
		}
		return instance;
	}
}
