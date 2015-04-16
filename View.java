import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class View extends JFrame {
private EvoPanel evoPanel;
	
	public View() {
		
		// exit on close
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// set the title default for this frame
		this.setTitle("Application");
		
		// set size defaults for this frame
		setPreferredSize(new Dimension(500, 600));
		setMinimumSize(new Dimension(500, 600));
		setMaximumSize(new Dimension(800, 1000));
		
		// set color defaults for this frame
		setBackground(Color.GRAY);
		
		// Create the Primary Panel and add it to the frame
		evoPanel = new EvoPanel();
		add(evoPanel);
		
	} // end View()
}
