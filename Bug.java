import java.util.Random;

/*
 * We will only be interested in the four different length values
 * The length will be represented by a 25 char length sections
 * of an 100 char length array
 * each 1 will represent 1cm in length.
 * 
 * The bug will be represented as a rectangle with length (0 - 25)cm sides
 * 
 */
public class Bug {
	final int MUTATION = 10; // 1% basically
	// Max amount bit string used in genetic algorithms for dna
	final int MAX = 1000;
	
	// Number of categories
	final int N = 10;
	
	// Length of each category
	final int SUB_MAX = (MAX/N);

	// Bit string used in genetic algorithms for dna
	private int[] dna;
	
	// Values derived from dna bit string
	private double[] values = new double[N];
	
	// Keep track of progress
	private int generation; //The current generation
	private int numOfChildren; // Adds to the generation number of children
	
	public Bug(){
		dna = new int[MAX];
		Random rand = new Random();
		
		// Generate a random bit string
		for(int i=0; i<MAX; i++){
			if( (rand.nextInt(2)) == 0)dna[i] = 0;
			else dna[i] = 1;
		}
		
		// Determine the values
		for(int i = 0; i<N; i++){
			values[i] = determineValue( (i*SUB_MAX), ((i+1)*SUB_MAX) );
		}
		
		// Set the generation
		generation = 0;
		
		// Set the number of children
		numOfChildren = 0;
	} // Bug()
	
	public Bug(int[] dna){ // dna must be a int[MAX]
		this.dna = dna;
		
		// Determine the values
		for(int i = 0; i<N; i++){
			values[i] = determineValue( (i*SUB_MAX), ((i+1)*SUB_MAX) );
		}
		
		// Set the generation
		generation = 0;
	} // Bug(int[] dna)
	
	private int determineValue(int start, int end){
		int result = 0;
		// Calculate the value by reading the given section of the char array
		// Add one to result for each '1'
		for(int i=start; i<end; i++){
			if(dna[i]==1) result++;
		}
		return result;
	} // determineValue()
	
	// Mate two bug parents together to produce a child bug
	public Bug mate(Bug Parent){
		int crossover;
		int firstParent;
		int mutationLocation;

		Bug child;
		
		int[] dnaP1 = this.getdna();
		int[] dnaP2 = Parent.getdna();
		int[] dnaChild = new int[MAX];
		
		Random rand = new Random();
		
		// Determine crossover
		crossover = rand.nextInt(MAX - 199) + 100; // 10 - 90
		
		// Determine which parent comes first
		firstParent = rand.nextInt(2); // 0 - 1
		
		//Create new char array
		if(firstParent == 1){
			for(int i = 0; i<crossover; i++){
				dnaChild[i] = dnaP1[i];
			}
			for(int i = crossover; i<MAX; i++){
				dnaChild[i] = dnaP2[i];
			}
			//Change numOfChildren
			this.numOfChildren++;
		}
		else{
			for(int i = 0; i<crossover; i++){
				dnaChild[i] = dnaP2[i];
			}
			for(int i = crossover; i<MAX; i++){
				dnaChild[i] = dnaP1[i];
			}
			//Change numOfChildren
			Parent.numOfChildren++;
		}
		
		// Perform mutation on child dna char array
		for(int m=0; m<MUTATION; m++){
			mutationLocation = rand.nextInt(MAX);
			if(dnaChild[mutationLocation] == 1) dnaChild[mutationLocation] = 0;
			else dnaChild[mutationLocation] = 1;
		}
		
		// Create the child
		child = new Bug(dnaChild);
		
		// Increment the generation based on the parent who provided the most genetic information
		if( ((firstParent == 1) && (crossover >=50)) || ((firstParent == 2) && (crossover < 50)) ){
			child.setGeneration(this.getGeneration() + this.numOfChildren);
		}
		else{
			child.setGeneration(Parent.getGeneration() + Parent.numOfChildren);
		}
		
		return child;
	}
	
	// Getter for the char array
	private int[] getdna(){
		return dna;
	}
	
	// getter for any value
	public double getValue(int i){ if(i<0 || i>=N) return 0.0; else return values[i]; }
	
	// getter for the generation
	public int getGeneration(){ return generation; }
	
	// setter for the generation
	public void setGeneration(int generation) { this.generation = generation; }
} // class Bug
