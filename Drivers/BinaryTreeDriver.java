package Drivers;

import Labs.OO48.BinaryTree;

public class BinaryTreeDriver
{
    public static void main(String[] args)
    {
        long START_TIME = System.currentTimeMillis();
        long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        BinaryTree<Character> driver = new BinaryTree<>('A', 4);
        for (int i = 1, j = 66; i < 8; i++, j += 2)
        {
            char left = (char) j;
            char right = (char) ((char) j + 1);
            driver.setLeft(i, left);
            driver.setRight(i, right);
        }
        driver.printLevelOrderTraversal();

        System.out.println("Root (Should be [A]): " + driver.getRoot());
        System.out.println("Height (Should be [4]): " + driver.getHeight());
        System.out.println("Number of nodes (Should be [15]): " + driver.getNumberOfNodes());
        System.out.println("Empty (Should be [false]): " + driver.isEmpty());

        long DELTA_TIME = System.currentTimeMillis() - START_TIME;
        double afterUsedMem = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576.0;
        String usage = afterUsedMem > 1 ? " bytes" : " byte";
        System.out.printf("\n----------- After runtime Performance -----------" +
                "\nMemory usage (bytes): %.3f %s" +
                "\nRun-time (ms): %d ms", afterUsedMem, usage, DELTA_TIME);
    }
}
