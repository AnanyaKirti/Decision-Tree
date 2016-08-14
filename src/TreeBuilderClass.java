import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
public abstract class TreeBuilderClass {
	/**
	 * 	Method to initialise the Decision Tree.
	 * 
	 * @param file all the information regarding the data
	 * @return	the root node of the tree.
	 */
	public static Node BuildTree(File file){
		
		// initialise the root node of the tree
		Node rootNode = new Node();
//		System.out.println(File.attributeAvailable.size());
		BuildTreeHelper(rootNode, file.TrainingSet, File.attributeAvailable);
		
		return rootNode;
	}
	
	/**
	 * Recursive function to build the Decision Tree.
	 * 
	 * @param node The current node being worked on
	 * @param attributeAvailable The attributes that have not yet been used
	 * @param instances	The instances available at the node.
	 */
	public static void BuildTreeHelper(Node node, List<Instance> instances, List<Integer> attributeAvailable){
		// get the entropy of the current node.
		float currentEntropy = getEntropy(instances);
		getBestAttribute(instances, attributeAvailable);
		
		
	}

	
	
	
	/**
	 *Method to calculate the total entropy of the attribute 
	 * @param entropy	array of individual child node entropy
	 * @param childInstanceNumber	number of children in the child node
	 * @param totalValues	total values at the parent node
	 * @return	returns the totalentropy
	 */
	private static float totalEntropy(float[] entropy, int[] childInstanceNumber, int totalValues){
		float totalEntropy = 0;
		
		for (int i = 0; i < childInstanceNumber.length; i++) {
			totalEntropy += entropy[i] * (float)(childInstanceNumber[i]) / (float)totalValues; 
		}
		
		return totalEntropy;
	}
	
	
	/**
	 * Method to get entropy of the instances.
	 * Assumes binary classes.
	 * @param instances	The instance list at the node.
	 */
	private static float getEntropy(List<Instance> instances) {
		int positiveValues = 0;
		int negativeValues = 0;
		int totalValues = instances.size();
		
		for (Instance instance : instances) {
			if(instance.getClassLabel() == 0){
				positiveValues++;
			}
			else{
				negativeValues++;
			}
		}
		
		if (positiveValues == 0) {
			return 0;
		}
		else if(negativeValues == 0){
			return 0;
		}
		
		float entropy = (float) ((float)positiveValues/(float)totalValues * ((Math.log(positiveValues) - Math.log(totalValues)) / Math.log(2)));
		entropy += (float) ((float)negativeValues/(float)totalValues * ((Math.log(negativeValues) - Math.log(totalValues)) / Math.log(2)));
		entropy *= -1;

		return entropy;
		
	}
	
	
	/**
	 * method to get the best Attribute on the basis of information gain.
	 * @param instances	all the instances at the node
	 * @param attributeAvailable all the attrivutes available at the node 1 if available, 0 if not available.
	 * @return	returns the index of the best attribute available.
	 */
	public static int getBestAttribute(List<Instance> instances, List<Integer> attributeAvailable){
		int totalValues = instances.size();
		
		float minEntropy = 1;
		float totalEntropy = 0;
		// initialised the bestAttribute to -1.
		int bestAttribute = -1;
		for (int i = 0; i < attributeAvailable.size(); i++) {
			if (attributeAvailable.get(i)!= 0) {
				List<List<Instance>> childrenInstances= new ArrayList<List<Instance>>();
				float[] entropy = new float[File.FeatureValues.get(i).size()];
				int[] childInstanceNumber = new int[File.FeatureValues.get(i).size()];
				
				for(int j = 0; j<File.FeatureValues.get(i).size(); j++){
					childrenInstances.add(j, new ArrayList<Instance>());
				}
				for (Instance instance : instances) {
					int index = File.FeatureValues.get(i).indexOf(instance.getFeatureValue(i));
					childrenInstances.get(index).add(instance);
					childInstanceNumber[index] ++;
				}
				for(int j = 0; j<File.FeatureValues.get(i).size(); j++){
					entropy[j] = getEntropy(childrenInstances.get(j));
				}
				
				totalEntropy = totalEntropy(entropy, childInstanceNumber, totalValues);
				if (minEntropy > totalEntropy) {
					bestAttribute = i;
					minEntropy = totalEntropy;
				}
				
			}
			
		}

		System.out.println(bestAttribute);
		System.out.println(minEntropy);
		return bestAttribute;
	}
}