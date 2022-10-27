package objects;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Chromosome {
    private StringBuffer invalidReason = new StringBuffer();
    private String scheduleId;
    private List<Gene> courses;
    private int fitness;
    private boolean mutated;

    public Chromosome(Chromosome c) {
        this.scheduleId = c.getScheduleId();
        this.fitness = c.getFitness();
        this.courses =  c.getCourses().stream().map(Gene::deepCopy).collect(Collectors.toList());
    }

    public Map<Professor, List<Gene>> getProfessorSchedule() {
        return courses.stream().collect(Collectors.groupingBy(Gene::getProfessor));
    }

    public  Chromosome(List<Gene> courses) {
        this.scheduleId = UUID.randomUUID().toString();
        this.courses = courses;
        this.fitness = getFitness();
    }

    public boolean isMutated() {
        return mutated;
    }

    public void setMutated(boolean mutated) {
        this.mutated = mutated;
    }

    public List<Gene> getCourses() {
        return courses;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public int getBonus() {
        // if a professor is the same room back to back and time periods are one after
        // another
        // then we give a bonus of 10
        var professorSchedule = getProfessorSchedule();

        var professors = professorSchedule.keySet();

        var bonus = 0;
        for (var prof : professors) {
            var rooms = new ArrayList<>();
            for (var g : courses) {
                if (g.getProfessor().number != prof.number) continue;

                if (!rooms.contains(g.getRoom().id)) {
                    rooms.add(g.getRoom().id);
                } else {
                    bonus += 5;
                }
            }
        }
        return bonus;
    }

    public int getPenalty() {
        // Subtract 15 points for each delay of 3 or more hours between courses.
        var penalty = 0;

        var professorSchedule = getProfessorSchedule();
        var professors = professorSchedule.keySet();


        for (var prof : professors)
        {

            var starts = new ArrayList<Integer>();
            for (var g : courses)
            {
                if (g.getProfessor().number != prof.number) continue;
                starts.add(g.getTimePeriod().getTimePeriodId());
            }

            Collections.sort(starts);

            int nex = 0;
            for (int j = 0; j < starts.size() - 1; j++)
            {
                int cur = starts.get(j);
                int next = starts.get(j + 1);
                nex = (next - cur == 1) ? nex + 1 : 0;
                if (next - cur >= 3) penalty += 15;
            }

            if (nex > 3) penalty += 10 * (nex - 3);

        }
        return penalty;
    }

    public int getFitness() {
        this.fitness = 0;
        for (var course : courses) {
            var fitness = course.getRoom().size - course.getSize();
            this.fitness -= fitness;
        }
        var bonus = getBonus();
        var penalty = getPenalty();
        this.fitness = fitness + bonus - penalty;
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public boolean mutate() {
        var randomTimePeriod = Constants.TIME_PERIODS
                .get(ThreadLocalRandom.current().nextInt(0, Constants.TIME_PERIODS.size()));
        var randomCourse = courses.get(ThreadLocalRandom.current().nextInt(courses.size()));

        courses.remove(randomCourse);
        var timePeriod = randomCourse.getTimePeriod();

        randomCourse.setTimePeriod(randomTimePeriod, courses);

        if (randomCourse.getTimePeriod() == null)
        {
            randomCourse.setTimePeriod(timePeriod);
            courses.add(randomCourse);
            return false;
        }

        courses.add(randomCourse);
        return true;
    }

    public boolean isValidSchedule()
    {

        // Courses that require a multimedia room must be assigned a multimedia room
        // Rooms must be large enough to accommodate the number of students enrolled.
        // A course (CRN) must only be on the schedule exactly 1 time.
        // A professor must only teach 1 course at a time.
        // A course cannot be scheduled in a room that is already in use at the same
        // time.
        var isValid = true;

        for (var e : getProfessorSchedule().entrySet()) {
            var v = e.getValue();
            var duplicate = v.stream()
                    .anyMatch(c -> v.stream().filter(
                                    c2 -> c2.getTimePeriod().getStartTime() == c.getTimePeriod().getStartTime())
                            .count() > 1);
            if (duplicate) {
                invalidReason.append("Professor ").append(e.getKey().name)
                        .append(" has a duplicate course\n");
                isValid = false;
            }
        }

        var courseSchedule = this.getCourses();
        for (var course : courseSchedule) {
            var room = course.getRoom();
            var size = course.getSize();
            if (course.hasMultiMedia() && !room.hasMedia) {
                this.invalidReason.append("Course ").append(course.getId())
                        .append(" requires a multimedia room\n");
                isValid = false;
            }
            if (room.size < size)
                return false;
        }

        var courseIds = new HashSet<Integer>();
        for (var course : courseSchedule) {
            if (courseIds.contains(course.getId())) {
                this.invalidReason.append("Course ").append(course.getName())
                        .append(" is scheduled more than once\n");
                isValid = false;
            }
            courseIds.add(course.getId());
        }

        for (var course : courseSchedule) {
            var timePeriod = course.getTimePeriod();
            var room = course.getRoom();
            for (var course2 : courseSchedule) {
                if (course.getId() == course2.getId())
                    continue;
                if (timePeriod.getTimePeriodId() == course2.getTimePeriod().getTimePeriodId()
                        && room.id == course2.getRoom().id) {
                    this.invalidReason.append("Room ")
                            .append(room.id)
                            .append(" is already in use @ ")
                            .append(timePeriod.getStartTime())
                            .append(" for ")
                            .append(course2.getName().toUpperCase())
                            .append(" with ")
                            .append(course2.getProfessor().name)
                            .append(" \n");
                    isValid = false;
                }
            }

        }
        return isValid;
    }

    public static Tuple<Tuple<Chromosome, Boolean>, Tuple<Chromosome, Boolean>> crossover(Chromosome firstParent, Chromosome secondParent) {

        var crossoverPoint = ThreadLocalRandom.current().nextInt(firstParent.getCourses().size() - 2) + 1;




        var partOneChildOne = firstParent.getCourses().subList(0, crossoverPoint);
        var partTwoChildOne = secondParent.getCourses().subList(crossoverPoint, secondParent.getCourses().size());

        var partOneChildTwo = secondParent.getCourses().subList(0, crossoverPoint);
        var partTwoChildTwo = firstParent.getCourses().subList(crossoverPoint, firstParent.getCourses().size());

        var childOne = new Chromosome(new ArrayList<>());
        childOne.getCourses().addAll(partOneChildOne);
        childOne.getCourses().addAll(partTwoChildOne);

        var childTwo = new Chromosome(new ArrayList<>());
        childTwo.getCourses().addAll(partOneChildTwo);
        childTwo.getCourses().addAll(partTwoChildTwo);

        var childOneValid = childOne.isValidSchedule();
        var childTwoValid = childTwo.isValidSchedule();


        childOne.getFitness();
        childTwo.getFitness();

        if (!childOneValid) {
            childOne = childTwo.random();
        }

        if (!childTwoValid) {
            childTwo = childTwo.random();
        }

        return new Tuple<>(new Tuple<>(childOne, childOneValid), new Tuple<>(childTwo, childTwoValid));

    }

    // generate a random schedule

    // prettify course list
    @Override
    public String toString()
    {
        var str = new StringBuilder();
        var last = 9;
        for (var course : this.courses) {
            if (last != course.getTimePeriod().getTimePeriodId()) {
                str.append("\n");
                last = course.getTimePeriod().getTimePeriodId();
            }
            str.append(course.getName()).append(" with ")
                    .append(course.getProfessor().name).append(" in ")
                    .append(course.getRoom().name).append(" at ")
                    .append(course.getTimePeriod().getStartTime()).append(" -- ")
                    .append(course.getSize()).append(" / ")
                    .append(course.getRoom().size).append(" students")
                    .append("\n");
        }
        return str.toString();
    }

    public void printHelperString() {
        System.out.printf("Schedule UUID: %s%nFitness: %d, Penalty: %d, Bonus: %d%n%n", this.getScheduleId(),
                this.getFitness(), this.getPenalty(), this.getBonus());
    }

    public void printTable() {
        System.out.println(
                "----------------------------------------------------------------------------------------------");
        System.out.printf("%5s %10s %15s %15s %6s %5s %10s %10s %10s", "CRN", "Course", "Professor", "Room",
                "Needs", "Has", "Time", "Size", "Capacity");
        System.out.println(
                "\n----------------------------------------------------------------------------------------------");
        for (Gene gene : this.courses) {
            System.out.printf("%5d %10s %15s %15s %6s %5s %10s %10d %10d",
                    gene.getId(), gene.getName().toUpperCase(), gene.getProfessor().name, gene.getRoom().name,
                    gene.hasMultiMedia() ? "X" : "", gene.getRoom().hasMedia ? "X" : "",
                    gene.getTimePeriod().getStartTime(), gene.getSize(), gene.getRoom().size);
            System.out.println();
        }
        System.out.printf("%s%n", this.isValidSchedule() ? "Valid Schedule" : "Invalid Schedule");
    }

    private Chromosome random()
    {
        Chromosome ret = null;

        do
        {
            List<Gene> courses = Constants.COURSE_LIST;
            List<Room> rooms = Constants.ROOM_LIST;
            List<TimePeriod> timePeriods = Constants.TIME_PERIODS;

            var newCourses = new ArrayList<Gene>();
            var visited = new HashSet<Integer>();
            var k = ThreadLocalRandom.current().nextInt(0, courses.size());
            boolean maxIterationsMet = false;

            while (visited.size() != courses.size())
            {
                if (visited.contains(k))
                {
                    k = ThreadLocalRandom.current().nextInt(0, courses.size());
                    continue;
                }

                visited.add(k);

                var course = courses.get(k).deepCopy();
                var randomRoom = rooms.get(ThreadLocalRandom.current().nextInt(0, rooms.size()));
                var randomTimePeriod = timePeriods
                        .get(
                                ThreadLocalRandom
                                        .current()
                                        .nextInt(0, timePeriods.size())
                        );

                for (var i = 0; course.getRoom() == null; i++)
                {
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

            if (maxIterationsMet) continue;
            newCourses.sort(Comparator.comparingInt(Gene::getId));
            ret = new Chromosome(newCourses);

        } while (ret == null);

        return ret;

    }
}