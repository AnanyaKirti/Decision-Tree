/**
 * Class for experiment 3.
 */
public abstract class Test3 {
	static float accuracy;
	static Node rootNode;
	static File file;
	static int numberOfNodesPrunedTree;

	public static void Start(String fileName) {

		file = new File(fileName);
		rootNode = TreeBuilderClass.BuildTree(file);
		numberOfNodesPrunedTree = TreeBuilderClass.numberOfNodes;

		accuracy = TreeTesterClass.TreeTester(rootNode, file);

		System.out.println("Accuracy of the tree: " + accuracy);
		System.out.println("Number of Nodes in the tree " + TreeBuilderClass.numberOfNodes);
		System.out.println("Number of Leaf Nodes in the tree " + TreeTesterClass.getNumberOfLeafNodes(rootNode));
		
		PruneTree(rootNode);
		
		System.out.println("Complete pruning");
		System.out.println("Accuracy of the pruned tree " + accuracy);
		System.out.println("Number of Nodes in the pruned tree " + numberOfNodesPrunedTree);
		System.out.println("Number of Leaf Nodes in the tree " + TreeTesterClass.getNumberOfLeafNodes(rootNode));
	}

	/**
	 * Static Recursive function to function to Prune the tree.
	 * 
	 * @param node
	 */
	public static void PruneTree(Node node) {
		if (isParentOfLeaves(node)) {
			node.setFlag(true);
			if (isBetter(node, file)) {
				numberOfNodesPrunedTree -= node.getChildren().size();
				node.deleteChildren();
				node.setFlag(false);
			}
		} else {
			for (Node childNode : node.getChildren()) {
				PruneTree(childNode);
			}
		}
		if (isParentOfLeaves(node)) {
			node.setFlag(true);
			if (isBetter(node, file)) {
				numberOfNodesPrunedTree -= node.getChildren().size();
				node.deleteChildren();
				node.setFlag(false);
			}
		}
	}

	/**
	 * Static function to check if the node is the parent of all leaf nodes.
	 * 
	 * @param node
	 *            The node in consideration
	 * @return true if isParentOfLeaves or else false.
	 */
	public static boolean isParentOfLeaves(Node node) {
		if (node.hasChildren() == 1) {
			boolean state = true;
			for (Node childNode : node.getChildren()) {
				if (childNode.hasChildren() == 1) {
					state = false;
				}
			}
			return state;
		} else {
			return false;
		}
	}

	/**
	 * Static function to check if removing a node offers a benefit.
	 * 
	 * @param rootNode
	 *            The root Node of the tree
	 * @param file
	 *            File containing the data
	 * @return t rue if removing the tree increses the accuracy.
	 */
	private static boolean isBetter(Node rootNode, File file) {
		float prunedAccuracy = TreeTesterClass.TreeTester(rootNode, file);
		if (prunedAccuracy >= accuracy) {
			accuracy = prunedAccuracy;
			return true;
		} else {
			return false;
		}
	}
}
