package objects;

import java.util.Objects;

public class Room {

    public int id;
    public String name;
    public int size;
    public boolean hasMedia;

    public Room(int id, String name, int size, boolean hasMedia) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.hasMedia = hasMedia;
    }

    @Override
    public String toString() {
        return String.format("Room { %s } %s %s %s |", id, name, size, hasMedia);
    }

    public Room deepCopy() {
        return new Room(id, name, size, hasMedia);
    }

}