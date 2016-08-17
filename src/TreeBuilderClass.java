import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class to build the tree
 */
public abstract class TreeBuilderClass {
	static int[] splitCount = new int[85];
	static int numberOfNodes = 1;

	/**
	 * Method to initialise the Decision Tree.
	 * 
	 * @param file
	 *            all the information regarding the data
	 * @return the root node of the tree.
	 */
	public static Node BuildTree(File file) {

		List<Integer> attributeAvailable = new ArrayList<Integer>();
		for (int i = 0; i < file.numberOfFeatures; i++) {
			attributeAvailable.add(1);
		}
		// initialise the root node of the tree
		Node rootNode = new Node(file.TrainingSet, attributeAvailable);
		BuildTreeHelper(rootNode, file.TrainingSet);
		System.out.println("Finished Building the Tree");
		return rootNode;
	}

	/**
	 * Recursive function to build the Decision Tree.
	 * 
	 * @param node
	 *            The current node being worked on
	 * @param attributeAvailable
	 *            The attributes that have not yet been used
	 * @param instances
	 *            The instances available at the node.
	 */
	public static void BuildTreeHelper(Node node, List<Instance> instances) {
		// get the entropy of the current node.
		List<Integer> attributeAvailable = node.getAttribute();
		int bestAttribute = getBestAttribute(instances, node.getAttribute());
		node.setTargeAttribute(bestAttribute);
		if (bestAttribute != -1) {
			attributeAvailable.set(bestAttribute, new Integer(0));
			node.setAttribute(attributeAvailable);
			splitCount[bestAttribute]++;
			// create a list of list to maintain which instance goes in which
			// child node
			List<List<Instance>> childrenInstances = new ArrayList<List<Instance>>();
			for (int j = 0; j < File.FeatureValues.get(bestAttribute).size(); j++) {
				// initialise the list of list
				childrenInstances.add(j, new ArrayList<Instance>());
			}
			// iterate over the instances to sort the instances.
			for (Instance instance : instances) {
				// select which child node the instance belongs to
				int index = File.FeatureValues.get(bestAttribute).indexOf(instance.getFeatureValue(bestAttribute));
				// add the instance to the child node.
				childrenInstances.get(index).add(instance);
				// increase the value of the child node instances
			}

			// create the child of the node.
			int i = 0;
			for (List<Instance> child : childrenInstances) {
				if (child.size() >= 0) {
					numberOfNodes++;
					node.addChild(new Node(child, node.getAttribute()));
					node.getLastChild().setTargetAttributeValue(File.FeatureValues.get(bestAttribute).get(i));
					if (isPure(child) != 1) {
						BuildTreeHelper(node.getLastChild(), child);
					}
				}
				i++;
			}

		}
	}

	/**
	 * Method to calculate the total entropy of the attribute
	 * 
	 * @param entropy
	 *            array of individual child node entropy
	 * @param childInstanceNumber
	 *            number of children in the child node
	 * @param totalValues
	 *            total values at the parent node
	 * @return returns the totalentropy
	 */
	private static float totalEntropy(float[] entropy, int[] childInstanceNumber, int totalValues) {
		float totalEntropy = 0;

		for (int i = 0; i < childInstanceNumber.length; i++) {
			totalEntropy += entropy[i] * (childInstanceNumber[i]) / totalValues;
		}

		return totalEntropy;
	}

	/**
	 * Method to get entropy of the instances. Assumes binary classes.
	 * 
	 * @param instances
	 *            The instance list at the node.
	 */
	private static float getEntropy(List<Instance> instances) {
		int positiveValues = 0;
		int negativeValues = 0;
		int totalValues = instances.size();

		// populate the entropy
		for (Instance instance : instances) {
			if (instance.getClassLabel() == 0) {
				positiveValues++;
			} else {
				negativeValues++;
			}
		}

		// if the values are zero
		if (positiveValues == 0 || negativeValues == 0) {
			return 0;
		}

		// calculate the entropy
		float entropy = (float) ((float) positiveValues / (float) totalValues
				* ((Math.log(positiveValues) - Math.log(totalValues)) / Math.log(2)));
		entropy += (float) ((float) negativeValues / (float) totalValues
				* ((Math.log(negativeValues) - Math.log(totalValues)) / Math.log(2)));
		entropy *= -1;

		return entropy;
	}

	/**
	 * method to get the best Attribute on the basis of information gain.
	 * 
	 * @param instances
	 *            all the instances at the node
	 * @param attributeAvailable
	 *            all the attrivutes available at the node 1 if available, 0 if
	 *            not available.
	 * @return returns the index of the best attribute available.
	 */
	public static int getBestAttribute(List<Instance> instances, List<Integer> attributeAvailable) {
		int totalValues = instances.size();

		float minEntropy = 1;
		float totalEntropy = 0;

		// initialised the bestAttribute to -1.
		int bestAttribute = -1;

		for (int i = 0; i < attributeAvailable.size(); i++) {
			if (attributeAvailable.get(i) != 0) {
				// create a float array to calculate the entropy of each child
				// nodes.
				float[] entropy = new float[File.FeatureValues.get(i).size()];
				// create an int array to store the number of nodes in the child
				// nodes
				int[] childInstanceNumber = new int[File.FeatureValues.get(i).size()];

				// create a list of list to maintain which instance goes in
				// which child node
				List<List<Instance>> childrenInstances = new ArrayList<List<Instance>>();
				for (int j = 0; j < File.FeatureValues.get(i).size(); j++) {
					// initialise the list of list
					childrenInstances.add(j, new ArrayList<Instance>());
				}

				// iterate over the instances to sort the instances.
				for (Instance instance : instances) {
					// select which child node the instance belongs to
					int index = File.FeatureValues.get(i).indexOf(instance.getFeatureValue(i));
					// add the instance to the child node.
					childrenInstances.get(index).add(instance);
					// increase the value of the child node instances
					childInstanceNumber[index]++;
				}

				// calculate the entropy for each of the child nodes.
				for (int j = 0; j < File.FeatureValues.get(i).size(); j++) {
					entropy[j] = getEntropy(childrenInstances.get(j));
				}

				// calculate the total entropy
				totalEntropy = totalEntropy(entropy, childInstanceNumber, totalValues);
				if (minEntropy > totalEntropy) {
					bestAttribute = i;
					minEntropy = totalEntropy;
				}
			}
		}
		return bestAttribute;
	}

	/**
	 * Method to check if the node is pure, i.e. is populated with only one
	 * class label.
	 * 
	 * @param instances
	 *            the instances associated with the node
	 * @return returns 1 if true, 0 if false.
	 */
	private static int isPure(List<Instance> instances) {
		int state = 1;
		if (instances.size() < 2) {
			return 1;
		}
		for (int i = 1; i < instances.size(); i++) {
			if (instances.get(i - 1).getClassLabel() != instances.get(1).getClassLabel()) {
				state = 0;
				return state;
			}
		}
		return state;
	}
}