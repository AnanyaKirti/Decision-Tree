import java.util.ArrayList;
import java.util.List;

/**
 * Node class to create tree node
 *
 */
class Node {
	// attribute list as integer
	// used to check the available instances.
	private List<Integer> attr;
	
	// the Target attribute
	private int targetAttr;

	//children list
    private List<Node> children;

    // parent node
    private Node parent;

    //Instance list
    private List<Instance> inst;
    
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
        this.targetAttr=-1;
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
    /**
     * Method to set target attribute
     * @param targetAttr
     */
    public void setTargeAttr(int targetAttr){
    	this.targetAttr=targetAttr;
    }
    /**
     * Method to get target attribute
     *
     * @return the target attribute
     */
    public int getTargetAttr(){
    	return this.targetAttr;
    }
}

