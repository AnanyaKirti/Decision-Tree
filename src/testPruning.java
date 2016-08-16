
public class testPruning {
	public static void Start(String fileName){
		
		File file = new File(fileName);
		Node rootNode = TreeBuilderClass.BuildTree(file);
		Node rootPruned=pruning.deleteNode(rootNode, TreeBuilderClass.numberOfNodes,file.InstanceSet);
		
		for (int i = 0; i < TreeBuilderClass.splitCount.length; i++) {
			System.out.println("Feature " + i + " count : " + TreeBuilderClass.splitCount[i]);
		}
		
		float accuracy;
		float accuracyEarlyStopping;
		accuracy = TreeTesterClass.TreeTester(rootNode, file);
		System.out.println("Tree Classification Accuracy without pruning: " + accuracy);
		System.out.println("Number of Nodes in the tree without pruning " + TreeBuilderClass.numberOfNodes);
		
		accuracy = TreeTesterClass.TreeTester(rootPruned, file);
		System.out.println("Tree Classification Accuracy with pruning: " + accuracy);
		System.out.println("Number of Nodes in the tree with pruning " + TreeBuilderClass.numberOfNodes);
		
	}
}
