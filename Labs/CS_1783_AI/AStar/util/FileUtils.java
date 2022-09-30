package Labs.CS_1783_AI.AStar.util;

import Labs.CS_1783_AI.AStar.objects.Map;
import objects.Coordinates;
import objects.Node;
import objects.ObstacleType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    private FileUtils() {}


    public static java.util.Map<Integer, List<Coordinates>> parseBarberFormat(File file) {
        try ( var reader = new BufferedReader(new FileReader(file)) )
        {
            var barberMap = new java.util.HashMap<Integer, List<Coordinates>>();
            String line;
            while ((line = reader.readLine()) != null)
            {
                if (line.equals("-1")) break;
                var split = line.split(" ");
                var timestep = Integer.parseInt(split[0]);
                var y = Integer.parseInt(split[1]);
                var x = Integer.parseInt(split[2]);
                var coordinates = new Coordinates(x, y);
                barberMap.computeIfAbsent(timestep, k -> new ArrayList<>()).add(coordinates);
            }
            return barberMap;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Simulation Format
     * M - Map Size followed by X,Y coordinate:   Eg:  M 6 8
     * S - Start location followed by X,Y coordinate: Eg: S 2 3
     * W - Wall followed by XY coordinate:             Eg:  W 2 3
     * E - End of file
     * All X Y coordinates start at index of 0.  The smallest X coordinate is at the left of the screen.
     * The smallest Y coordinate is at the top of the screen.
     * The general format for the file is as follows:
     **/
    public static Map parseSimulationFormat(File mapFile)
    {

        var map = new Node[0][0];
        var mapObj = new Map();
        try (var reader = new BufferedReader(new FileReader(mapFile)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                var lineParts = line.split(" ");
                var command = lineParts[0];
                switch (command)
                {
                    case "M" -> {
                        var y = Integer.parseInt(lineParts[1]);
                        var x = Integer.parseInt(lineParts[2]);
                        map = new Node[x][y];
                    }
                    case "S" -> {
                        var startY = Integer.parseInt(lineParts[1]);
                        var startX = Integer.parseInt(lineParts[2]);
                        map[startX][startY] = new Node(startX, startY, ObstacleType.SCANDRO);
                        mapObj.setStart(map[startX][startY]);
                        mapObj.setScandro(map[startX][startY]);
                    }
                    case "W" -> {
                        var wallY = Integer.parseInt(lineParts[1]);
                        var wallX = Integer.parseInt(lineParts[2]);
                        map[wallX][wallY] = new Node(wallX, wallY, ObstacleType.WALL);
                    }
                    case "G" -> {
                        var goalY = Integer.parseInt(lineParts[1]);
                        var goalX = Integer.parseInt(lineParts[2]);
                        var node = new Node(goalX, goalY, ObstacleType.GOAL);
                        node.setGoal(true);
                        map[goalX][goalY] = node;
                        mapObj.setGoal(node);
                    }
                    case "E" ->  {
                        // go through the map and set the obstacles
                        for (int x = 0; x < map.length; x++)
                        {
                            for (int y = 0; y < map[x].length; y++)
                            {
                                if (map[x][y] == null)
                                {
                                    map[x][y] = new Node(x, y, ObstacleType.EMPTY);
                                }
                            }
                        }
                        mapObj.setMap(map);
                        return mapObj;
                    }
                    default -> throw new RuntimeException("Invalid command: " + command);
                }
            }
        }
        catch (Exception e)
        {
            System.err.println("Error parsing map file: " + e.getMessage());
            System.exit(1);
        }
        return new Map();
    }


    // read whole file into a string


}