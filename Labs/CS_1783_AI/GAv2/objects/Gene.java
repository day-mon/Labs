package objects;

import java.util.List;
import java.util.Objects;

// This is a Course
public final class Gene {
    private int id;
    private String name;
    private Professor professor;
    private int size;
    private boolean hasMedia;
    private TimePeriod timePeriod;
    private Room room;

    public Gene(int id, String name, Professor professor, int size, boolean hasMedia) {
        this.id = id;
        this.name = name;
        this.professor = professor;
        this.size = size;
        this.hasMedia = hasMedia;
        this.timePeriod = null;
        this.room = null;
    }

    public Gene() {

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

    public void setTimePeriod(TimePeriod timePeriod, List<Gene> courses) {
        if (courses.isEmpty() || this.timePeriod == null) {
            this.timePeriod = timePeriod;
        }

        for (var course : courses) {
            // 1. A professor can't teach two courses at the same time
            // 2. A course can't be in two rooms at the same time
            // 3. A course can't be in two time periods at the same time

            // 1. A professor can't teach two courses at the same time
            if (course.getProfessor().number == this.professor.number
                    && course.getTimePeriod().getTimePeriodId() == timePeriod.getTimePeriodId()) {
                this.timePeriod = null;
                return;
            }

            // 2. A course can't be in two rooms at the same time
            if (course.getRoom().id == this.room.id
                    && course.getTimePeriod().getTimePeriodId() == timePeriod.getTimePeriodId()) {
                this.timePeriod = null;
                return;
            }

            if (course.getId() == this.id && this.timePeriod.getTimePeriodId() == timePeriod.getTimePeriodId()) {
                this.timePeriod = null;
                return;
            }

            this.timePeriod = timePeriod;
        }
    }

    public void setTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

    public void setRoom(Room room) {
        if (!(room.size >= size))
            return;
        if (this.hasMedia && !room.hasMedia)
            return;
        this.room = room;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        var that = (Gene) obj;
        return this.id == that.id &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.professor, that.professor) &&
                this.size == that.size &&
                this.hasMedia == that.hasMedia &&
                Objects.equals(this.timePeriod, that.timePeriod) &&
                Objects.equals(this.room, that.room);
    }

    public Gene deepCopy() {
        var course = new Gene();
        course.setId(this.id);
        course.setName(this.name);
        course.setProfessor(this.professor);
        course.setSize(this.size);
        course.setHasMedia(this.hasMedia);
        if (this.room != null) {
            course.setRoom(this.room);
        }
        if (this.timePeriod != null) {
            course.setTimePeriod(this.timePeriod);
        }
        return course;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, professor, size, hasMedia, timePeriod, room);
    }

    @Override
    public String toString() {
        return String.format("Course { %s }  %s   %s    %s    %s ", id, name, professor, room, timePeriod);
    }

}
