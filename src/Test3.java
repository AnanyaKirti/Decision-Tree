
public abstract class Test3 {
	public static void Start(String fileName){
		
		File file = new File(fileName);
		Node rootNode = TreeBuilderClass.BuildTree(file);

		float accuracy;
		accuracy = TreeTesterClass.TreeTester(rootNode, file);
		System.out.println("Tree Classification Accuracy: " + accuracy);
		System.out.println("Number of Nodes in the tree " + TreeBuilderClass.numberOfNodes);
		
		
	}

}
