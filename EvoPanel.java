import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


public class EvoPanel extends JPanel {
	// Needs to contain an EvolutionChamber object
	EvolutionChamber evoChamber;
	
	// needs to contain buttons to control the EvolutionChamber object
	// removeBugs button
	private JButton removeBugsButton;
	private RemoveBugsButtonListener removeBugsButtonListener;
	
	// simulateReproduction button
	private JButton simulateReproductionButton;
	private SimulateReproductionButtonListener simulateReproductionButtonListener;
	
	// Needs to contain an array of BugPanel JPanels
	BugPanel[] bugPanels;
	// A panel to hold the bugPanels
	JPanel centerPanel;

	// swapBugs button    for each BugPanel
	
	// Needs to be in the correct layout
	
	public EvoPanel(){
		// set Layout to BorderLayout
		setLayout(new BorderLayout());
		
		// Initialize the EvolutionChamber object
		evoChamber = new EvolutionChamber();
		
		// Create removeBugsButton and give it the removeBugsButtonListener
		removeBugsButton = new JButton("Remove Extra");
		removeBugsButtonListener = new RemoveBugsButtonListener();
		removeBugsButton.addActionListener(removeBugsButtonListener);
		
		// Create simulateReproductionButton and give it the simulateReproductionButtonListener
		simulateReproductionButton = new JButton("Simulate Reproduction");
		simulateReproductionButtonListener = new SimulateReproductionButtonListener();
		simulateReproductionButton.addActionListener(simulateReproductionButtonListener);
		
		
		// Add each button to the North part of the Border
		JPanel evoButtonsPanel = new JPanel();
		evoButtonsPanel.add(removeBugsButton);
		evoButtonsPanel.add(simulateReproductionButton);
		add(evoButtonsPanel, BorderLayout.NORTH);
		
		// Create listeners for swapLeftButton, swapRightButton and deleteButton
		
		// Create the BugPanels
		bugPanels = new BugPanel[evoChamber.getNumberOfBugs()];
		setBugPanels();
		
		// Add the bugPanels to the center panel then add it to the EvoPanel
		centerPanel = new JPanel();
		addBugPanelsToCenterPanel();
		add(centerPanel, BorderLayout.CENTER);
	}
	
	// removeBugsButton listener
	public class RemoveBugsButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent event) {
			evoChamber.removeBugs();
			renew();
			
		}
	}
	
	// simulateReproductionButton listener
	public class SimulateReproductionButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			evoChamber.simulateReproduction();
			renew();
		}
	}
	
	// SwapLeftButton
	public class SwapLeftButton extends JButton{
		private int spot; // location
		public SwapLeftButton(int location){
			super("<");
			this.spot = location;
			setMargin(new Insets(0, 0, 0, 0));
			setFont(new Font("Sans Serif", Font.PLAIN, 15));
			setPreferredSize(new Dimension(33, 25));
		}
		public int getSpot(){
			return spot;
		}
	}
	
	// SwapRightButton
	public class SwapRightButton extends JButton{
		private int spot; // location
		public SwapRightButton(int location){
			super(">");
			this.spot = location;
			setMargin(new Insets(0, 0, 0, 0));
			setFont(new Font("Sans Serif", Font.PLAIN, 15));
			setPreferredSize(new Dimension(33, 25));
		}
		public int getSpot(){
			return spot;
		}
	}
	
	// SwapRightButton
	public class DeleteButton extends JButton{
		private int spot; // location
		public DeleteButton(int location){
			super("D");
			this.spot = location;
			setMargin(new Insets(0, 0, 0, 0));
			setFont(new Font("Sans Serif", Font.PLAIN, 15));
			setPreferredSize(new Dimension(33, 25));
		}
		public int getSpot(){
			return spot;
		}
	}	

	// SwapLeftButton listener
	public class SwapLeftButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			evoChamber.swapBugs( ((SwapLeftButton) event.getSource()).getSpot(), ((SwapLeftButton) event.getSource()).getSpot()-1);
			renew();
		}
	}
		
	// SwapRightButton listener
	public class SwapRightButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			evoChamber.swapBugs( ((SwapRightButton) event.getSource()).getSpot(), ((SwapRightButton) event.getSource()).getSpot()+1);
			renew();
		}
	}
		
	// DeleteButton listener
	public class DeleteButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			evoChamber.deleteBug( ((DeleteButton) event.getSource()).getSpot());
			renew();
		}
	}
	
	// Set each bug panel for each bug in evoChamber
	private void setBugPanels(){
		// Buttons
		SwapLeftButton swapLeftButton;
		SwapRightButton swapRightButton;
		DeleteButton deleteButton;
		
		// Button listeners
		SwapLeftButtonListener swapLeftButtonListener = new SwapLeftButtonListener();
		SwapRightButtonListener swapRightButtonListener= new SwapRightButtonListener();
		DeleteButtonListener deleteButtonListener = new DeleteButtonListener();
		
		for(int i=0; i<evoChamber.getNumberOfBugs(); i++){
			// The bug panel needs a Bug object 1 to 1
			// Bug panel needs a Swap Forward, swap Backward and a delete button
			swapLeftButton = new SwapLeftButton(i);
			swapLeftButton.addActionListener(swapLeftButtonListener);
			swapRightButton = new SwapRightButton(i);
			swapRightButton.addActionListener(swapRightButtonListener);
			deleteButton = new DeleteButton(i);
			deleteButton.addActionListener(deleteButtonListener);
			
			bugPanels[i] = new BugPanel(evoChamber.getBug(i), swapLeftButton, swapRightButton, deleteButton);
		}
	}
	
	// Add bugPanels to the center panel
	private void addBugPanelsToCenterPanel(){
		for(int i=0; i<evoChamber.getNumberOfBugs(); i++){
			centerPanel.add(bugPanels[i]);
		}
	}
	
	// Renew 
	private void renew(){
		// Renew the BugPanels
		bugPanels = new BugPanel[evoChamber.getNumberOfBugs()];
		setBugPanels();
		remove(centerPanel);		
		// Renew the center panel
		centerPanel = new JPanel();
		addBugPanelsToCenterPanel();
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.revalidate();
	}
}

