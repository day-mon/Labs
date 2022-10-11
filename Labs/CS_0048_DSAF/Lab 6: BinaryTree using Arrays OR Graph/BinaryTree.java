public class BinaryTree<E> implements BinaryTreeFinal<E>
{
    private int size;
    private Object[] elements;
    private final int DEFAULT_HEIGHT = 5;

    public BinaryTree()
    {
        elements = new Object[(int)Math.pow(2, DEFAULT_HEIGHT)];
        size = 0;
    }

    public BinaryTree(int height)
    {
        elements = new Object[(int)Math.pow(2, height)];
    }

    public BinaryTree(E root, int height)
    {
        elements = new Object[(int)Math.pow(2, height)];
        elements[1] = (Object)root;
        size++;
    }

    @SuppressWarnings("unchecked")
    public E getRoot()
    {
        return (E)elements[1] != null ? (E)elements[1] : null;
    }

    @Override
    public void setRoot(E element)
    {
        // Nothing is in root. If there is you don't need to increase size
        if (elements[1] == null) size++;
        elements[1] = (Object)element;

    }

    @Override
    public boolean setLeft(int parent, E element)
    {
        if (parent < 0 || parent > size || elements[parent] == null)
        {
            return false;
        }
        else
        {
            if (elements[2 * parent] == null)
            {
                size++;
            }
            elements[2 * parent] = (Object)element;
            return true;
        }
    }

    @Override
    public boolean setRight(int parent, E element)
    {
        if (parent < 0 || parent > size || elements[parent] == null)
        {
            return false;
        }
        else
        {
            if (elements[2 * parent + 1] == null)
            {
                size++;
            }
            elements[parent * 2 + 1] = (Object)element;
            return true;
        }
    }

    @Override
    public int getHeight()
    {
        return (int)(Math.log(elements.length)/Math.log(2));
    }

    @Override
    public int getNumberOfNodes()
    {
        return size;
    }

    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    public void printLevelOrderTraversal()
    {
        for (Object ele : elements)
        {
            System.out.println(ele);
        }
    }
}
