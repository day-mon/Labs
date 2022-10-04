package Labs.CS_1783_AI.GA;

import java.util.Objects;

public record Room(int id, String name, int size, boolean hasMedia) {
    @Override
    public String toString() {
        return "Room { %s } %s %s %s".formatted(id, name, size, hasMedia);
    }
}