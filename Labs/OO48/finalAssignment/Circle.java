package Labs.OO48.finalAssignment;

import java.io.Serializable;
import java.util.Objects;

public class Circle implements Serializable
{
    private Point center;
    private double radius;

    public Circle(Point center, double radius)
    {
        this.center = center;
        this.radius = radius;
    }

    public Circle(double x, double y, double radius)
    {
        this.radius = radius;
        center = new Point(x, y, "Center");
    }

    public double circumference()
    {
        return 2 * Math.PI * radius;
    }

    public double area()
    {
        return Math.PI * radius * radius;
    }

    public boolean contains(Point p)
    {
        return p.distance(this.center) < radius;
    }


    public Point getCenter()
    {
        return center;
    }

    public void setCenter(Point center)
    {
        this.center = center;
    }

    public double getRadius()
    {
        return radius;
    }

    public void setRadius(double radius)
    {
        this.radius = radius;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return Double.compare(circle.radius, radius) == 0 &&
                Objects.equals(center, circle.center);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(center, radius);
    }

    @Override
    public String toString()
    {
        return "Circle{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }
}
