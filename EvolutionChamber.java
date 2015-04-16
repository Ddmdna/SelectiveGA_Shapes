/*
 * This will be a container for all of the bugs being mated together
 * The maximum capacity will be 30
 * Mating will only occur when the population is 20 or less
 * 
 * Can access each bug object
 * Can change the location of each bug object
 * Perform mating on pairs of bugs
 * ie: 0&1, 2&3, 4&5
 */
public class EvolutionChamber {
	final int MAX_CAPACITY = 30;
	final int MAX_MATING = 20;
	private int numberOfBugs;
	private Bug[] bugs;
	//Going to need an array for the shapes
	//Going to need an array for buttons to control deletion and order
	// of shapes and bugs
	
	public EvolutionChamber(){
		// Should start with a set number of bugs. 10?
		int initialNumberOfBugs = 10;
		
		// Create the bug array
		bugs = new Bug[10];
		
		// Initialize each bug in the array
		for(int i=0; i<initialNumberOfBugs; i++){
			bugs[i] = new Bug();
		}
		
		// Keep track of the number of bugs
		numberOfBugs = initialNumberOfBugs;
	} // EvolutionChamber()
	
	// Cut down to 20 bugs
	public void removeBugs(){
		// used to store old bugs
		Bug[] bugsCopy;
		
		// Check to see if over limit
		if(numberOfBugs>MAX_MATING){
			//create the copy
			bugsCopy = bugs;
			
			// Create new bugs array with limit at MAX_MATING
			bugs = new Bug[MAX_MATING];
			
			// Keep track of the number of bugs
			numberOfBugs = MAX_MATING;
			
			// get bugs from the copy up to the limit
			for(int i=0; i<MAX_MATING; i++){
				bugs[i] = bugsCopy[i];
			}
		} // end if
		
	} // removeBugs()
	
	// Heres the reproduction process
	public void simulateReproduction(){
		// used to store old bugs
		Bug[] bugsCopy;
		
		// Used to keep track of children
		int currentNumberOfChildren;
		
		// Check if numberOfBugs > 20
		if(numberOfBugs <= 20){
			// copy the old bugs
			bugsCopy = bugs;
			
			// set the initial currentNumberOfChildren to 0
			currentNumberOfChildren = 0;
			
			// Check if numberOfBugs is even
			if( (numberOfBugs % 2) == 0 ){
				// Create the new bug array and account for children
				bugs = new Bug[ ( numberOfBugs + (numberOfBugs/2) ) ];
			}
			else{
					// Create the new bug array and account for children
					bugs = new Bug[ ( numberOfBugs + (numberOfBugs/2) + 1 ) ];
			}
		
			// Populate the new bugs array with old bugs and children
			for(int i=0; i<numberOfBugs; i++){
				//copy the parent
				bugs[i + currentNumberOfChildren] = bugsCopy[i];
				// After copying two parents and at odd numbers of i
				if( (i>0) && (i%2 != 0)){
					// Create child from parent
					bugs[i + currentNumberOfChildren + 1] = bugs[i].mate(bugs[i + currentNumberOfChildren]);
					// keep track of the number of children
					currentNumberOfChildren++;
				}
			}
			// keep track of the number of bugs
			numberOfBugs = (numberOfBugs + currentNumberOfChildren);
		} // end if(numberOfBugs <= 20)
		
	} // simulateReproduction()
	
	public void swapBugs(int a, int b){
		// bug to be used in copying
		Bug copy;
		
		// Make sure a and b are valid locations
		if(a < 0) a = 0;
		if(b < 0) b = 0;
		if(a >= numberOfBugs) a = (numberOfBugs - 1);
		if(b >= numberOfBugs) b = (numberOfBugs - 1);
		
		// Copy action
		copy = bugs[a];
		bugs[a] = bugs[b];
		bugs[b] = copy;
	} // swapBugs(int a, int b)
	
	public void deleteBug(int b){
		// used to store old bugs
		Bug[] bugsCopy;
		
		// copy the bugs
		bugsCopy = bugs;
		
		// Create a new bugs array thats one less than before
		bugs = new Bug[numberOfBugs - 1];
		
		int n = 0; // Keeps track of the deleted bug
		// Copy all bugs but the deleted bug
		for(int i=0; i<numberOfBugs; i++){
			if(i!=b){
				bugs[n] = bugsCopy[i];
				n++;
			}
		}
		
		// Keep track of the expected new numberOfBugs
		numberOfBugs--;
				
		// Make sure numberOfBugs is valid
		if(numberOfBugs<0) numberOfBugs = 0;
		
	} // deleteBug(int b)
	
	// get the numberOfBugs
	public int getNumberOfBugs(){
		return numberOfBugs;
	}
	
	// get bug at location
	public Bug getBug(int i){
		//make sure i is valid
		if(i < 0) i = 0;
		if(i >= numberOfBugs) i = (numberOfBugs - 1);
		
		return bugs[i];
	}
} // class EvolutionChamber
