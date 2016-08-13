import java.util.ArrayList;
import java.util.List;

/**
 * Node class to create tree node
 *
 */
class Node {
	// attribute list as integer
	// used to check the available instances.
	private List<Integer> attributeAvailable;
	
	// the Target attribute
	private int targetAttribute;

	//children list
    private List<Node> children;

    // parent node
    private Node parent;

    //Instance list
    private List<Instance> instances;
    
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
        this.targetAttribute=-1;
        setAttr(attr);
        setInstances(inst);
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
        return this.attributeAvailable;
    }
    
    /** set attribute list of integers
     * 
     * @param attr sets the attribute
     */
    public void setAttr(List<Integer> attribute) {
        this.attributeAvailable = attribute;
    }
    
    /** Method to get Instances in current node
     * 
     * @return Instances associated with the node.
     */
    public List<Instance> getInstances() {
         return this.instances;
    }
    
    /** Method to set instances in current node
     * 
     * 
     * @param Instance list
     */
    public void setInstances(List<Instance> inst) {
         this.instances = inst;
    }
    
    /** Methdo to set the parent node
     * 
     * @param parent
     */
    public void setParent(Node parent){
    	this.parent = parent;
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
    public void setTargeAttribute(int targetAttribute){
    	this.targetAttribute = targetAttribute;
    }
    /**
     * Method to get target attribute
     *
     * @return the target attribute
     */
    public int getTargetAttribute(){
    	return this.targetAttribute;
    }
}

