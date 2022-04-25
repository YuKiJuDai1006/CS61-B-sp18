package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private Position bottomLeft;
    private Position upRight;

    private static int roomMaxNum = 20;
    private static int roomMaxWidth = 8;
    private static int roomMaxHeight = 6;

    public Room(Position bottomLeft, Position upRight) {
        this.bottomLeft = bottomLeft;
        this.upRight = upRight;
    }

    public int getWidth() {
        return upRight.getX() - bottomLeft.getX();
    }

    public int getHeight() {
        return upRight.getY() - bottomLeft.getY();
    }

    public boolean isOverlapped(Room other) {
        int xCentreDiff = Math.abs(bottomLeft.getX() - other.bottomLeft.getX());
        int yCentreDiff = Math.abs(bottomLeft.getY() - other.bottomLeft.getY());

        boolean isXOver = xCentreDiff <= getWidth() / 2 + other.getWidth() / 2 + 2;
        boolean isYOver = yCentreDiff <= getHeight() / 2 + other.getHeight() / 2 + 2;
        return isXOver && isYOver;
    }

    public boolean isOverlapped(List<Room> rooms) {
        if (rooms.isEmpty()) {
            return false;
        }
        for (Room room : rooms) {
            if (this.isOverlapped(room)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isLegal(Room r) {
        boolean isXLegal = r.bottomLeft.getX() < r.upRight.getX();
        boolean isYLegal = r.bottomLeft.getY() < r.upRight.getY();
        boolean isWidthLegal = r.getWidth() <= Room.roomMaxWidth;
        boolean isHeightLegal = r.getHeight() <= Room.roomMaxHeight;
        return isXLegal && isYLegal && isWidthLegal && isHeightLegal;
    }

    public void drawRoom(TETile[][] world, TETile t) {
        for (int i = bottomLeft.getX(); i <= upRight.getX(); i++) {
            for (int j = bottomLeft.getY(); j <= upRight.getY(); j++) {
                world[i][j] = t;
            }
        }
    }

    public List<Connector> addLegalConnector(TETile[][] world, int WIDTH, int HEIGTH) {
        List<Connector> res = new ArrayList<>();
        for (int i = bottomLeft.getX(); i <= upRight.getX(); i++) {
            Position pH = new Position(bottomLeft.getX() + i, bottomLeft.getY());
            Connector.addConnectorToList(res, pH, Direction.DOWN, WIDTH, HEIGTH, world, Tileset.FLOOR);
        }
        for (int i = bottomLeft.getX(); i <= upRight.getX(); i++) {
            Position pH = new Position(bottomLeft.getX() + i, upRight.getY());
            Connector.addConnectorToList(res, pH, Direction.UP, WIDTH, HEIGTH, world, Tileset.FLOOR);
        }
        for (int j = bottomLeft.getY(); j <= upRight.getY(); j++) {
            Position pV = new Position(bottomLeft.getX(), bottomLeft.getY() + j);
            Connector.addConnectorToList(res, pV, Direction.LEFT, WIDTH, HEIGTH, world, Tileset.FLOOR);
        }
        for (int j = bottomLeft.getY(); j <= upRight.getY(); j++) {
            Position pV = new Position(upRight.getX(), bottomLeft.getY() + j);
            Connector.addConnectorToList(res, pV, Direction.RIGHT, WIDTH, HEIGTH, world, Tileset.FLOOR);
        }
        return res;
    }

    public static void setRoomMaxNum(int roomMaxNum) {
        Room.roomMaxNum = roomMaxNum;
    }
}
