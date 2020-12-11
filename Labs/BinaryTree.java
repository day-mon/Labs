import java.util.Iterator;
import java.util.NavigableMap;
import Labs.TreeNode;


/**
 * In recurrsion everything needs a base case,
 * if you get stuck trying to figure out where to start,
 * start there.
 */

public class BinaryTree<T extends Comparable> {
    private TreeNode<T> root;
    private int size;

    public BinaryTree() {
        this.root = null;
        this.size = 0;
    }

    public BinaryTree(TreeNode<T> root) {
        this.root = root;
        this.size = 1;
    }

    public BinaryTree(T value) {
        this.root = new TreeNode<T>(value);
        this.size = 1;
    }

    public boolean insert(TreeNode<T> node) {
        if (this.root == null) {
            this.root = node;
            size++;
            return true;
        }

        rInsert(root, node);
        return true;
    }


    public boolean search(T obj)  {
       return rSearch(root, obj);
    }

    private boolean rSearch(TreeNode<T> current, T obj) {
        // first case item you're searching for it the root
        if (this.root.value == obj) return true;
        if (root.compareTo(obj) <= 0) /*go left now*/ {
            if (current.left.equals(obj)) {
                return true;
            } else {
                rSearch(current.left, obj);
            }
        } else {
            if (root.compareTo(obj) > 0) /* go right now */ {
                if(current.right.equals(obj)) {
                    return true;
                } else {
                    rSearch(current.right, obj);
                }
            }
        }
    }


    private boolean rInsert(TreeNode<T> node, TreeNode<T> current) {
        
        if (current.compareTo(node.value) >= 0) {
            if (current.left != null) {
                current.left = node;
                return true;
            } else {
                rInsert(current.left, node);
            }
        } else {
            if (current.compareTo(node.value) < 0) {
                current.right  = node;
                return true;
            } else {
                rInsert(current.right, node);
            }
        }
        return false;
    }


    public boolean delete(T obj) {};

    private boolean rDelete(T obj, TreeNode<T> current) {
        //Base Case: Tree is empty;
        if (this.root == null) throw new E
    }


    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


}