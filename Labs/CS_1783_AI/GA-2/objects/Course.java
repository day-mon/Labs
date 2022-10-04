package Labs.CS_1783_AI.GA;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public final class Course {
    private  int id;
    private String name;
    private Professor professor;
    private int size;
    private boolean hasMedia;
    private TimePeriod timePeriod;
    private Room room;


    public Course(int id, String name, Professor professor, int size, boolean hasMedia) {
        this.id = id;
        this.name = name;
        this.professor = professor;
        this.size = size;
        this.hasMedia = hasMedia;
        this.timePeriod = null;
        this.room = null;
    }

    public Course() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public Professor getProfessor() {
        return professor;
    }

    public Room getRoom() {
        return room;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public void setHasMedia(boolean hasMedia) {
        this.hasMedia = hasMedia;
    }

    public boolean hasMultiMedia() {
        return hasMedia;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setTimePeriod(TimePeriod timePeriod, List<Course> courses)
    {
        if (courses.isEmpty() || this.timePeriod == null)
        {
            this.timePeriod = timePeriod;
        }

        for (var course : courses)
        {
            // 1. A professor can't teach two courses at the same time
            // 2. A course can't be in two rooms at the same time
            // 3. A course can't be in two time periods at the same time

            // 1. A professor can't teach two courses at the same time
            if (course.getProfessor().number() == this.professor.number() && course.getTimePeriod().getTimePeriodId() == timePeriod.getTimePeriodId())
            {
                this.timePeriod = null;
                return;
            }

            // 2. A course can't be in two rooms at the same time
            if (course.getRoom().id() == this.room.id() && course.getTimePeriod().getTimePeriodId() == timePeriod.getTimePeriodId())
            {
                this.timePeriod = null;
                return;
            }

            if (course.getId() == this.id && this.timePeriod.getTimePeriodId() == this.timePeriod.getTimePeriodId())
            {
                this.timePeriod = null;
                return;
            }


            this.timePeriod = timePeriod;
        }
    }




    public void setRoom(Room room)
    {
        if (!(room.size() >= size)) return;
        if (this.hasMedia && !room.hasMedia()) return;
        this.room = room;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Course) obj;
        return this.id == that.id &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.professor, that.professor) &&
                this.size == that.size &&
                this.hasMedia == that.hasMedia &&
                Objects.equals(this.timePeriod, that.timePeriod) &&
                Objects.equals(this.room, that.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, professor, size, hasMedia, timePeriod, room);
    }

    @Override
    public String toString() {
        return "Course { %s }  %s %s %s %s".formatted(id, name, professor, room, timePeriod);
    }

}
