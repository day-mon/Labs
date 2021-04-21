package Labs.OO48.finalAssignment;

import java.io.Serializable;
import java.util.Objects;

public class Point implements Serializable
{

    private double x;
    private double y;
    private String label;

    public Point()
    {

    }

    public Point(String label)
    {
        this.label = label;
    }

    public Point(double x, double y, String label)
    {
        this.x = x;
        this.y = y;
        this.label = label;
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public double distance(Point o)
    {
        return Math.sqrt(
                Math.pow(x - o.x, 2) + Math.pow(y - o.y, 2)
        );
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 &&
                Double.compare(point.y, y) == 0 &&
                Objects.equals(label, point.label);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y, label);
    }

    @Override
    public String toString()
    {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", label='" + label + '\'' +
                '}';
    }
}
