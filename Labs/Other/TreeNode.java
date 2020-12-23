package Labs.Other;


public class TreeNode<T extends Comparable> implements Comparable<T> {

    protected TreeNode<T> parent, left, right;
    protected T value;

    public TreeNode(T value) {
        this.value = value;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    public TreeNode(T value, TreeNode<T> left, TreeNode<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.parent = null;
    }

    public TreeNode(T value, TreeNode<T> left, TreeNode<T> right, TreeNode<T> parent) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    /**
     * *************** GETTERS ***************
     */

    public TreeNode<T> getLeft() {
        return left;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public T getValue() {
        return value;
    }

    /**
     * *************** SETTERS ***************
     */

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    @Override
    public int compareTo(T o) {
        return this.value.compareTo(o);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((left == null) ? 0 : left.hashCode());
        result = prime * result + ((parent == null) ? 0 : parent.hashCode());
        result = prime * result + ((right == null) ? 0 : right.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TreeNode other = (TreeNode) obj;
        if (left == null) {
            if (other.left != null)
                return false;
        } else if (!left.equals(other.left))
            return false;
        if (parent == null) {
            if (other.parent != null)
                return false;
        } else if (!parent.equals(other.parent))
            return false;
        if (right == null) {
            if (other.right != null)
                return false;
        } else if (!right.equals(other.right))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TreeNode [left=" + left + ", parent=" + parent + ", right=" + right + ", value=" + value + "]";
    }

}
