import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class File {
	List<Instance> TrainingSet = new ArrayList<Instance>();
	List<Instance> InstanceSet = new ArrayList<Instance>();
	static List<List<Integer>> FeatureValues = new ArrayList<List<Integer>>();
	int numberOfInstances;
	int numberOfFeatures;
	
	public File(String fileName){
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			// read the 1st line of input, that is the number of Instances
		    numberOfInstances = Integer.parseInt(br.readLine());
		    System.out.println("Number of Instances is: " + numberOfInstances);

			// read the 2nd line of input, that is the number of Features
		    numberOfFeatures = Integer.parseInt(br.readLine());
		    System.out.println("Number of Features is: " + numberOfFeatures); 
		    
		    for (int i = 0; i < numberOfFeatures; i++) {
				FeatureValues.add(i, new ArrayList<Integer>());

		    }
		    
		    // read the rest of the input data.
		    String line; // string iterator.
		    while ((line = br.readLine()) != null) {
		    	InstanceSet.add(new Instance(line));	// populate the list of all instances.
		     }
		    
		    //randomised the instance set.
		    long seed = System.nanoTime();
		    Collections.shuffle(InstanceSet, new Random(seed)); 
		    
		    // select the first 1000 instances as the Training set, the rest become the test set.
		    for (int i = 0; i < 1000; i++) {
		    	// add the instance to the TrainingSet, and delete it from the Instance Set.
		    	TrainingSet.add(InstanceSet.remove(i));
			}
		    
		    for (List<Integer> list : FeatureValues) {
		    	Collections.sort(list);
			}
		    System.out.println("Finished Reading the file.");
		}
		catch (Exception e) {
			System.out.println("Error in opening file!");
		}
	}
}

