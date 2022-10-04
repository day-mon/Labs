package Labs.CS_1783_AI.GA;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Schedule {
    private String scheduleId;
    private List<Course> courses;
    private int fitness;


    public Map<Professor, List<Course>> getProfessorSchedule() {
       return courses.stream().collect(Collectors.groupingBy(Course::getProfessor));
    }

    public Schedule(List<Course> courses) {
        this.scheduleId = UUID.randomUUID().toString();
        this.courses = courses;
        this.fitness = 0;
    }

    public Schedule()
    {
        this.scheduleId = UUID.randomUUID().toString();
        this.courses = new ArrayList<>();
        this.fitness = 0;
    }


    public List<Course> getCourses() {
        return courses;
    }

    public String getScheduleId() {
        return scheduleId;
    }




    public int getFitness()
    {
        // Subtract 1 point for each seat available that is not taken.

        for (var course : courses)
        {
            var room = course.getRoom();
            var size = course.getSize();
            var seats = room.size();
            var seatsAvailable = seats - size;
            fitness -= seatsAvailable;
        }

        return 0;
        // Subtract 15 points for each delay of 3 or more hours between courses.

    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }



    // generate a random schedule


    // prettify course list
    @Override
    public String toString() {
        var str = new StringBuilder();
        var last = 9;
        for (var course : this.courses) {
            if (last != course.getTimePeriod().getTimePeriodId())
            {
                str.append("\n")
                        .append("-----")
                        .append("\n");
                last = course.getTimePeriod().getTimePeriodId();
            }
            str.append(course.getName()).append(" with ")
                    .append(course.getProfessor().name()).append(" in ")
                    .append(course.getRoom().name()).append(" at ")
                    .append(course.getTimePeriod().getStartTime()).append(" -- ")
                    .append(course.getSize()).append(" / ")
                    .append(course.getRoom().size()).append(" students")
                    .append("\n");
        }
        return str.toString();
    }



    public void printTable()
    {
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.printf("%5s %10s %15s %15s %6s %5s %10s %10s %10s", "CRN", "Course", "Professor", "Room", "Needs", "Has", "Time", "Size", "Capacity");
        System.out.println("\n----------------------------------------------------------------------------------------------");
        for (Course course : this.courses) {
            System.out.printf("%5d %10s %15s %15s %6s %5s %10s %10d %10d",
                    course.getId(), course.getName().toUpperCase(), course.getProfessor().name(), course.getRoom().name(),
                    course.hasMultiMedia() ? "X" : "", course.getRoom().hasMedia() ? "X" : "", course.getTimePeriod().getStartTime(), course.getSize(), course.getRoom().size());
            System.out.println();
        }
    }
}