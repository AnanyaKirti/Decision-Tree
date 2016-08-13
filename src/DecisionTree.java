import java.io.*;
import java.util.*;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;


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

		    
		    // helper to get the median of the features.
		    List<List<Integer>> featureRange = new ArrayList<List<Integer>>();
		    for (int i = 0; i < numberOfFeatures-1; i++) {
				featureRange.add(i, new ArrayList<Integer>());
			}

		    
		    // select the first 1000 instances as the Training set, the rest become the test set.
		    List<Instance> TrainingSet = new ArrayList<Instance>();
		    for (int i = 0; i < 1000; i++) {
		    	// add the instance to the TrainingSet, and delete it from the Instance Set.
		    	TrainingSet.add(InstanceSet.remove(0));
		    	for (int j = 0; j < numberOfFeatures-1; j++) {
		    		// Populate the featureRange.
		    		featureRange.get(j).add(TrainingSet.get(i).getFeatureValue(j));
				}
			}
		    
		    // we get the median feature, to decide where to split the data.
		    int[] medianFeature= new int[numberOfFeatures -1];
		    for (int i = 0; i < numberOfFeatures-1 ; i++) {
				Collections.sort(featureRange.get(i));
				medianFeature[i] = featureRange.get(i).get(1000/2) + 1;
				if (featureRange.get(i).get(0) == 0 && featureRange.get(i).get(numberOfFeatures - 1) == 1) {
					medianFeature[i] = 1;
				}
			}

		    
		    
			// Now we have generated the test data, and training data.
		    
			
		    
		    
		}
		catch (Exception e) {
			System.out.println("Error in opening file!");
		}
		
		
		
		
		
	}
	
	
	

	
	
	/**
	 * Method to extract the best feature
	 * @param attr	The list of available attributes.
	 * @param inst	The list of the instances available at the node.
	 * @return The index of the best feature, the target feature.
	 */
//	private int getBestFeature( List<Integer> attr, List<Instance> inst, int[] medianFeature){
//		
//		// TODO finish the function
//		
//		float informationGain = 0f;
//		float bestInformationGain = 0f;
//		int targetAtribute = -1;
//		for (int index = 0; index < attr.size(); index++) {
//			if (attr.get(index)== 1) {
//				informationGain = 0;
//				for (Instance instance : inst) {
//					instance.getFeatureValue(index);
//				}
//			}
//		}
//		
//		
//		return targetAtribute;
//	}

}