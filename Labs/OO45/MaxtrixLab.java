package OO45;

import javax.swing.text.Style;
import java.util.Scanner;

public class MaxtrixLab {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        double m1[][];
        double m2[][];

        System.out.println("How many rows & columns (separate by space!):  ");
        int rows = in.nextInt();
        int cols = in.nextInt();


        m1 = new double[rows][cols];
        m2 = new double[rows][cols];

        System.out.println("Input your elements for matrix 1: ");
        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < cols; k++) {
                 m1[i][k] = in.nextDouble();
            }
        }

        System.out.println("Input your elements for matrix 2: ");
        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < cols; k++) {
                m2[i][k] = in.nextDouble();
            }
        }

        double[][] sum = add(m1, m2);

            for (int i = 0; i < rows; i++) {
                for (int k = 0; k < cols; k++) {
                    System.out.print(sum[i][k] + " ");
                }
                // will create a new space for each row.
                System.out.println(" ");
            }
    }

    public static double[][] add(double m1[][], double m2[][]) {
        // first road block: not adding first column and row
        // fixed: on line 31, i init m1 twice so it wouldnt add anything.

        double[][] result = m1;
        // set result to m1 because it'll have the same dimensions.


        /**
         * Ex on how this works:
         *   using 2 x 2
         *      [0][0] + [0][0]
         *      [0][1] + [0][1]
         *      [1][0] + [1][0]
         *      [1][1] + [1][1]
         *   done
         */
        for (int i = 0; i < m1.length; i++) {
            // this will be for rows
            for (int k = 0; k < m1[0].length; k++) {
                // this will be for cols
                result[i][k] = m1[i][k] + m2[i][k];
            }
        }

        return result;
    }
}
