import java.io.*;
import java.util.*;


public class DecisionTree {

	/**
	 * 
	 * @param args input the file, as well as the test number
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO use args
		

		try (BufferedReader br = new BufferedReader(new FileReader("ticdata2000.txt"))) {
			// read the 1st line of input, that is the number of Instances
		    int numberOfInstances = Integer.parseInt(br.readLine());
		    System.out.println("number of Instances is: " + numberOfInstances);

			// read the 1st line of input, that is the number of Features
		    int numberOfFeatures= Integer.parseInt(br.readLine());
		    System.out.println("number of Features is: " + numberOfFeatures); 
		    
		    // read the rest of the input data.
		    String line; // string iterator.
		    List<Instance> InstanceSet = new ArrayList<Instance>(); // The list of all instances.
		    while ((line = br.readLine()) != null) {
		    	InstanceSet.add(new Instance(line));	// populate the list of all instances.
		     }
		    
		    //randomised the instance set.
		    long seed = System.nanoTime();
		    Collections.shuffle(InstanceSet, new Random(seed)); 
		    
		    // select the first 1000 instances as the Training set, the rest become the test set.
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

/** tree class to initiate tree 
 * 
 */
class Tree {
	private Node root;
	/** initializing a tree
	 * 
	 */
    public Tree() {
        super();
    }
    
    /** method to get the root node.
     * 
     * @return
     */
    public Node getRoot() {
        return this.root;
    }
    
    /** setting tree root
     * 
     * @param root the root node of the tree
     */
    public void setRoot(Node root) {
        this.root = root;
    }
}

/**
 * Node class to create tree node
 *
 */
class Node {
	//attribute list as integer
	public List<Integer> attr;
	
	//children list
    public List<Node> children;

    // parent node
    public Node parent;

    //Instance list
    public List<Instance> inst;
    
    public Node() {
        super();
    }
    /**
     * Initializing Node by giving List of Attribute and Instances
     * we can set children and parent using given functions
     * 
     * @param attr attribute list as integer
     * @param inst Instance list
     */
    public Node(List<Integer> attr,List<Instance> inst) {
        this();
        this.parent=null;
        setAttr(attr);
        setInst(inst);
    }
    
    /** to get children array list
     * 
     * @return List of children
     */
    public List<Node> getChildren() {
        if (this.children == null) {
            return new ArrayList<Node>();
        }
        return this.children;
    }
    
    /** function to set the children
     * 
     * 
     * @param children 
     */
    public void setChildren(List<Node> children) {
        this.children = children;
    }
    
    /**
     * 
     * @return returns the number of children, 0 if it is a leaf node.
     */
    public int getNumberOfChildren() {
    	// test for leaf node.
        if (children == null) {
            return 0;
        }
        return children.size();
    }
    
    /** addChild to children list
     * 
     * 
     * @param child 
     */
    public void addChild(Node child) {
        if (children == null) {
            children = new ArrayList<Node>();
        }
        children.add(child);
    }
   
    /** get attribute list for this node
     * 
     * @return returns the data associated with the list.
     */
    public List<Integer> getData() {
        return this.attr;
    }
    
    /** set attribute list of integers
     * 
     * @param attr sets the attr
     */
    public void setAttr(List<Integer> attr) {
        this.attr = attr;
    }
    
    /** Method to get Instances in current node
     * 
     * @return Instances associated with the node.
     */
    public List<Instance> getInst() {
         return this.inst;
    }
    
    /** Method to set instances in current node
     * 
     * 
     * @param Instance list
     */
    public void setInst(List<Instance> inst) {
         this.inst = inst;
    }
    
    /** Methdo to set the parent node
     * 
     * @param par
     */
    public void setParent(Node par){
    	this.parent=par;
    } 
    
    /** Method to get the parent node.
     * 
     */
    public Node getParent(){
    	return this.parent;
    }
}

/**
 * The instance class is used to represent an instance 
 * 
 * @param s The String is is used to collect the feature set of the instance. 
 * The last item of the string is the class label
 *
 */
class Instance{
	private int classLabel = -1;
	private int[] featureList;
	/**
	 * 
	 * @param s The String is is used to collect the feature set of the instance. 
	 * The last item of the string is the class label
	 */
	Instance(String s){
		try{
			featureList = toIntArray(s);
		}
		catch (Exception c){
			System.out.println("error in instance with featurs :" + s);
		}
	}
	
	/**
	 * Method to get the ClassLabel
	 * 
	 * @return classLabel returns the class label associated with the instance/
	 * returns -1 if error.
	 */
	public int getClassLabel(){

		return classLabel;
	}
	
	/**
	 * Method to set the class label
	 * 
	 * @param i set the class label to the value i
	 */
	public void setClassLabel(int i){
		classLabel = i;
	}
	
	/**
	 * Method to convert a string to an int array.
	 * Also sets the classlabel of the instance.
	 * 
	 * @param input The string s
	 * @return the int array which represents the feature set of the instance.
	 */
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


