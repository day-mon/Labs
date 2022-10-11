

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 */
public class GeometryApp
{
    static ArrayList<Point> points;
    static ArrayList<Circle> circles;
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args)
    {
        //Initialize data structures
        points = new ArrayList<>();
        circles = new ArrayList<>();

        //Display menu and get user option
        boolean done = false;
        while (!done)
        {
            printMenu();
            int option = in.nextInt();
            in.nextLine();
            switch (option)
            {
                case 1:
                    addPoint();
                    break;
                case 2:
                    addCircle();
                    break;
                case 3:
                    printCollection();
                    break;
                case 4:
                    writeToFile();
                    break;
                case 5:
                    readFromFile();
                    break;
                case 6:
                    done = true;
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
        System.out.println("Goodbye!");
    }

    public static void printMenu()
    {
        System.out.println("***** Geometry Application *****");
        System.out.println("1. Point to collection");
        System.out.println("2. Circle to collection");
        System.out.println("3. Display contents of collection");
        System.out.println("4. Write collection to file");
        System.out.println("5. Load collection from file");
        System.out.println("6. Exit the program");
    }

    //TODO: Prompt the user for details regarding the point to add to the collection.
    //Add the point to the points ArrayList
    // +2 pts extra credit: verify if the point label is already in the collection
    // Update the value in that Labs.OO48.finalAssignment.Point instead of adding a new Labs.OO48.finalAssignment.Point if the label already exists
    public static void addPoint()
    {
        System.out.print("Enter a label: ");
        String label = in.nextLine();
        System.out.print("Enter a x and y value: ");
        double x = in.nextDouble();
        double y = in.nextDouble();
        System.out.printf("%2f, %2f, %s", x, y, label);
        Point p = new Point(x, y, label);

        for (Point point: points)
        {
            if (point.getLabel().equals(p.getLabel()))
            {
                point.setX(x);
                point.setY(y);
                point.setLabel(label);
                return;
            }
        }
        points.add(p);
        System.out.println("Added");
    }

    //TODO: Prompt the user for details regarding the enter of the circle, and add the point
    // to points ArrayList. Add the Labs.OO48.finalAssignment.Circle to the circles ArrayList
    public static void addCircle()
    {
        System.out.print("Enter a x, y, and radius value: ");
        double x = in.nextDouble();
        double y = in.nextDouble();
        double radius = in.nextDouble();
        Point p = new Point(x, y, "Center: " + circles.size());
        points.add(p);
        Circle c = new Circle(p, radius);
        circles.add(c);



    }

    //TODO (worth 5 points): print all the points in the points list
    //For every circle in the circles list, print the circle's details (center point and radius) AND
    //print every point contained in the circle.
    public static void printCollection()
    {
        points.forEach(System.out::println);

        circles.forEach(circle -> {
            points.forEach(point1 -> {
                if (circle.contains(point1))
                {
                    System.out.println(point1);
                }
            });
        });
    }

    //TODO: Prompt the user to enter the filename. Write all the circles and points lists to the file using
    //ObjectOutputStream. NOTE: ArrayList implements java.io.Serializable, so just write the entire two lists
    //to the file.
    public static void writeToFile()
    {
        System.out.print("Enter file name: ");
        String name = in.nextLine();
        try
        {
            FileOutputStream fos = new FileOutputStream(new File(name+".ser"), true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(points);
            oos.writeObject(circles);
            oos.close();
            fos.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //TODO: Prompt the user to enter the filename. Read the two lists and overwrite any currently existing
    //values in the ArrayLists.
    public static void readFromFile()
    {
        System.out.print("Enter file name: ");
        String name = in.nextLine();
        try
        {
            FileInputStream fis = new FileInputStream(name);
            ObjectInputStream ois = new ObjectInputStream(fis);
            points = (ArrayList<Point>) ois.readObject();
            circles = (ArrayList<Circle>) ois.readObject();
            fis.close();
            ois.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
