package Labs.CS_1783_AI.AStar.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Map {
    private Node[][] map;
    private Node start;
    private Node goal;
    private Node scandro;
    private java.util.Map<Integer, List<Coordinates>> barberMap;

    public Map(Node[][] map, Node start, Node goal, Node scandro) {
        this.map = map;
        this.start = start;
        this.goal = goal;
        this.scandro = scandro;
    }

   public Map() {

   }

    public void setMap(Node[][] map) {
        this.map = map;
    }

    public java.util.Map<Integer, List<Coordinates>> getBarberMap() {
        return barberMap;
    }

    public void setBarberMap(java.util.Map<Integer, List<Coordinates>> barberMap) {
        this.barberMap = barberMap;
    }

    public void setGoal(Node goal) {
        this.goal = goal;
    }

    public void setScandro(Node scandro) {
        this.scandro = scandro;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public Node[][] getMap() {
        return map;
    }

    public Node getStart() {
        return start;
    }

    public Node getGoal() {
        return goal;
    }

    public Node getScandro() {
        return scandro;
    }

    public void printMap()
    {
        // print map to console
        for (Node[] nodes : this.map)
        {
            for (Node node : nodes)
            {
                System.out.printf("%c   ", node.getObstacle().getSymbol());
            }
            System.out.println("\n");
        }

    }

    public List<Node> performAStar()
    {
        var path = new ArrayList<Node>();
        var notDiscovered = new ArrayList<Node>();
        var discoverd = new ArrayList<Node>();

        start.setH(getDistance(start, goal));
        start.setF(start.getG() + start.getH());

        // add start node to open list
        notDiscovered.add(start);


        // while open list is not empty
        while (!notDiscovered.isEmpty())
        {
            // f = g + h
            // g = cost from start to current node
            // h = cost from current node to goal
            // f = total cost



            // get node with lowest f cost
            Node current = getLowestFCost(notDiscovered);

            // if current node is goal node
            if (current == goal)
            {
                path.add(current);
                while (current != start) {
                    current = current.getParent();
                    current.setObstacle(ObstacleType.PATH);
                    path.add(current);
                }
                var startNode = path.get(path.size() - 1);
                startNode.setObstacle(ObstacleType.SCANDRO);

                if (path.size() == 1)  return path;

                this.start = path.get(path.size() - 2);
                System.out.printf("Start moved to {%d, %d}%n", this.start.getX(), start.getY());
                return path;
            }


            // the open list is a list of nodes that have been discovered but not yet evaluated
            // remove current node from open list
            notDiscovered.remove(current);

            // the closed list is a list of nodes that have been evaluated
            // add current node to closed list
            discoverd.add(current);

            // get all neighbors of current node
            var neighbors = getNeighbors(current, map);

            // for each neighbor
            for (var neighbor : neighbors)
            {
                // if neighbor is in closed list
                if (discoverd.contains(neighbor))
                {
                    // skip to next neighbor
                    continue;
                }


                var currentG = current.getG();
                var distance = getDistance(current, neighbor);
                // calculate g cost
                int g = currentG + distance;


                // if neighbor is not in open list
                if (!notDiscovered.contains(neighbor))
                {
                    // add neighbor to open list
                    notDiscovered.add(neighbor);
                }
                else if (g + distance >= neighbor.getG())
                {
                    // skip to next neighbor
                    continue;
                }


                // set neighbor's parent to current node
                neighbor.setParent(current);

                // set neighbor's g cost to g
                neighbor.setG(g);

                // set neighbor's h cost to h
                neighbor.setH(getHCost(neighbor, goal));

                // set neighbor's f cost to f
                neighbor.setF(neighbor.getG() + neighbor.getH());
            }
        }
        return Collections.emptyList();
    }

    private static int getHCost(Node neighbor, Node goal) {
        return Math.abs(neighbor.getX() - goal.getX()) + Math.abs(neighbor.getY() - goal.getY());
    }

    private static Node getLowestFCost(List<Node> openList) {
        // set lowest f cost to first node in open list
        Node lowestFCost = openList.get(0);

        // for each node in open list
        for (Node node : openList)
        {
            // if node's f cost is less than lowest f cost
            if (node.getF() < lowestFCost.getF())
            {
                // set lowest f cost to node
                lowestFCost = node;
            }
        }

        // return lowest f cost
        return lowestFCost;
    }

    private List<Node> getNeighbors(Node current, Node[][] map) {
        List<Node> neighbors = new ArrayList<>();
        int x = current.getX();
        int y = current.getY();

        if (x > 0) {
            var leftNode = map[x - 1][y];
            if (!leftNode.hasObstacle()) {
                neighbors.add(leftNode);
            }
        }
        if (x < map.length - 1) {
            var rightNode = map[x + 1][y];
            if (!rightNode.hasObstacle()) {
                neighbors.add(rightNode);
            }
        }
        if (y > 0) {
            var topNode = map[x][y - 1];
            if (!topNode.hasObstacle()) {
                neighbors.add(topNode);
            }
        }

        if (y < map[0].length - 1) {
            var bottomNode = map[x][y + 1];
            if (!bottomNode.hasObstacle()) {
                neighbors.add(bottomNode);
            }
        }
        return neighbors;
    }

    public Map reEvaluateMap(int timestep) {
        for (var nodes : map)
        {
            for (var node : nodes)
            {
                if (node.getObstacle() == ObstacleType.BARBER || node.getObstacle() == ObstacleType.PATH)
                {
                    node.setObstacle(ObstacleType.EMPTY);
                }
            }
        }

        var barbers = barberMap.getOrDefault(timestep, List.of());

        for (var coord : barbers)
        {
            var x = coord.x();
            var y = coord.y();

            map[x][y].setObstacle(ObstacleType.BARBER);
        }
        return this;
    }


    private int getDistance(Node a, Node b)
    {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }
}
