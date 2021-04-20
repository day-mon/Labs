package Interfaces;

import java.util.Iterator;

public interface BinaryTree<T>
{

    public void setTree(T rootData);

    public void setTree(T rootData, BinaryTree<T> leftSubtree, BinaryTree<T> rightSubtree);

    public T getRootData();

    public BinaryTree<T> getLeftSubtree();

    public BinaryTree<T> getRightSubtree();

    public int getHeight();

    public int getNumberOfNodes();

    public boolean isEmpty();

    public Iterator<T> preorderTraversal();

    public Iterator<T> postorderTraversal();

    public Iterator<T> inorderTraversal();
    //TODO: Print tree
}