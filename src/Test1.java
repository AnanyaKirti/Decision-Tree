/**
 * Class for experiment 1.
 */
public abstract class Test1 {
	public static void Start(String fileName) {

		File file = new File(fileName);
		Node rootNode = TreeBuilderClass.BuildTree(file);

		for (int i = 0; i < TreeBuilderClass.splitCount.length; i++) {
			System.out.println("Feature " + i + " count : " + TreeBuilderClass.splitCount[i]);
		}

		float accuracy;
		accuracy = TreeTesterClass.TreeTester(rootNode, file);
		System.out.println("Tree Classification Accuracy: " + accuracy);
		System.out.println("Number of Nodes in the tree " + TreeBuilderClass.numberOfNodes);
		System.out.println("Number of Leaf Nodes in the tree " + TreeTesterClass.getNumberOfLeafNodes(rootNode));

		int maxLevel = 2;
		int printMaxLevel = maxLevel + 1;
		float accuracyEarlyStopping;
		System.out.println("Level used for early stopping is " + printMaxLevel);
		accuracyEarlyStopping = TreeTesterClass.TreeTesterEarlyStopping(rootNode, file, maxLevel);
		System.out.println("Number of Nodes in the early stopping tree " + TreeTesterClass.getNumberOfNodes(rootNode));
		System.out.println("Number of Terminal Nodes in the early stopping tree " + TreeTesterClass.getNumberOfTerminalNodes(rootNode));

		System.out.println("Early Stopping Tree Accuracy is: " + accuracyEarlyStopping);
	}
}
