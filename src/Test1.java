
public abstract class Test1 {
	public static void Start(String fileName){
		
		File file = new File(fileName);
		Node rootNode = TreeBuilderClass.BuildTree(file);
		
		for (int i = 0; i < TreeBuilderClass.splitCount.length; i++) {
			System.out.println("Feature " + i + " count : " + TreeBuilderClass.splitCount[i]);
		}
		
		float accuracy;
		float accuracyEarlyStopping;
		accuracy = TreeTesterClass.TreeTester(rootNode, file);
		System.out.println("Tree Classification Accuracy: " + accuracy);
		System.out.println("Number of Nodes in the tree " + TreeBuilderClass.numberOfNodes);
		
		int maxLevel = 2;
		System.out.println("Level used for early stopping is" + maxLevel );
		accuracyEarlyStopping = TreeTesterClass.TreeTesterEarlyStopping(rootNode, file, maxLevel);
		
		
		System.out.println("Early Stopping Tree Accuracy is: " + accuracyEarlyStopping);
		
		
	}
}
