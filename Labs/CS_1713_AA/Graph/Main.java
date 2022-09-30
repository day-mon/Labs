package Labs.CS_1713_AA.Graph;

import Labs.CS_1713_AA.Graph.MyGraph;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main
{
      public static void main(String[] args)
      {

            var list = new Stack<MyGraph>();
            var scanner = new Scanner(System.in);

            for (var c = 0; c < 6; c++)
            {
                  switch (c)
                  {
                        case 0:
                              list.add(new MyGraph(4, 3));
                              var G1 = list.get(c);

                              G1.setAdjMatrix(0, 1, 1);
                              G1.setAdjMatrix(1, 0, 1);
                              G1.setAdjMatrix(1, 2, 1);
                              G1.setAdjMatrix(2, 1, 1);
                              G1.setAdjMatrix(1, 3, 1);
                              G1.setAdjMatrix(3, 1, 1);
                              break;

                        case 1:
                              list.add(new MyGraph(6, 10));
                              var G2 = list.get(c);

                              G2.setAdjMatrix(0, 1, 1);
                              G2.setAdjMatrix(1, 0, 1);
                              G2.setAdjMatrix(0, 2, 1);
                              G2.setAdjMatrix(2, 0, 1);
                              G2.setAdjMatrix(0, 3, 1);
                              G2.setAdjMatrix(3, 0, 1);
                              G2.setAdjMatrix(0, 4, 1);
                              G2.setAdjMatrix(4, 0, 1);
                              G2.setAdjMatrix(1, 2, 1);
                              G2.setAdjMatrix(2, 1, 1);
                              G2.setAdjMatrix(2, 4, 1);
                              G2.setAdjMatrix(4, 2, 1);
                              G2.setAdjMatrix(2, 5, 1);
                              G2.setAdjMatrix(5, 2, 1);
                              G2.setAdjMatrix(3, 4, 1);
                              G2.setAdjMatrix(4, 3, 1);
                              G2.setAdjMatrix(3, 5, 1);
                              G2.setAdjMatrix(5, 3, 1);
                              G2.setAdjMatrix(4, 5, 1);
                              G2.setAdjMatrix(5, 4, 1);
                              break;

                        case 2:
                              list.add(new MyGraph(13, 17));
                              var G3 = list.get(c);

                              G3.setAdjMatrix(0, 1, 1);
                              G3.setAdjMatrix(1, 0, 1);
                              G3.setAdjMatrix(0, 5, 1);
                              G3.setAdjMatrix(5, 0, 1);
                              G3.setAdjMatrix(0, 6, 1);
                              G3.setAdjMatrix(6, 0, 1);
                              G3.setAdjMatrix(0, 2, 1);
                              G3.setAdjMatrix(2, 0, 1);
                              G3.setAdjMatrix(2, 6, 1);
                              G3.setAdjMatrix(6, 2, 1);
                              G3.setAdjMatrix(4, 3, 1);
                              G3.setAdjMatrix(3, 4, 1);
                              G3.setAdjMatrix(3, 5, 1);
                              G3.setAdjMatrix(5, 3, 1);
                              G3.setAdjMatrix(4, 5, 1);
                              G3.setAdjMatrix(5, 4, 1);
                              G3.setAdjMatrix(4, 6, 1);
                              G3.setAdjMatrix(6, 4, 1);
                              G3.setAdjMatrix(6, 7, 1);
                              G3.setAdjMatrix(7, 6, 1);
                              G3.setAdjMatrix(6, 11, 1);
                              G3.setAdjMatrix(11, 6, 1);
                              G3.setAdjMatrix(6, 9, 1);
                              G3.setAdjMatrix(9, 6, 1);
                              G3.setAdjMatrix(7, 8, 1);
                              G3.setAdjMatrix(8, 7, 1);
                              G3.setAdjMatrix(9, 10, 1);
                              G3.setAdjMatrix(10, 9, 1);
                              G3.setAdjMatrix(9, 11, 1);
                              G3.setAdjMatrix(11, 9, 1);
                              G3.setAdjMatrix(9, 12, 1);
                              G3.setAdjMatrix(12, 9, 1);
                              G3.setAdjMatrix(11, 12, 1);
                              G3.setAdjMatrix(12, 11, 1);
                              break;

                        case 3:
                              list.add(new MyGraph(5, 4));
                              var G4 = list.get(c);

                              G4.setAdjMatrix(0, 1, 1);
                              G4.setAdjMatrix(1, 0, 1);
                              G4.setAdjMatrix(0, 2, 1);
                              G4.setAdjMatrix(2, 0, 1);
                              G4.setAdjMatrix(1, 2, 1);
                              G4.setAdjMatrix(2, 1, 1);
                              G4.setAdjMatrix(3, 4, 1);
                              G4.setAdjMatrix(4, 3, 1);
                              break;

                        case 4:
                              list.add(new MyGraph(10, 14));
                              var G5 = list.get(c);

                              G5.setAdjMatrix(0, 3, 1);
                              G5.setAdjMatrix(3, 0, 1);
                              G5.setAdjMatrix(2, 2, 1);
                              G5.setAdjMatrix(3, 5, 1);
                              G5.setAdjMatrix(5, 3, 1);
                              G5.setAdjMatrix(8, 9, 1);
                              G5.setAdjMatrix(9, 8, 1);
                              G5.setAdjMatrix(0, 5, 1);
                              G5.setAdjMatrix(5, 0, 1);
                              G5.setAdjMatrix(8, 3, 1);
                              G5.setAdjMatrix(3, 8, 1);
                              G5.setAdjMatrix(5, 9, 1);
                              G5.setAdjMatrix(9, 5, 1);
                              G5.setAdjMatrix(3, 9, 1);
                              G5.setAdjMatrix(9, 3, 1);
                              G5.setAdjMatrix(8, 3, 1);
                              G5.setAdjMatrix(3, 8, 1);
                              G5.setAdjMatrix(1, 7, 1);
                              G5.setAdjMatrix(7, 1, 1);
                              G5.setAdjMatrix(4, 6, 1);
                              G5.setAdjMatrix(6, 4, 1);
                              G5.setAdjMatrix(6, 7, 1);
                              G5.setAdjMatrix(7, 6, 1);
                              G5.setAdjMatrix(1, 4, 1);
                              G5.setAdjMatrix(4, 1, 1);
                              G5.setAdjMatrix(1, 6, 1);
                              G5.setAdjMatrix(6, 1, 1);
                              G5.setAdjMatrix(4, 7, 1);
                              G5.setAdjMatrix(7, 4, 1);
                              break;
                  }
            }

            var size = list.size();
            for (var i = 0; i < size; i++)
            {
                  System.out.printf("Would you like to see Graph #%d (Yes/No): ", i+1);
                  var ans = scanner.nextLine();

                  if (!ans.equalsIgnoreCase("Yes"))
                  {
                        break;
                  }

                  var item = list.get(i);



                  item.printGraph();
                  item.getNeighbors(1);
                  item.BFS();
                  item.DFS();
                  item.artPts();

                  System.out.println("=========================================");
            }

            System.out.println("Finished");
      }
}
