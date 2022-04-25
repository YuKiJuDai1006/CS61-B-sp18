package byog.Core;

import byog.TileEngine.TETile;

import java.util.List;

public class Connector {
    private Position goalPos;
    private Direction direction;

    public Connector(Position goalPos, Direction direction) {
        this.goalPos =goalPos;
        this.direction = direction;
    }

    public Position getGoalPos() {
        return goalPos;
    }

    public Direction getDirection() {
        return direction;
    }

    public static void addConnectorToList(List<Connector> list, Position pos, Direction dir,
                                          int WIDTH, int HEIGHT, TETile[][]world, TETile t) {
        switch (dir) {
            case UP:
                if (pos.getY() + 2 < HEIGHT && world[pos.getX()][pos.getY() + 2] == t) {
                    Connector con = new Connector(new Position(pos.getX(), pos.getY() + 2), dir);
                    list.add(con);
                }
                break;
            case DOWN:
                if (pos.getY() - 2 > 0 && world[pos.getX()][pos.getY() - 2] == t) {
                    Connector con = new Connector(new Position(pos.getX(), pos.getY() - 2), dir);
                    list.add(con);
                }
                break;
            case LEFT:
                if (pos.getX() - 2 > 0 && world[pos.getX() - 2][pos.getY()] == t) {
                    Connector con = new Connector(new Position(pos.getX() - 2, pos.getY()), dir);
                    list.add(con);
                }
                break;
            case RIGHT:
                if (pos.getX() + 2 < WIDTH && world[pos.getX() + 2][pos.getY()] == t) {
                    Connector con = new Connector(new Position(pos.getX() + 2, pos.getY()), dir);
                    list.add(con);
                }
                break;
        }
    }

    public void connect(TETile[][] world, TETile t) {
        switch (direction) {
            case UP:
                world[goalPos.getX()][goalPos.getY() - 1] = t;
                break;
            case DOWN:
                world[goalPos.getX()][goalPos.getY() + 1] = t;
                break;
            case LEFT:
                world[goalPos.getX() + 1][goalPos.getY()] = t;
                break;
            case RIGHT:
                world[goalPos.getX() - 1][goalPos.getY()] = t;
                break;
        }
    }
}
