package Labs.CS_1783_AI.GA.out.production.GA.src;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static final int CHROMOSOME_SIZE = 20;
    private static String FILE_NAME = "";

    /**
     *
     *
     * 100 1 0.5 0.01
     * 100 1 0.5 0.05
     * 100 2 0.5 0.01
     * 100 2 0.5 0.05
     * 100 1 1.0 0.01
     * 100 2 1.0 0.01
     * 100 1 0.5 0.0
     * 100 2 0.5 0.0
     */

    public static void main(String[] args)
    {
        var n = Integer.parseInt(args[0]);
        var method = Integer.parseInt(args[1]);
        var pc = Double.parseDouble(args[2]);
        var pm = Double.parseDouble(args[3]);

        FILE_NAME = "%s-%s-%s-%s".formatted(
                String.valueOf(n),
                String.valueOf(method),
                String.valueOf(pc),
                String.valueOf(pm)
        );

        System.out.printf("Population size: %d, Method: %d, PC: %.2f, PM: %.2f", n, method, pc, pm);

        var initialPopulation = initializePopulation(n);
        var newPopulation = new ArrayList<String>();
        var random = ThreadLocalRandom.current();

        var generation = 1;


        recordToFile("Generation", "Minimum", "Average", "Maximum");
        recordToFile("Population Size = " + n,  "Method = " + method , " Crossover Probability = " + pc, "Mutation Probability = " + pm);



        while (getBestFitness(initialPopulation) != 20)
        {
            for (var i = 0; i < n / 2; i++)
            {
                var parents = selectParents(initialPopulation, method);
                var firstParent = parents.get(0);
                var secondParent = parents.get(1);


                if (random.nextDouble() < pc) {
                    var children = crossover(firstParent, secondParent);
                    firstParent = children.get(0);
                    secondParent = children.get(1);
                }


                if (random.nextDouble() < pm) {
                    mutate(firstParent);
                }

                if (random.nextDouble() < pm) {
                    mutate(secondParent);
                }

                newPopulation.add(firstParent);
                newPopulation.add(secondParent);
            }


            System.out.printf("Generation %d | Highest Fitness: %d | Average Fitness: %f \n", generation, getLargestFitness(initialPopulation),getAverageFitness(initialPopulation));

            var gen = String.valueOf(generation);
            var lowestFitness = String.valueOf(getLowestFitness(initialPopulation));
            var avg = String.valueOf(new BigDecimal(getAverageFitness(initialPopulation)).setScale(2, RoundingMode.HALF_UP));
            var largest = String.valueOf(getLargestFitness(initialPopulation));

            recordToFile(gen, lowestFitness, avg, largest);


            initialPopulation.clear();
            initialPopulation.addAll(newPopulation);
            newPopulation.clear();
            generation += 1;
        }
        var gen = String.valueOf(generation);
        var lowestFitness = String.valueOf(getLowestFitness(initialPopulation));
        var avg = String.valueOf(new BigDecimal(getAverageFitness(initialPopulation)).setScale(2, RoundingMode.HALF_UP));
        var largest = String.valueOf(getLargestFitness(initialPopulation));

        recordToFile(gen, lowestFitness, avg, largest);

    }

    public static double getAverageFitness(List<String> population)
    {
        // calculate the average fitness of the population without using stream
        var sum = 0f;
        for (String chromosome : population)
        {
            var fitness = measureFitness(chromosome);
            sum += fitness;
        }
        var avg = sum / population.size();
        return avg;
    }

    private static void recordToFile(String generation, String min, String avg, String max) {

        // make a one liner that writes to a file
        try { new FileWriter("%s.csv".formatted(FILE_NAME), true).append(String.format("%s,%s,%s,%s%n", generation, min, avg, max)).close(); } catch (IOException e) {throw new RuntimeException(e);}
    }

    public static long getLargestFitness(List<String> population)
    {
        return population.stream().mapToLong(Main::measureFitness).max().orElseThrow();
    }


    public static long getLowestFitness(List<String> population)
    {
        return population.stream().mapToLong(Main::measureFitness).min().orElseThrow();
    }

    public static String mutate(String parent)
    {

        int mutatePoint = (int) (ThreadLocalRandom.current().nextDouble() * parent.length());


        return mutatePoint == 1  ?
            parent = "%s0%s".formatted(parent.substring(0, mutatePoint), parent.substring(mutatePoint))
                :  "%s1%s".formatted(parent.substring(0, mutatePoint), parent.substring(mutatePoint));

    }


    private static List<String> crossover(String firstParent, String secondParent) {


        var random = ThreadLocalRandom.current();
        var children = new ArrayList<String>();


        // get a random crossover point
        var crossoverPoint = random.nextInt(1, CHROMOSOME_SIZE - 1);
        // get the first part of the first parent
        var child1 = firstParent.substring(0, crossoverPoint);
        // get the second part of the second parent
        var child2 = secondParent.substring(crossoverPoint);
        // get the second part of the first parent
        var child3 = firstParent.substring(crossoverPoint);
        // get the first part of the second parent
        var child4 = secondParent.substring(0, crossoverPoint);
        // add the children to the list of children
        children.add(child1 + child2);
        children.add(child3 + child4);

        return children;
    }

    private static int getBestFitness(List<String> population) {
        return (int) population.stream().mapToLong(Main::measureFitness).max().orElseThrow();

    }

    private static List<String> selectParents(List<String> population, int method) {
        var parents = new ArrayList<String>();
        if (method == 1) {
            var totalFitness = population.stream().mapToLong(Main::measureFitness).sum();
            var random = ThreadLocalRandom.current();

            while (parents.size() < 2) {
                var randomFitness = random.nextInt((int) totalFitness);
                var fitness = 0;
                for (var chromosome : population) {
                    fitness += measureFitness(chromosome);
                    if (fitness >= randomFitness) {
                        parents.add(chromosome);
                        break;
                    }
                }
            }

        } else {
            var random = ThreadLocalRandom.current();
            while (parents.size() < 2) {
                var a = population.get(random.nextInt(population.size()));
                var b = population.get(random.nextInt(population.size()));
                parents.add(measureFitness(a) > measureFitness(b) ? a : b);
            }
        }
        return parents;
    }

    private static int measureFitness(String chromosome) {
        return (int) chromosome.chars().filter(ch -> ch == '1').count();
    }


    public static List<String> initializePopulation(int n) {
        return IntStream.range(0, n).mapToObj(obj -> IntStream.range(0, CHROMOSOME_SIZE).mapToObj(j -> ThreadLocalRandom.current().nextInt(0, 2)).map(Objects::toString).collect(Collectors.joining())).collect(Collectors.toList());
    }
}