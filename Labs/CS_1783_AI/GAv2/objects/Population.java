package objects;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Population {
    private List<Chromosome> chromosomeList;
    private int populationSize;
    private double crossoverRate;

    public Population(int popSize) {
        this.chromosomeList = init(popSize);
        this.populationSize = chromosomeList.size();
    }


    public Population() {
        this.chromosomeList = new ArrayList<>();
        this.populationSize = 0;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void addChromosome(Chromosome chromosome) {
        chromosomeList.add(chromosome);
        populationSize++;
    }

    public IntSummaryStatistics getPopulationStatistics() {
        return chromosomeList.stream().mapToInt(Chromosome::getFitness).summaryStatistics();
    }

    public List<Chromosome> getScheduleList() {
        return chromosomeList;
    }


    private List<Chromosome> init(int n) {
        var startTime = System.nanoTime();

        var schedules = new ArrayList<Chromosome>();

        for (var f = 0; f < n; f++)
        {
            List<Gene> courses = Constants.COURSE_LIST;
            List<Room> rooms = Constants.ROOM_LIST;
            List<TimePeriod> timePeriods = Constants.TIME_PERIODS;

            var newCourses = new ArrayList<Gene>();
            var visited = new HashSet<Integer>();
            var k = ThreadLocalRandom.current().nextInt(0, courses.size());
            boolean maxIterationsMet = false;

            while (visited.size() != courses.size()) {
                if (visited.contains(k)) {
                    k = ThreadLocalRandom.current().nextInt(0, courses.size());
                    continue;
                }

                visited.add(k);

                var course = courses.get(k).    deepCopy();
                var randomRoom = rooms.get(ThreadLocalRandom.current().nextInt(0, rooms.size()));
                var randomTimePeriod = timePeriods.get(ThreadLocalRandom.current().nextInt(0, timePeriods.size()));

                for (var i = 0; course.getRoom() == null; i++) {
                    var randomNum = ThreadLocalRandom.current().nextInt(0, rooms.size());
                    var newRandomRoom = rooms.get(randomNum);
                    course.setRoom(i == 0 ? randomRoom : newRandomRoom);
                }

                for (var i = 0; course.getTimePeriod() == null; i++)
                {
                    if (i >= timePeriods.size())
                    {
                        visited.clear();
                        newCourses.clear();
                        maxIterationsMet = true;
                        f -= 1;
                        break;
                    }

                    var randomNumber = ThreadLocalRandom.current().nextInt(0, timePeriods.size());
                    var newTimePeriod = timePeriods.get(randomNumber);
                    course.setTimePeriod(i == 0 ? randomTimePeriod : newTimePeriod, newCourses);
                }

                if (maxIterationsMet)
                {
                    k = ThreadLocalRandom.current().nextInt(0, courses.size());
                    break;
                }
                k = ThreadLocalRandom.current().nextInt(0, courses.size());

                newCourses.add(course);
            }
            newCourses.sort(Comparator.comparingInt(Gene::getId));
            if (newCourses.isEmpty()) continue;
            schedules.add(new Chromosome(newCourses));
            visited.clear();
        }

        var delta = (System.nanoTime() - startTime) / 1000000;
        System.out.println("Time to generate random schedule: " + delta + " ms");


        return schedules;
    }


    public void setCrossoverRate(double crossoverRate) {
        this.crossoverRate = crossoverRate;
    }

    public double getCrossoverRate() {
        return crossoverRate;
    }



    public void setScheduleList(List<Chromosome> chromosomeList) {
        this.chromosomeList = chromosomeList;
    }

    public Tuple<Chromosome, Chromosome> tournamentSelection()
    {
        final int parentPoolSize = 10;
        var random = ThreadLocalRandom.current();

        List<Tuple<Chromosome, Chromosome>> list = new ArrayList<>();


        for (var i = 0; i < parentPoolSize; i++)
        {
            var tuple = new Tuple<Chromosome, Chromosome>();

            while (!tuple.isFull())
            {
                var a = chromosomeList.get(random.nextInt(chromosomeList.size()));
                var b = chromosomeList.get(random.nextInt(chromosomeList.size()));

                tuple.insert(a.getFitness() > b.getFitness() ? a : b);
            }
            list.add(tuple);
        }

        // get best tuple out of list of tuples
        var bestTuple = list.get(0);
        for (var i = 1; i < list.size(); i++)
        {
            var tuple = list.get(i);
            if ((tuple.first.getFitness() > bestTuple.first.getFitness()) && (tuple.second.getFitness() > bestTuple.second.getFitness()))
                bestTuple = list.get(i);
        }
        return bestTuple;

    }

    public Tuple<Chromosome, Chromosome> elitismSelection()
    {
        var elitismPercentage = 0.5;
        var top = chromosomeList.stream().sorted(Comparator.comparingInt(Chromosome::getFitness)
                        .reversed())
                .limit((int) (populationSize * elitismPercentage))
                .toList();


        var random = ThreadLocalRandom.current();
        var a = top.get(random.nextInt(top.size()));
        var b = top.get(random.nextInt(top.size()));




        return new Tuple<>(a, b);

    }

    public Tuple<Chromosome, Chromosome> rouletteWheelSelection()
    {
        var tuple = new Tuple<Chromosome, Chromosome>();

        var sum = chromosomeList.stream().mapToInt(Chromosome::getFitness).sum();

        do {
            int random = (int) (Math.random() * sum);
            int count = 0;


            for (var s :  chromosomeList)
            {
                count += s.getFitness();
                if (count >= random)
                {
                    tuple.insert(s);
                }
            }
        } while (!tuple.isFull());

        return tuple;
    }


    public Tuple<Chromosome, Chromosome> selectParents(GeneticAlgorithm.SelectionMethod selectionMethod)
    {
        switch (selectionMethod)
        {
            case TOURNAMENT -> {
                return tournamentSelection();
            }
            case ROULETTE_WHEEL -> {
                return rouletteWheelSelection();
            }
            case ELITISM -> {
                return elitismSelection();
            }
        }
        throw new IllegalArgumentException("Invalid selection method");
    }
}