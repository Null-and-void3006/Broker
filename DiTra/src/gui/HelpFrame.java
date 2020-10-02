package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HelpFrame extends ClosableFrame {
	private static HelpFrame instance = null;	

    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JScrollPane scrollPane;
    private JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private String description = "DiTra je service-based, dinamican sistem napravljen radi olaksavanja rukovanja informacionim resursima za visokoskolske ustanove koji se nalaze u bazama podataka."
    		+ "\n\nDiTra omogucava: \n\n1. Pristup informacionim resursima (datotekama ili skladistima) \n2. Manipulaciju informacionim resursima."
    		+"\n\nAko zelite da vam se prikaze tabela sa atributima onda drzite SHIFT i kliknete na zeljeni entitet u stablu (nema na cemu)!" ;
	
	private JButton cancelButton = new JButton("Cancel");
	
	public HelpFrame(){
		this.setTitle("Help");
		setSize(new Dimension(400, 400));
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, 
			    (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
		initCenter();
		initBottom();
		
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
       
        add(mainPanel);
	}
	
	private void initCenter(){       
		JTextArea descriptionArea = new JTextArea(description);
		descriptionArea.setEditable(false);
		descriptionArea.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		descriptionArea.setLineWrap(true);
		descriptionArea.setWrapStyleWord(true);
		
		scrollPane = new JScrollPane(descriptionArea);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		scrollPane.setMaximumSize(new Dimension(2000, 300));
	}
	
	private void initBottom(){
		bottomPanel.add(cancelButton);
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}
	
	public static HelpFrame getInstance() {
		if (instance == null) {
			instance = new HelpFrame();
		}
		return instance;
	}
}
