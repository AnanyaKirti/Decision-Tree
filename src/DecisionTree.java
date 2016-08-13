import java.io.*;
import java.util.*;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;
import sun.util.logging.resources.logging;


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
	 * @param attributeAvailable	The list of available attributes.
	 * @param instances	The list of the instances available at the node.
	 * @param medianFeature the median of the features, used to split the data.
	 * @return The index of the best feature, the target feature.
	 */
	@SuppressWarnings("unused")
	private static int getBestFeature( List<Integer> attributeAvailable, List<Instance> instances, int[] medianFeature, float entropy){
		
		// TODO finish the function
		
		float informationGain = 0f;
		float bestInformationGain = 0f;
//		float entropy = 1	f;
		float entropyLeft = 1f;
		float entropyRight = 1f;
		
		int numberOfInstances = instances.size();
		int targetAtribute = -1;
		
		for (int index = 0; index < attributeAvailable.size(); index++) {
			informationGain = 0;
		    int[] leftChild = new int[3];
		    int[] rightChild = new int[3];

			
			if (attributeAvailable.get(index) == 1) {
				informationGain = 0;
				for (Instance instance : instances) {
					if (instance.getFeatureValue(index) < medianFeature[index]) {
						leftChild[instance.getClassLabel()]++;
						leftChild[2]++;
					}
					else {
						rightChild[instance.getClassLabel()]++;
						rightChild[2]++;
					}
				}
			}
			
			entropyLeft = (float) (-1*leftChild[0]/leftChild[2]* Math.log(leftChild[0]/leftChild[2])) + (float) (-1*leftChild[1]/leftChild[2]* Math.log(leftChild[1]/leftChild[2]));
			entropyRight= (float) (-1*rightChild[0]/rightChild[2]* Math.log(rightChild[0]/rightChild[2])) + (float) (-1*rightChild[1]/rightChild[2]* Math.log(leftChild[1]/leftChild[2]));
			
			informationGain = entropy - entropyLeft/numberOfInstances - entropyRight/numberOfInstances;
			

			
		}
		
		
		return targetAtribute;
	}
}

