package Labs.CS_1783_AI.AStar.objects;

public enum ObstacleType {
    // '\u200E'
    EMPTY(' '),
    WALL('W'),
    SCANDRO('S'),
    GOAL('G'),
    PATH('*'),
    BARBER('B');

    private char symbol;

    ObstacleType(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
