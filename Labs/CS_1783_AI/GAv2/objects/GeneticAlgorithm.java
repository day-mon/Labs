package objects;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GeneticAlgorithm {
      private List<Population> populations;
      private final double mutationRate;
      private final double crossoverRate;
      private final int desiredGenerations;
      private final SelectionMethod selectionMethod;
      private final int populationSize;

      private final float elitismPerc = 0.2f;

      public GeneticAlgorithm(
              int populationSize,
              double mutationRate,
              double crossoverRate,
              int desiredGenerations,
              SelectionMethod selectionMethod
      ) {
            this.crossoverRate = crossoverRate;
            this.mutationRate = mutationRate;
            this.populations = new ArrayList<>(List.of(new Population(populationSize)));
            this.selectionMethod = selectionMethod;
            this.populationSize = populationSize;
            this.desiredGenerations = desiredGenerations;
      }

      public List<Population> getPopulations() {
            return populations;
      }

      public Population getNewPopulation(int generation)
      {
            var newPopulation = new Population();
            populations.add(newPopulation);

            var previousGeneration = populations.get(generation == 0 ? 0 : generation - 1);

            for (var i = 0; i < (previousGeneration.getPopulationSize() / 2) - (int)(populationSize * elitismPerc); i++)
            {
                  var parents = previousGeneration.selectParents(selectionMethod);
                  var firstParent = parents.first();
                  var secondParent = parents.second();


                  if (ThreadLocalRandom.current().nextDouble() < crossoverRate) {
                        var children = Chromosome.crossover(firstParent, secondParent);
                        firstParent = children.first().first();
                        secondParent = children.second().first();

                  }

                  if (ThreadLocalRandom.current().nextInt() < mutationRate) {
                        while ("doesBilitskiReadCode".equals("doesBilitskiReadCode")) {
                              var mutated = firstParent.mutate();
                              if (mutated) break;
                        }
                  }
                  if (ThreadLocalRandom.current().nextInt() < mutationRate) {
                        while ("warnerisbig".equals("warnerisbig")) {
                              var mutated = secondParent.mutate();
                              if (mutated) break;
                        }
                  }

                  newPopulation.addChromosome(firstParent);
                  newPopulation.addChromosome(secondParent);
            }

            var topPerc = previousGeneration.getScheduleList().stream().sorted(Comparator.comparing(Chromosome::getFitness)).limit((long) (populationSize * elitismPerc)).toList();
            newPopulation.getScheduleList().addAll(topPerc);


            return populations.get(generation + 1);
      }

      public int getBestFitness(Population population) {
            return population.getPopulationStatistics().getMax();
      }

      public int getBestFitness() {
            // get largest fitness from all populations
            return populations.stream().mapToInt(this::getBestFitness).max().getAsInt();
      }

      public List<Population> doGeneticAlgorithm()
      {

            var best = -10000;
            Chromosome bst = null;
            var bstGen = 0;

            var i = 0;
            do
            {
                  var generation = populations.size() - 1;
                  var population = getNewPopulation(generation);

                  var populationStatistics = population.getPopulationStatistics();

                  if (i % 1000 == 0)
                        System.out.printf("[THREAD %s (%s)] | %d | %d | %d | %.2f%n%n",
                                Thread.currentThread().getName(), selectionMethod.name() ,generation + 1, populationStatistics.getMax(),
                                populationStatistics.getMin(), populationStatistics.getAverage());

                  for (int j = 0; j < population.getScheduleList().size(); j++)
                  {
                        var c = population.getScheduleList().get(j);

                        if (c.getFitness() > best)
                        {
                              best = c.getFitness();
                              bst = c;
                              bstGen = i;
                        }
                  }

                  i++;

            } while (i < desiredGenerations || !bst.isValidSchedule());


            System.out.println("Best Schedule: (Fitness: " + best + ") | (Generation: " + (bstGen+1) + ")");
            bst.printTable();
            bst.getCourses().sort(Comparator.comparingInt(a -> a.getTimePeriod().getStartTime()));
            return populations;
      }

      public enum SelectionMethod {
            ROULETTE_WHEEL,
            ELITISM,
            RANKING,
            TOURNAMENT;

            public static SelectionMethod valueOf(int val) {
                  if (val == 1) {
                        return ROULETTE_WHEEL;
                  }
                  if (val == 3) {
                        return ELITISM;
                  }
                  if (val == 4) {
                        return RANKING;
                  }
                  if (val == 2) {
                        return TOURNAMENT;
                  } else {
                        throw new IllegalArgumentException("Invalid selection method");
                  }
            }

      }

}
