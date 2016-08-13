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
		
		return rootNode;
	}
	
	/**
	 * Recursive function to build the Decision Tree.
	 * 
	 * @param node The current node being worked on
	 * @param attributeAvailable The attributes that have not yet been used
	 * @param instances	The instances available at the node.
	 */
	public void BuildTreeHelper(Node node, List<Integer> attributeAvailable, List<Instance> instances){
		
	}
	
	public int getBestAttribute(){
		int bestAttribute = -1;
		
		
		
		return bestAttribute;
	}
}
	