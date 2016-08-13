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
		BuildTreeHelper(rootNode, file.TrainingSet);
		
		return rootNode;
	}
	
	/**
	 * Recursive function to build the Decision Tree.
	 * 
	 * @param node The current node being worked on
	 * @param attributeAvailable The attributes that have not yet been used
	 * @param instances	The instances available at the node.
	 */
	public static void BuildTreeHelper(Node node, List<Instance> instances){// , List<Integer> attributeAvailable,){
		int positiveValues = 0;
		int negativeValues = 0;
		int totalValues = instances.size();
		
		for (Instance instance : instances) {
			if(instance.getClassLabel() == 0){
				positiveValues++;
			}
			else{
				negativeValues++;
			}
		}
//		System.out.println(positiveValues);
//		System.out.println(negativeValues);
//		System.out.println(Math.log(positiveValues));
//		System.out.println(Math.log(totalValues));
		
		float entropy = (float) (-1*(positiveValues/totalValues * (Math.log(positiveValues) - Math.log(totalValues)))
				+  (negativeValues/totalValues * (Math.log(negativeValues) - Math.log(totalValues))  )) ;
		System.out.println(entropy);
		
	}
	
	
	public int getBestAttribute(){
		int bestAttribute = -1;
		
		
		
		return bestAttribute;
	}
}
	