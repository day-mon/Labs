
import Interfaces.BinaryTree;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class LinkedBinaryTree<T> implements BinaryTree<T>
{
    private TreeNode<T> root;

    public LinkedBinaryTree()
    {
    }

    public LinkedBinaryTree(T rootData)
    {
        this.setTree(rootData);
    }


    protected LinkedBinaryTree(TreeNode<T> root)
    {
        this.root = root;
    }

    @Override
    public void setTree(T rootData)
    {
        this.root = new TreeNode<T>(rootData);
    }

    @Override
    public T getRootData()
    {
        return root.getData();
    }

    @Override
    public void setTree(T rootData, BinaryTree<T> leftSubtree, BinaryTree<T> rightSubtree)
    {
        this.root = new TreeNode<T>(rootData);
        if (leftSubtree != null)
        {
            if (!(leftSubtree instanceof LinkedBinaryTree))
                throw new IllegalArgumentException("Cannot connect non-LinkedBinaryTree to LinkedBinaryTree");
            root.setLeft(((LinkedBinaryTree<T>) leftSubtree).root);
        }
        if (rightSubtree != null)
        {
            if (!(rightSubtree instanceof LinkedBinaryTree))
                throw new IllegalArgumentException("Cannot connect non-LinkedBinaryTree to LinkedBinaryTree");
            root.setRight(((LinkedBinaryTree<T>) rightSubtree).root);
        }
    }

    @Override
    public BinaryTree<T> getLeftSubtree()
    {
        return new LinkedBinaryTree<T>(root.getLeft());
    }

    @Override
    public BinaryTree<T> getRightSubtree()
    {
        return new LinkedBinaryTree<T>(root.getRight());
    }

    @Override
    public int getHeight()
    {
        return root.getHeight();
    }

    @Override
    public int getNumberOfNodes()
    {
        if (root == null)
        {
            return 0;

        }
        return 1 + root.getNumberOfDescendents();
    }

    @Override
    public boolean isEmpty()
    {
        return root == null;
    }

    @Override
    public Iterator<T> preorderTraversal()
    {
        Stack<TreeNode<T>> stack = new Stack<>();
        if (root != null)
        {
            stack.push(root);
        }
        return new Iterator<T>()
        {
            @Override
            public boolean hasNext()
            {
                return !stack.isEmpty();
            }

            @Override
            public T next()
            {
                if (hasNext())
                {
                    TreeNode<T> next = stack.pop();
                    if (next.getRight() != null)
                    {
                        stack.push(next.getRight());

                    }
                    if (next.getLeft() != null)
                    {
                        stack.push(next.getLeft());
                    }
                    return next.getData();
                }
                else
                {
                    throw new NoSuchElementException();
                }
            }

        };

    }

    @Override
    public Iterator<T> postorderTraversal()
    {
        Stack<TreeNode<T>> treeNodeStack = new Stack<>();

        return new Iterator<T>()
        {
            TreeNode<T> cur = root;

            @Override
            public boolean hasNext()
            {
                return (!treeNodeStack.isEmpty()) || (cur != null);
            }

            @Override
            public T next()
            {
                if (hasNext())
                {
                    TreeNode<T> left = null;
                    TreeNode<T> next = null;

                    while (cur != null)
                    {
                        treeNodeStack.push(cur);
                        left = cur.getLeft();
                        if (left == null)
                        {
                            cur = cur.getRight();
                        }
                        else
                        {
                            cur = cur.getLeft();
                        }

                    }
                    if (!treeNodeStack.isEmpty())
                    {
                        next = treeNodeStack.pop();

                        TreeNode<T> parent = null;
                        if (!treeNodeStack.isEmpty())
                        {
                            parent = treeNodeStack.peek();
                            if (next == parent.getLeft())
                            {
                                cur = parent.getRight();
                            }
                            else
                            {
                                cur = null;
                            }
                        }
                        else
                        {
                            cur = null;
                        }
                    }
                    else
                    {
                        throw new NoSuchElementException();
                    }
                    return next.getData();
                }
                throw new NoSuchElementException();
            }
        };
    }

    @Override
    public Iterator<T> inorderTraversal()
    {
        Stack<TreeNode<T>> stack = new Stack<>();
        return new Iterator<T>()
        {
            TreeNode<T> current = root;

            @Override
            public boolean hasNext()
            {
                return (!stack.isEmpty() || (current != null));


            }

            @Override
            public T next()
            {
                if (hasNext())
                {
                    TreeNode<T> next = null;

                    while (current != null)
                    {
                        stack.push(current);
                        current = current.getLeft();
                    }

                    if (!stack.isEmpty())
                    {
                        next = stack.pop();
                        current = next.getRight();

                    }
                    else
                    {
                        throw new NoSuchElementException();
                    }
                    return next.getData();
                }
                else
                {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}
