import java.io.*;
import java.util.*;


public class DecisionTree {

	/**
	 * 
	 * @param args input the file, as well as the test number
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO use args
		

		try (BufferedReader br = new BufferedReader(new FileReader("ticdata2000.txt"))) {
			// read the 1st line of input, that is the number of Instances
		    int numberOfInstances = Integer.parseInt(br.readLine());
		    System.out.println("number of Instances is: " + numberOfInstances);

			// read the 2nd line of input, that is the number of Features
		    int numberOfFeatures= Integer.parseInt(br.readLine());
		    System.out.println("number of Features is: " + numberOfFeatures); 
		    
		    // read the rest of the input data.
		    String line; // string iterator.
		    List<Instance> InstanceSet = new ArrayList<Instance>(); // The list of all instances.
		    while ((line = br.readLine()) != null) {
		    	InstanceSet.add(new Instance(line));	// populate the list of all instances.
		     }
		    
		    //randomised the instance set.
		    long seed = System.nanoTime();
		    Collections.shuffle(InstanceSet, new Random(seed)); 
		    
		    // select the first 1000 instances as the Training set, the rest become the test set.
		    List<Instance> TrainingSet = new ArrayList<Instance>();
		    for (int i = 0; i < 1000; i++) {
		    	TrainingSet.add(InstanceSet.remove(i));
			}
		}
		catch (Exception e) {
			System.out.println("Error in opening file!");
		}
		
		
		// Now we have generated the test data, and training data.
		
		
		
	}
	
	private int getBestFeature( List<Integer> attr, List<Instance> inst){
		long informationGain = 0;
		for (int j = 0; j < attr.size(); j++) {
			if (attr[j] == 1) {
				
			}
		}
		
		
		return i;
	}

}

