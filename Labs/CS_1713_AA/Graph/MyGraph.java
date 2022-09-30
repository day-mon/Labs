package Labs.CS_1713_AA.Graph;

import java.sql.Array;
import java.util.*;

public class MyGraph
{
      private int numVertices;
      private int numEdges;
      private int[][] adjMatrix;
      private List<Integer> valList = new ArrayList<>();
      private int count;

      public MyGraph(int vertices, int edges)
      {
            this.numEdges = edges;
            this.numVertices = vertices;
            this.adjMatrix = new int[numVertices][numVertices];


      }

      public void setCount(int count)
      {
            this.count = count;
      }

      public void setNumEdges(int numEdges)
      {
            this.numEdges = numEdges;
      }

      public void setNumVertices(int numVertices)
      {
            this.numVertices = numVertices;
      }

      public void setValList(int index, int entry)
      {
            var size = valList.size();

            if (index > size || index < 0)
            {
                  throw new IndexOutOfBoundsException();
            }

            valList.set(index, entry);
      }

      public void getNeighbors(int vertex)
      {
            System.out.print("Neighbors of 2nd node are: ");
            var neighbors = new ArrayList<Integer>();
            for (var i = 0; i < adjMatrix.length; i++)
            {
                  if (adjMatrix[vertex][i] == 1)
                  {
                        neighbors.add(i);
                  }
            }

            neighbors.forEach(ele -> System.out.printf("%d ", ele));
      }


      public void setAdjMatrix(int row, int column, int element)
      {
            adjMatrix[row][column] = element;
      }

      public int getCount()
      {
            return count;
      }

      public int getNumEdges()
      {
            return numEdges;
      }

      public int getNumVertices()
      {
            return numVertices;
      }

      public int getAdjMatrix(int row, int column)
      {
            return adjMatrix[row][column];
      }

      public int getVal(int index)
      {
            return valList.get(index);
      }

      public void printGraph()
      {

            System.out.println("Adjacency Matrix: ");
            for (int i = 0; i < numVertices; i++)
            {
                  System.out.print("[ ");
                  for (int c = 0; c < numVertices; c++)
                  {
                        System.out.printf("%d ", adjMatrix[i][c]);
                  }
                  System.out.println("]");
            }
            System.out.println();

      }

      public void BFS()
      {
            doSearch("bfs");
      }


      private void doSearch(String type)
      {

            if (!type.equals("bfs") && !type.equals("dfs") && !type.equals("artPts")) return;


            System.out.print("\nCarrying out " + type.toUpperCase());

            valList = new ArrayList<>(Collections.nCopies(numVertices, 0));
            count = 0;

            for (var k = 0; k < numVertices; k++)
            {
                  var element = valList.get(k);

                  if (element == 0)
                  {
                        System.out.println();
                        if (type.equals("bfs"))
                        {
                              System.out.print("Connected Component:");
                              visitBFS(k);
                        }
                        else if (type.equals("dfs"))
                        {
                              System.out.print("Connected Component:");
                              visitDFS(k);
                        }
                        else
                        {
                              visitDFSArtPts(k);
                        }
                  }
            }
            System.out.println();
      }

      private void visitBFS(int k)
      {
            count += 1;
            valList.set(k, count);
            var queue = new LinkedList<Integer>();
            queue.offer(k);


            while (!queue.isEmpty())
            {
                  var row = queue.poll();

                  for (var col = 0; col < numVertices; col++)
                  {

                        var visited = valList.get(col) != 0;
                        var element = adjMatrix[row][col];

                        if (element == 1 && !visited)
                        {
                              count += 1;
                              valList.set(col, count);
                              queue.offer(col);

                              System.out.printf(" <%d, %d>", row, col);

                        }
                  }
            }
      }


      public void DFS()
      {
            doSearch("dfs");
      }

      private void visitDFS(int k)
      {
            count += 1;
            valList.set(k, count);

            for (var w = 1; w < numVertices; w++)
            {
                  var element = adjMatrix[k][w];

                  if (element == 1)
                  {
                        var valElement = valList.get(w);

                        if (valElement == 0)
                        {
                              System.out.printf(" <%d, %d>", k, w);
                              visitDFS(w);
                        }
                  }
            }
      }

      public void artPts()
      {
            doSearch("artPts");
      }

      private int visitDFSArtPts(int k)
      {
            count += 1;
            valList.set(k, count);
            var min = count;

            for (var w = 1; w < numVertices; w++)
            {
                  var element = valList.get(w);

                  if (element == 0)
                  {
                        var m = visitDFSArtPts(w);
                        if (m < min)
                        {
                              min = m;
                        }

                        var item = valList.get(k);

                        if (m >= item)
                        {
                              System.out.printf("Articulation Point Found: <%d>\n", k);
                        }
                  }
                  else if (element < min)
                  {
                        min = element;
                  }
            }

            return min;
      }

}
