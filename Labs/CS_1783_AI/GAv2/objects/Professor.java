package objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Professor {
    public int number;
    public String name;

    public Professor deepCopy() {
        return new Professor(number, name);
    }

    public Professor(int number, String name) {
        this.number = number;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Professor { %d } %s  | ", number, name);
    }
}
