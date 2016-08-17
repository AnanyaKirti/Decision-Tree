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

	// children list
	private List<Node> children;

	// parent node
	private Node parent;

	// Instance list
	private List<Instance> instances;

	int targetAttributeValue;
	boolean flag = false;

	/**
	 * 
	 * TODO add features to this.
	 */
	public Node() {
		super();
	}

	/**
	 * Initializing Node by giving List of Attribute and Instances we can set
	 * children and parent using given functions
	 * 
	 * @param attr
	 *            attribute list as integer
	 * @param inst
	 *            Instance list
	 */
	public Node(List<Instance> instances, List<Integer> attributeAvailable) {
		this();
		this.parent = null;
		this.targetAttribute = -1;
		// this.attributeAvailable = attributeAvailable;
		setAttribute(attributeAvailable);
		this.instances = instances;
	}

	/**
	 * to get children array list
	 * 
	 * @return List of children
	 */
	public List<Node> getChildren() {
		if (this.children == null) {
			return new ArrayList<Node>();
		}
		return this.children;
	}

	/**
	 * function to set the children
	 * 
	 * @param children
	 */
	public void setChildren(List<Node> children) {
		this.children = children;
	}

	/**
	 * Function to get the number of children at a node.
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

	/**
	 * addChild to children list
	 * 
	 * @param child
	 */
	public void addChild(Node child) {
		if (children == null) {
			children = new ArrayList<Node>();
		}
		children.add(child);
	}

	/**
	 * Function to get the lasChildren of the Node.
	 * 
	 * @return
	 */
	public Node getLastChild() {
		return children.get(children.size() - 1);
	}

	/**
	 * get attribute list for this node
	 * 
	 * @return returns the data associated with the list.
	 */
	public List<Integer> getAttribute() {
		return this.attributeAvailable;
	}

	/**
	 * set attribute list of integers
	 * 
	 * @param attr
	 *            sets the attribute
	 */
	public void setAttribute(List<Integer> attribute) {
		List<Integer> dummy = new ArrayList<Integer>();
		for (Integer integer : attribute) {
			dummy.add(new Integer(integer));
		}
		this.attributeAvailable = dummy;
	}

	/**
	 * Method to get Instances in current node
	 * 
	 * @return Instances associated with the node.
	 */
	public List<Instance> getInstances() {
		return this.instances;
	}

	/**
	 * Method to set instances in current node
	 * 
	 * @param Instance
	 *            list
	 */
	public void setInstances(List<Instance> inst) {
		this.instances = inst;
	}

	/**
	 * Method to set the parent node
	 * 
	 * @param parent
	 */
	public void setParent(Node parent) {
		this.parent = parent;
	}

	/**
	 * Method to get the parent node.
	 */
	public Node getParent() {
		return this.parent;
	}

	/**
	 * Method to set target attribute
	 * 
	 * @param targetAttr
	 */
	public void setTargeAttribute(int targetAttribute) {
		this.targetAttribute = targetAttribute;
	}

	/**
	 * Method to get target attribute
	 *
	 * @return the target attribute
	 */
	public int getTargetAttribute() {
		return this.targetAttribute;
	}

	/**
	 * Function to check if the node has children.
	 * 
	 * @return 0 if no children, 1 if has children.
	 */
	public int hasChildren() {
		if (children == null) {
			return 0;
		}
		return 1;
	}

	/**
	 * Returns the best attribute at the node, which is used as the splitting
	 * attribute.
	 * 
	 * @return Integer of the targetAttribute used.
	 */
	public int getTargetAttributeValue() {
		return targetAttributeValue;
	}

	/**
	 * Function to set the target attribute value, which is used as the
	 * splitting attribute at the node
	 * 
	 * @param i
	 *            The best attribute at i
	 */
	public void setTargetAttributeValue(int i) {
		this.targetAttributeValue = i;
	}

	/**
	 * Function to set the flag of a node, to be used in Pruning
	 * 
	 * @param state
	 */
	public void setFlag(boolean state) {
		flag = state;
	}

	/**
	 * Function to delete all the childern of a node
	 */
	public void deleteChildren() {
		this.children = null;
	}
}
