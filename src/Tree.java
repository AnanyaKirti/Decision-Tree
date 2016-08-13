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

