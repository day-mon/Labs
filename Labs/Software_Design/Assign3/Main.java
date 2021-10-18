package Labs.Software_Design.Assign3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main
{
      private static final File file = new File("bible.txt");
      private static List<AssignObj> assignObjs = new ArrayList<>();


      public static void main(String[] args)
      {
            var list = Arrays.asList(args);

            var mode = list.get(0);


            if (!mode.equalsIgnoreCase("m") && !mode.equalsIgnoreCase("s"))
            {
                  System.err.print("You must choose single thread mode or multi thread mode");
                  return;
            }


            var size = list.size();

            var runnable = new ArrayList<Runnable>();


            for (var c = 1; c < size; c++)
            {
                  if (mode.equalsIgnoreCase("s"))
                  {
                        var word = list.get(c);
                        findWord(mode, word);
                  }
                  else
                  {
                        var word = list.get(c);
                        runnable.add(() -> findWord(mode, word));
                  }

            }
            runnable.forEach(Runnable::run);

            assignObjs.forEach(System.out::println);
            var totalTime = assignObjs.stream()
                    .map(AssignObj::time)
                    .reduce(0L, Long::sum);

            System.out.println("\nTotal time to search for all words is: " + totalTime + "ms");
      }


      public static void findWord(String mode, String word)
      {
            var START = System.currentTimeMillis();
            var counter = 0;
            try (BufferedReader br = new BufferedReader(new FileReader(file)))
            {
                  String line;
                  while ((line = br.readLine()) != null)
                  {
                        if (line.contains(word))
                        {
                              counter++;
                        }
                  }
            }
            catch (IOException e)
            {
                  e.printStackTrace();
                  return;
            }
            var DELTA_TIME = System.currentTimeMillis() - START;
            assignObjs.add(
                    new AssignObj(
                            mode, counter, word, DELTA_TIME
                    )
            );

      }


      public record AssignObj(String type, int occurrence, String word, long time)
      {
            @Override
            public String toString()
            {
                  return ("%s appeared %d times and took %dms and was " + (type.equalsIgnoreCase("m") ? "Multi-Threaded" : "Singly Threaded")).formatted(word, occurrence, time, type);
            }
      }

}
