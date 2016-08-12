import java.io.*;
import java.util.*;


public class DecisionTree {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub

		try (BufferedReader br = new BufferedReader(new FileReader("ticdata2000.txt"))) {
		    int numberOfInstances = Integer.parseInt(br.readLine());
		    System.out.println("number of Instances is: " + numberOfInstances);

		    int numberOfFeatures= Integer.parseInt(br.readLine());
		    System.out.println("number of Features is: " + numberOfFeatures); 
		    
		    String line;
		    List<Instance> InstanceSet = new ArrayList<Instance>();
		    while ((line = br.readLine()) != null) {
		    	InstanceSet.add(new Instance(line));
		     }
		    
		    long seed = System.nanoTime();
		    Collections.shuffle(InstanceSet, new Random(seed)); // randomised the instance set.
		    List<Instance> TrainingSet = new ArrayList<Instance>();
		    for (int i = 0; i < 1000; i++) {
		    	TrainingSet.add(InstanceSet.remove(i));
			}
		    
		}
		catch (Exception e) {
			System.out.println("Error in opening file!");
		}
	}

}

//tree class to initiate tree
class Tree {
	private Node root;
	//initializing a tree
    public Tree() {
        super();
    }
    public Node getRoot() {
        return this.root;
    }
    //setting tree root
    public void setRoot(Node root) {
        this.root = root;
    }
}

// Node class to create tree node
class Node {
	//attribute list as integer
	public List<Integer> attr;
	//children list
    public List<Node> children;
    
    public Node parent;
    //Instance list
    public List<Instance> inst;
    
    public Node() {
        super();
    }
    //Initializing Node by giving List of Attribute and Instances
    // we can set children and parent using given functions
    public Node(List<Integer> attr,List<Instance> inst) {
        this();
        this.parent=null;
        setAttr(attr);
        setInst(inst);
    }
    
    //to get children array list
    public List<Node> getChildren() {
        if (this.children == null) {
            return new ArrayList<Node>();
        }
        return this.children;
    }
    //to get children arraylist of Nodes
    public void setChildren(List<Node> children) {
        this.children = children;
    }
    
    public int getNumberOfChildren() {
        if (children == null) {
            return 0;
        }
        return children.size();
    }
    
    //addChild to children array
    public void addChild(Node child) {
        if (children == null) {
            children = new ArrayList<Node>();
        }
        children.add(child);
    }
   
    //get attribute list for this node
    public List<Integer> getData() {
        return this.attr;
    }
    // set attribute list of integers
    public void setAttr(List<Integer> attr) {
        this.attr = attr;
    }
    // get Instances in current node
    public List<Instance> getInst() {
         return this.inst;
    }
    
    //set instances in current node
    public void setInst(List<Instance> inst) {
         this.inst = inst;
    }
    
    //setting parent node
    public void setParent(Node par){
    	this.parent=par;
    } 
    
    public Node getParent(){
    	return this.parent;
    }
}


class Instance{
	private int classLabel = -1;
	private int[] featureList;
	Instance(String s){
		try{
			featureList = toIntArray(s);
//			System.out.println(classLabel);
		}
		catch (Exception c){
			System.out.println("error in instance");
		}
	}
	public int getClassLabel(){
		return classLabel;
	}
	
	public void setClassLabel(int i){
		classLabel = i;
	}
	
	public int[] toIntArray(String input) {
	    String[] split = input.split("\\s+");
	    int[] result = new int[split.length -1];
	    for (int i = 0; i < split.length -1; i++) {
	        result[i] = Integer.parseInt(split[i]);
	    }
	    classLabel = Integer.parseInt(split[split.length - 1]);
	    return result;
	}
}


