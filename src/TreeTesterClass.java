import java.util.List;

/**
 * Abstract class to Test Tree formed
 */
public abstract class TreeTesterClass {

	static int maxLevel;

	/**
	 * Static function to test the accuracy of the tree. With a max level of
	 * maxLevel
	 * 
	 * @param rootNode
	 *            The rootNode of the tree
	 * @param file
	 *            The File data
	 * @return The acccuracy of the tree.
	 */
	public static float TreeTester(Node rootNode, File file) {
		float accuracy = 0;
		int correct = 0;
		int total = 0;

		for (Instance instance : file.InstanceSet) {
			total++;
			if (Classifier(rootNode, instance) == instance.getClassLabel()) {
				correct++;
			}
		}
		accuracy = (float) correct / total * 100;
		return accuracy;
	}

	/**
	 * Static recursive function to classify an instance, using the tree formed.
	 * 
	 * @param node
	 *            The current node being considered.
	 * @param instance
	 *            The Instance being considered to be classified
	 * @return The class label of the instance
	 */
	public static int Classifier(Node node, Instance instance) {
		int label = 0;
		int state = 0;
		if (node.flag == false) {

			if (node.hasChildren() == 1) {
				List<Node> children = node.getChildren();
				for (Node child : children) {
					if (instance.getFeatureValue((node.getTargetAttribute())) == child.getTargetAttributeValue()) {
						label = Classifier(child, instance);
						state = 1;
						break;
					}
				}
				if (state == 0) {
					return Majority(node);
				}
			} else {
				return Majority(node);
			}
			return label;
		} else {
			return Majority(node);
		}

	}

	/**
	 * Static function to test the tree in case of early stopping.
	 * 
	 * @param rootNode
	 *            rootNode of the tree
	 * @param file
	 *            File containing the dataset
	 * @param level
	 *            currenct level being considered.
	 * @return returns the accuracy of the early stopping tree.
	 */
	public static float TreeTesterEarlyStopping(Node rootNode, File file, int level) {
		maxLevel = level;
		float accuracy = 0;
		int correct = 0;
		int total = 0;

		for (Instance instance : file.InstanceSet) {
			total++;
			if (ClassifierEarlyStopping(rootNode, instance, 0) == instance.getClassLabel()) {
				correct++;
			}
		}
		accuracy = (float) correct / total * 100;
		return accuracy;
	}

	/**
	 * Method to classifiy the instance with early stopping
	 * 
	 * @param node
	 *            The node in consideration
	 * @param instance
	 *            The instance in consideration
	 * @param level
	 *            MaxLevel allowed
	 * @return The class label of the instance.
	 */
	public static int ClassifierEarlyStopping(Node node, Instance instance, int level) {
		int label = 0;
		int state = 0;
		if (level >= maxLevel) {
			return Majority(node);
		}
		if (node.hasChildren() == 1) {
			List<Node> children = node.getChildren();
			for (Node child : children) {
				if (instance.getFeatureValue((node.getTargetAttribute())) == child.getTargetAttributeValue()) {
					label = ClassifierEarlyStopping(child, instance, ++level);
					state = 1;
					break;
				}
			}
			if (state == 0) {
				return Majority(node);
			}
		} else {
			return Majority(node);
		}
		return label;

	}

	/**
	 * Static method to return the majority of the class label at the node
	 * 
	 * @param node
	 *            The node in consideration
	 * @return returns the class label which is in majority.
	 */
	private static int Majority(Node node) {
		int positive = 0;
		int negative = 0;
		for (Instance instance : node.getInstances()) {
			if (instance.getClassLabel() == 0) {
				positive++;
			} else {
				negative++;
			}
		}
		if (positive > negative) {
			return 0;
		} else if (negative > positive) {
			return 1;
		} else {
			if (Math.random() <= 0.5) {
				return 1;
			}
			else{
				return 0;
			}
		}
	}

	/**
	 * Function to count the number of nodes in the early stopping tree with
	 * maxlevel maxLevel.
	 * 
	 * @param node
	 *            the current node of the tree. current node in consideration.
	 */
	private static void setNumberOfNodes(Node node, int level) {
		if (level <= maxLevel) {
			for (Node childNode : node.getChildren()) {
				TreeBuilderClass.numberOfNodes++;
				setNumberOfNodes(childNode, ++level);
			}
		}
	}

	/**
	 * Function to get the number of nodes in the early stopping tree.
	 * 
	 * @param rootNode
	 *            root node of the tree.
	 * @return 
	 * 			  the number of nodes of the tree.
	 */
	public static int getNumberOfNodes(Node rootNode) {
		TreeBuilderClass.numberOfNodes = 0;
		setNumberOfNodes(rootNode, 0);
		return TreeBuilderClass.numberOfNodes;
	}
}
