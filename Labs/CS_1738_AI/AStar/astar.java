package Labs.CS_1738_AI.AStar;

import objects.Node;
import objects.ObstacleType;
import util.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class astar
{
    public static void main(String[] args)
    {
        var fileMap = Map.of(
                "mapcup.txt", "barbercup.txt",
                "mapgoaraound.txt", "barbergoaround.txt",
                "mapgoaraoundfake.txt", "barbergoaroundfake.txt"
        );


        fileMap.forEach((mapFile, barberFile) -> {
            var map = util.FileUtils.parseSimulationFormat(new File("data/%s".formatted(mapFile)));
            var barberMap = util.FileUtils.parseBarberFormat(new File("data/%s".formatted(barberFile)));
            map.setBarberMap(barberMap);


            for (var i = 0;  ; i++) {

                System.out.printf("Timestep %d%n", i+1);
                var mapWBarber = map.reEvaluateMap(i);
                var evaledBarbMap = mapWBarber.performAStar();

                if (evaledBarbMap.size() == 1)
                {
                    System.out.printf("Path found in %d timestamps%n", i+1);
                    break;
                }

                if (evaledBarbMap.isEmpty())
                {
                    System.out.println("PATH NOT FOUND!");
                    break;
                }

                map.printMap();
            }
            System.out.println("--------------------------------\n\n\n");
        });

    }
}
