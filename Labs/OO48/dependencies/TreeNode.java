package Labs.OO48.dependencies;

import Labs.OO48.LinkedBinaryTree;

public class TreeNode<T>
{
    private T data;
    private TreeNode<T> left;
    private TreeNode<T> right;

    public TreeNode(T data)
    {
        this.data = data;
    }

    public TreeNode(T data, TreeNode<T> left, TreeNode<T> right)
    {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

    public TreeNode<T> getLeft()
    {
        return left;
    }

    public void setLeft(TreeNode<T> left)
    {
        this.left = left;
    }

    public TreeNode<T> getRight()
    {
        return right;
    }

    public void setRight(TreeNode<T> right)
    {
        this.right = right;
    }

    public boolean hasLeft()
    {
        return left != null;
    }

    public boolean hasRight()
    {
        return right != null;
    }

    public boolean isLeaf()
    {
        return left == null && right == null;
    }

    public int getHeight()
    {
        return 1 + Math.max((left == null) ? 0 : left.getHeight(), (right == null) ? 0 : right.getHeight());
    }

    @SuppressWarnings("unchecked")
    public int getNumberOfDescendents()
    {
        return (new LinkedBinaryTree<T>((T) left)).getNumberOfNodes() + (new LinkedBinaryTree<T>((T) right)).getNumberOfNodes();
    }
}
