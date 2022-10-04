package Labs.CS_1783_AI.GA;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Population
{
    private List<Schedule> scheduleList;
    private int fitness;

    private static final int MAX_ITERATIONS = 3000;


    public Population(int populationSize)
    {
        this.scheduleList = init(populationSize);
    }

    public int getFitness()
    {
        return fitness;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }



    private List<Schedule> init(int n)
    {
        var startTime = System.nanoTime();

        var courses = Constants.COURSES;
        var rooms = Constants.ROOMS;
        var timePeriods = Constants.TIME_PERIODS;

        var schedules = new ArrayList<Schedule>();

        for (var c = 0; c < n; c++)
        {
            var newCourses = new ArrayList<Course>();
            var visted = new HashSet<Integer>();
            var k = ThreadLocalRandom.current().nextInt(0, courses.size());
            boolean maxIterationsMet = false;



            while (visted.size() != courses.size())
            {
                if (visted.contains(k))
                {
                    k = ThreadLocalRandom.current().nextInt(0, courses.size());
                    continue;
                }
                visted.add(k);
                var course = courses.get(k);
                var randomRoom = rooms.get(ThreadLocalRandom.current().nextInt(0, rooms.size()));
                var randomTimePeriod = timePeriods.get(ThreadLocalRandom.current().nextInt(0, timePeriods.size()));

                for (var i = 0; course.getRoom() == null; i++)
                {
                    var randomNum = ThreadLocalRandom.current().nextInt(0, rooms.size());
                    var newRandomRoom = rooms.get(randomNum);
                    course.setRoom(i == 0 ? randomRoom : newRandomRoom);
                }

                for (var i = 0; course.getTimePeriod() == null; i++)
                {
                    if (i == MAX_ITERATIONS)
                    {
                        visted.clear();
                        newCourses.clear();
                        maxIterationsMet = true;
                        c -= 1;
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
            newCourses.sort(Comparator.comparingInt(o -> o.getTimePeriod().getTimePeriodId()));
            schedules.add(new Schedule(newCourses));
            visted.clear();
        }

        var delta = (System.nanoTime() - startTime) / 1000000;
        System.out.println("Time to generate random schedule: " + delta + " ms");
        return schedules.stream().filter(s -> !s.getCourses().isEmpty()).toList();
    }
    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }
}
