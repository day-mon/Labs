package Interfaces;

public interface BinaryTreeFinal<E>
{
    public void setRoot(E element);

    public boolean setLeft(int parent, E element);

    public boolean setRight(int parent, E element);

    public int getHeight();

    public int getNumberOfNodes();

    public boolean isEmpty();

    public void printLevelOrderTraversal();
}
