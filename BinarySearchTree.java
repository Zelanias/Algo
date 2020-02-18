// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
import java.lang.Exception;
import java.util.*;
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new NullPointerException("Tree is empty");
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty( ) )
            throw new NullPointerException("Tree is empty");
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }

    public int nodeCount(){
       return nodeCounter(root); 
    }
    //helper function for node count
    //recursive function that adds up each node  
    private int nodeCounter(BinaryNode<AnyType> n){
        if(n==null){
            return 0;
        }
        return 1+nodeCounter(n.left)+nodeCounter(n.right);
    }
    //A full tree has every level full, can have a linear function that relates height to node count
    public boolean isFull(){
        int ht = height(root);
        int nc = nodeCount();
        double expected = Math.pow(2,ht) - 1;
        if(nc == expected){
            return true;
        }
        return false;
    }

    public boolean compareStructure(BinarySearchTree<AnyType> second){
        return compareStructure(root,second.root);
    }
    //helper function for comparestructure
    private boolean compareStructure(BinaryNode<AnyType> a,BinaryNode<AnyType> b){
        if(a == null && b == null){
            return true;
        }else if(a == null || b == null){
            return false;
        }else{
            return compareStructure(a.left,b.left) && compareStructure(a.right,b.right);
        }
    }
    public boolean equals(BinarySearchTree<AnyType> t){
        return equalsH(root,t.root);
    }
    //helper function for equals
    private boolean equalsH(BinaryNode<AnyType> fr,BinaryNode<AnyType> scnd){
        if(fr.element != scnd.element){
            return false;
        }
        else if(fr.left == null && fr.right == null && scnd.left == null && scnd.right == null){
                    return true;
            }
        else if(fr.left == null && fr.right != null && scnd.left == null && scnd.right != null){
                return equalsH(fr.right, scnd.right);
        }
        else if(fr.right == null && fr.left != null && scnd.right == null && scnd.left != null){
                return equalsH(fr.left, scnd.left);
            }
        return false;
    }
    //copying 
    public BinarySearchTree<AnyType> copy(){
        BinarySearchTree<AnyType> temp = new BinarySearchTree<>();
        //first checkinf for null case
        if(root.element == null){
            return null;
        }else{
            temp.root = copyH(root);
            return temp;
        }
        
    }
    //helper function if not null
    private BinaryNode<AnyType> copyH(BinaryNode<AnyType> n){
        if(n == null){
            return null;
        }
        BinaryNode<AnyType> co = new BinaryNode<AnyType>(n.element);
        co.left = copyH(n.left);
        co.right = copyH(n.right);
        return co;
    }
    //mirror function
    public BinarySearchTree<AnyType> mirror(){
        BinarySearchTree<AnyType> temp = new BinarySearchTree<>();
        //null case
        if(root == null){
            return null;
        }
        temp.root = root;
        temp.root = mirrorH(root);
        return temp;
    }
    //helper function
    private BinaryNode<AnyType> mirrorH(BinaryNode<AnyType> n){
        if(n == null){
            return null;
        }
        BinaryNode<AnyType> re = new BinaryNode<AnyType>(n.element);
        re.left = mirrorH(n.right);
        re.right = mirrorH(n.left);
        return re;
    }

    public boolean isMirror(BinarySearchTree<AnyType> scnd){
        return isMirrorH(root,scnd.root);
    }
    public boolean isMirrorH(BinaryNode<AnyType> fr, BinaryNode<AnyType> scnd){
        if(fr.element != scnd.element){
            return false;
        }
        else if(fr.left == null && fr.right == null && scnd.left == null && scnd.right == null){
                    return true;
            }
        else if(fr.left == null && fr.right != null && scnd.left != null && scnd.right == null){
                return isMirrorH(fr.right, scnd.left);
        }
        else if(fr.right == null && fr.left != null && scnd.right != null && scnd.left == null){
                return isMirrorH(fr.left, scnd.right);
            }
        return false;
    }

    public void rotateRight(AnyType v){
        //node to rotate
        BinaryNode<AnyType> s = find(v,root);
        //checks if its null and if it can rotate
        if(s != null && s.left != null){
            //if root has to rotate and set root to new value
            if(s.element.equals(root.element)){
                BinaryNode<AnyType> temp = s.left;
                s.left = temp.right;
                temp.right = s;
                root = temp;
            }else{
                //else find parent and rotate and setting new parent child relation
                BinaryNode<AnyType> parent = findParent(v, root);
                BinaryNode<AnyType> temp = s.left;
                s.left = temp.right;
                temp.right = s;
                if((parent.left != null) && parent.left.element.equals(v)){
                    parent.left = temp;
                }else{
                    parent.right = temp;
                }
            }
        }else{
            //else throw exception
            throw new NullPointerException("Can't rotate right if no left child");
        }

    }
    //same as rotate right but left
    public void rotateLeft(AnyType v){
        BinaryNode<AnyType> s = find(v,root);
        if(s != null && s.right != null){
            if(s.element.equals(root.element)){
                BinaryNode<AnyType> temp = s.right;
                s.right = temp.left;
                temp.left = s;
                root = temp;
            }else{
                BinaryNode<AnyType> parent = findParent(v, root);
                BinaryNode<AnyType> temp = s.right;
                s.right = temp.left;
                temp.left = s;
                if((parent.left != null) && parent.left.element.equals(v)){
                    parent.left = temp;
                }else{
                    parent.right = temp;
                }
            }
        }else{
            throw new NullPointerException("Can't rotate left if no right child");
        }

    }
    //function to find node of value
    private BinaryNode<AnyType> find(AnyType v, BinaryNode<AnyType> n){
        //null case
        if(n == null){
            return null;
        }
        //find comprison value
        int c = v.compareTo(n.element);
        if(c < 0){
            //less then left
            return find(v,n.left);
        }else if(c > 0){
            //greater then right
            return find(v,n.right);
        }
        return n;
        
    }
    //same as find but returns the parent instead
    private BinaryNode<AnyType> findParent(AnyType v, BinaryNode<AnyType> n){
        if(n == null){
            return null;
        }
        int c = v.compareTo(n.element);
        if((n.left!=null) && (n.left.element==v)){
            return n;
        }else if((n.right!=null) && (n.right.element == v)){
            return n;
        }else if(c < 0){
            return findParent(v,n.left);
        }else if(c > 0){
            return findParent(v,n.right);
        }
        return n;
    }
    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }

    public void printLevels(){
        printLevels(root);
      /** The tree root. */
    }
    private void printLevels(BinaryNode<AnyType> n){
        if(n == null){
            return;
        }
        Queue<BinaryNode<AnyType>> node = new LinkedList<>();
        node.add(n);
        BinaryNode<AnyType> temp;
        while(!node.isEmpty()){
            temp = node.poll();
                if(temp.left != null){
                    node.add(temp.left);
                }if(temp.right != null){
                    node.add(temp.right);
                }  
                System.out.print(temp.element + " ");
        }
    }
    private BinaryNode<AnyType> root;


        // Test program
    public static void main( String [ ] args )
    {
        //setting up 3 trees
        BinarySearchTree<Integer> t = new BinarySearchTree<>( );
        BinarySearchTree<Integer> r = new BinarySearchTree<>( );
        BinarySearchTree<Integer> f = new BinarySearchTree<>( );

        //t and r are the same
        final int NUMS = 10;
        final int GAP  = 1;
        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS ){
            t.insert( i );
        }
        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS ){
            r.insert( i );
        }
        //f is for testing rotations
        f.insert(0);
        f.insert(2);
        f.insert(1);
        f.insert(-2);
        f.insert(-1);
        //Node count
        int count = t.nodeCount();
        System.out.println("Node count: " + count);
        //if full test
        boolean state = t.isFull();
        System.out.println("Fullness = " + state);
        //compare structure test
        state = t.compareStructure(r);
        System.out.println("Structure state = " + state);
        //testing equals
        System.out.println("Equal? " + t.equals(r));

        r = t.copy();
        System.out.println("Testing copy tree");
        r.printTree();
        //setting up mirror test
        BinarySearchTree<Integer> o = t.mirror();
        o = t.mirror();
        System.out.println("Mirrored tree");
        o.printTree();
        System.out.println("Is mirror? " + t.isMirror(o));
        //testing rotations
        System.out.println("Original");
        f.printLevels();
        System.out.println();
        f.rotateRight(0);
        System.out.println("Rotate right");
        f.printLevels();
        f.rotateLeft(0);
        System.out.println("\nRotate Left");
        f.printLevels();
        System.out.println();
        f.rotateRight(2);
        f.rotateLeft(-2);
        System.out.println("Back to orginal");
        f.printLevels();

        
    }
}
