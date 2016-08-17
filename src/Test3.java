
public abstract class Test3 {
	static float accuracy;
	static Node rootNode;
	static File file;
	static int numberOfNodesPrunedTree;
	
	public static void Start(String fileName){
		
		file = new File(fileName);
		rootNode = TreeBuilderClass.BuildTree(file);
		numberOfNodesPrunedTree = TreeBuilderClass.numberOfNodes;

		accuracy = TreeTesterClass.TreeTester(rootNode, file);
		
		System.out.println("Accuracy of the tree: "+accuracy);
		System.out.println("Number of Nodes in the tree " + TreeBuilderClass.numberOfNodes);
		
		PruneTree(rootNode);
		System.out.println("Complete pruning");
		System.out.println("Accuracy of the pruned tree "+ accuracy);
		System.out.println("Number of Nodes in the pruned tree " + numberOfNodesPrunedTree);
		
//		System.out.println("Tree Classification Accuracy: " + accuracy);
//		System.out.println("Number of Nodes in the tree " + TreeBuilderClass.numberOfNodes);
		
	}
	
	public static void PruneTree(Node node){
		if (isParentOfLeaves(node)) {
			node.setFlag(true);
			if (isBetter(node, file)) {
				numberOfNodesPrunedTree -= node.getChildren().size();
				node.deleteChildren();
				node.setFlag(false);
			}
		}
		else{
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
	
	public static boolean isParentOfLeaves(Node node){
		if (node.hasChildren() == 1) {
			boolean state = true;
			for (Node childNode : node.getChildren()) {
				if (childNode.hasChildren() == 1) {
					state = false;
				}
			}
			return state;
		}
		else{
			return false;
		}
	}
	
	private static boolean isBetter(Node rootNode, File file){
		float prunedAccuracy = TreeTesterClass.TreeTester(rootNode, file);
		if (prunedAccuracy >= accuracy) {
			accuracy = prunedAccuracy;
			return true;
		}
		else{
			return false;
		}
		
	}

}
