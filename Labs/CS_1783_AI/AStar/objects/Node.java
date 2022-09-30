package Labs.CS_1783_AI.AStar.objects;

// A star node
public class Node {
    private int g; // cost from start to current node
    private int h; // heuristic cost from current node to goal
    private int f; // total cost of start to goal going through current node
    private int x; // x coordinate
    private int y; // y coordinate
    private Node parent; // parent node
    private ObstacleType obstacle; // obsticle at this node
    private boolean isGoal; // is this node the goal node

    public Node(Node parent) {
        this.parent = parent;
    }

    public Node(int x, int y, ObstacleType obstacle) {
        this.x = x;
        this.y = y;
        this.obstacle = obstacle;
    }

    public Node() {
    }

    public void setG(int g) {
        this.g = g;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getF() {
        return f;
    }

    public int getG() {
        return g;
    }

    public int getH() {
        return h;
    }

    public boolean hasObstacle() {
        return obstacle == ObstacleType.BARBER || obstacle == ObstacleType.WALL;
    }

    public int getX() {return x;}

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    public ObstacleType getObstacle() {
        return obstacle;
    }

    public int getY() {
        return y;
    }

    public boolean isGoal() {
        return isGoal;
    }

    public void setGoal(boolean b) {
        this.isGoal = b;
    }

    public void setObstacle(ObstacleType obstacle) {
        this.obstacle = obstacle;
    }
}