import java.util.List;

/**
 * Class to build the random classifier
 */
public abstract class RandomClassifier {
	public static float TreeTester(List<Node> trees, File file) {
		float accuracy = 0;
		int correct = 0;
		int incorrect = 0;
		int totalCorrect = 0;
		int total = 0;

		for (Instance instance : file.InstanceSet) {
			total++;
			correct = 0;
			incorrect = 0;
			for (int i = 0; i < trees.size(); i++) {
				if (Classifier(trees.get(i), instance) == instance.getClassLabel()) {
					correct++;
				} else {
					incorrect++;
				}
			}
			if (correct > incorrect) {
				totalCorrect++;
			}
		}
		accuracy = (float) totalCorrect / total * 100;
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
		}  else {
			if (Math.random() <= 0.5) {
				return 1;
			}
			else{
				return 0;
			}
		}
	}

}
