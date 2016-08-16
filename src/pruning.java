import java.util.ArrayList;
import java.util.List;

public abstract class pruning {
	static int numberOfNodes = 0;
	static List validSet=new ArrayList<Instance>();
	static List remainTestSet=new ArrayList<Instance>();
	static float accuracy=0;
	static float accuPrun=0;
	static List lostChilderen=new ArrayList<Node>();
	static Node parent;
	public static Node getLeaf(Node root){
		if (root.hasChildren()==0){
			return root;
		}else{
			root=root.getLastChild();
			getLeaf(root);
		}
		return root;
	}
	public void setValidationSet(List<Instance> instances){
		
		for (int i = 0; i <100; i++) {
			validSet.add(instances.remove(i));
		}
		remainTestSet=instances;
	}
	public static Node deleteNode(Node root,int numberNodes,List<Instance> instances){
		accuracy=prunClassifier.TreeTester(root,validSet);
		numberOfNodes=numberNodes;
		Node leaf=getLeaf(root);
		if (leaf.getParent()!=null){
			lostChilderen=leaf.getParent().getChildren();	
			parent=leaf.getParent();
			parent.setChildren(null);	
			accuPrun=prunClassifier.TreeTester(root, validSet);
			if (accuPrun>accuracy){
				numberOfNodes=numberOfNodes-leaf.getParent().getChildren().size();
				return root;
			} else {
				parent.setChildren(lostChilderen);
				return root;
			}
		} 
		else{
			return root;	
		}
		
	}
}
