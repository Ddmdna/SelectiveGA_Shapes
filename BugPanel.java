import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class BugPanel extends JPanel {
	Model model;
	public BugPanel(Bug bug, JButton l, JButton r, JButton d){
		// initialize the model
		model = new Model(bug);
		
		// Set Layout to BoxLayout Y_AXIS
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//modelPanel
		JPanel modelPanel = new JPanel();
		modelPanel.add(model.getDrawPanel());
		
		// detailLable
		JLabel detailLabel = new JLabel("G: " + Integer.toString(bug.getGeneration()));
		
		//controlPanel
		JPanel controlPanel = new JPanel();
		controlPanel.add(l);
		controlPanel.add(d);
		controlPanel.add(r);
		//controlPanel.setPreferredSize(new Dimension(100, 25));
		
		// Add modelPanel and controlPanel to BugPanel
		add(modelPanel);
		add(detailLabel);
		add(controlPanel);
	}
}
