import objects.GeneticAlgorithm;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ga
{

      private static final int DESIRED_GENERATIONS = 1_000;
      private static final int CLUSTER_GRAPH_SIZE = DESIRED_GENERATIONS / 100;
      private static final int NUM_OF_RUNS = 1;

      public static void main(String[] args) throws Exception
      {
            var commandArgsMap = Map.of(
                    1, new CommandArgs(250, 0.5, 0.05, GeneticAlgorithm.SelectionMethod.ELITISM, DESIRED_GENERATIONS),
                    5, new CommandArgs(250, 0.25, 0.02, GeneticAlgorithm.SelectionMethod.ELITISM, DESIRED_GENERATIONS),
                    3, new CommandArgs(250, 1.0, 0.05, GeneticAlgorithm.SelectionMethod.ELITISM, DESIRED_GENERATIONS),
                    2, new CommandArgs(250, 0.5, 0.05, GeneticAlgorithm.SelectionMethod.TOURNAMENT, DESIRED_GENERATIONS),
                    4, new CommandArgs(250, 1.0, 0.05, GeneticAlgorithm.SelectionMethod.TOURNAMENT, DESIRED_GENERATIONS),
                    6, new CommandArgs(250, 0.25, 0.02, GeneticAlgorithm.SelectionMethod.TOURNAMENT, DESIRED_GENERATIONS)
            );
            var executor = Executors.newFixedThreadPool(commandArgsMap.size());

            List<Future<?>> futures = new ArrayList<>();
            for (var w = 0; w < NUM_OF_RUNS ; w++)
            {
                  for (var i = 0; i < commandArgsMap.size(); i++)
                  {
                        int finalI = i;
                        var job = executor.submit(() ->
                        {
                              try {
                                    Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
                                    var details = commandArgsMap.get(finalI + 1);

                                    var startTime = System.currentTimeMillis();

                                    var n = details.popSize();
                                    var percentageCrossover = details.crossover();
                                    var percentageMutation = details.mutation();
                                    var selectionMethod = details.selectionMethod();
                                    var desiredGenerations = details.desiredGenerations();
                                    var fileName = String.format("n=%d,pc=%.2f,pm=%.2f,selection method=%s,%s", n, percentageCrossover, percentageMutation, selectionMethod, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss")));

                                    var ga = new GeneticAlgorithm(n, percentageCrossover, percentageMutation, desiredGenerations, selectionMethod);


                                    var populations = ga.doGeneticAlgorithm();

                                    var endTime = System.currentTimeMillis();
                                    var duration = Duration.ofMillis(endTime - startTime);

                                    System.out.printf("Time taken: %d seconds%n", duration.getSeconds());
                                    System.out.println("Writing to file...");

                                    var builder = new StringBuilder();
                                    builder.append("Generation,Max,Min,Average")
                                            .append(System.lineSeparator());

                                    for (var k = 0; k < populations.size(); k++) {
                                          if (k % CLUSTER_GRAPH_SIZE != 0) continue;

                                          var population = populations.get(k);
                                          var populationStatistics = population.getPopulationStatistics();
                                          builder.append(String.format("%d,%d,%d,%.2f%n", k + 1, populationStatistics.getMax(), populationStatistics.getMin(), populationStatistics.getAverage()));
                                    }


                                    writeToFile(fileName, builder.toString());
                                    System.out.printf("Done writing to file: %s for thread: %s%n", fileName, Thread.currentThread().getName());
                              } catch (Exception e) {
                                    e.printStackTrace();
                              }
                        });
                        futures.add(job);
                  }
            }
            while (futures.stream().anyMatch(f -> !f.isDone()))
            {
                  Thread.sleep(1000);
            }
            executor.shutdown();


      }

      private static void writeToFile(String fileName, String content)
      {
            FileWriter writer;
            try
            {
                  writer = new FileWriter(fileName + ".csv");
                  writer.write(content);
                  writer.close();
            }
            catch (IOException e)
            {
                  throw new RuntimeException(e);
            }

      }


      public record CommandArgs(int popSize, double crossover, double mutation, GeneticAlgorithm.SelectionMethod selectionMethod, int desiredGenerations) {}
}