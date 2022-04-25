package byog.lab5;

import byog.Core.Position;
import byog.Core.Room;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.junit.Test;

import java.util.Random;

public class addHexagonDemo {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 70;
    private static final long SEED = 287312;
    private static final Random RANDOM = new Random(SEED);

    /**
     * Computes the width of row i for a size s hexagon.
     * @param s The size of the hex.
     * @param i The row number where i = 0 is the bottom row.
     * @return
     */
    public static int hexRowWidth(int s, int i) {
        if (i > s - 1) {
            i = 2 * s - 1 - i;
        }
        return s + 2 * i;
    }

    /**
     * Computesrelative x coordinate of the leftmost tile in the ith
     * row of a hexagon, assuming that the bottom row has an x-coordinate
     * of zero. For example, if s = 3, and i = 2, this function
     * returns -2, because the row 2 up from the bottom starts 2 to the left
     * of the start position, e.g.
     *   xxxx
     *  xxxxxx
     * xxxxxxxx
     * xxxxxxxx <-- i = 2, starts 2 spots to the left of the bottom of the hex
     *  xxxxxx
     *   xxxx
     *
     * @param s size of the hexagon
     * @param i row num of the hexagon, where i = 0 is the bottom
     * @return
     */
    public static int hexRowOffset(int s, int i) {
        if (i > s - 1) {
            i = 2 * s - 1 - i;
        }
        return -i;
    }

    /** Adds a row of the same tile.
     * @param world the world to draw on
     * @param p the leftmost position of the row
     * @param width the number of tiles wide to draw
     * @param t the tile to draw
     */
     public static void addRow(TETile[][] world, Position p, int width, TETile t) {
         for (int xi = 0; xi < width; xi += 1) {
             int xCoord = p.x + xi;
             int yCoord = p.y;
             world[xCoord][yCoord] = t;
         }
     }

    /**
     * Adds a hexagon to the world.
     * @param world the world to draw on
     * @param p the bottom left coordinate of the hexagon
     * @param s the size of the hexagon
     * @param t the tile to draw
     */
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {

        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }

        // hexagons have 2*s rows. this code iterates up from the bottom row,
        // which we call row 0.
        for (int yi = 0; yi < 2 * s; yi += 1) {
            int thisRowY = p.y + yi;

            int xRowStart = p.x + hexRowOffset(s, yi);
            Position rowStartP = new Position(xRowStart, thisRowY);

            int rowWidth = hexRowWidth(s, yi);

            addRow(world, rowStartP, rowWidth, t);

        }
    }

     private static class Position {
         int x;
         int y;
         public Position(int xpos, int ypos) {
             x = xpos;
             y = ypos;
         }
     }

     public static void drawRandomVerticalHexes(int columnTh, Position startP, TETile[][] world, int s) {
        Position start = bottomLeftNeighbor(startP, columnTh, s);
        int num = columnTh < s ? s + columnTh : 3 * s - columnTh - 2;
        for (int i = 0; i < num; i ++) {
            TETile t = randomTile();
            Position p = new Position(start.x, start.y + 2 * s * i);
            addHexagon(world, p, s, t);
        }
     }

     private static Position bottomLeftNeighbor(Position startP, int columnTh, int s) {
        int xPos = startP.x + (2 * s - 1) * columnTh;
        int yPos = startP.y + (columnTh < s ? -columnTh * s : (columnTh - 2 * s + 2) * s);
        Position res= new Position(xPos, yPos);
        return res;
     }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(4);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.SAND;
            case 3: return Tileset.GRASS;
            default: return Tileset.NOTHING;
        }
    }


     public static void main(String[] args) {
         TERenderer ter = new TERenderer();
         ter.initialize(WIDTH, HEIGHT);
         int s = 4;
         Position startP = new Position(15, 15);
         TETile t = Tileset.FLOWER;

         TETile[][] world = new TETile[WIDTH][HEIGHT];
         for (int x = 0; x < WIDTH; x += 1) {
             for (int y = 0; y < HEIGHT; y += 1) {
                 world[x][y] = Tileset.NOTHING;
             }
         }

         /*drawRandomVerticalHexes(0, startP, world, s);
         drawRandomVerticalHexes(1, startP, world, s);
         drawRandomVerticalHexes(2, startP, world, s);
         drawRandomVerticalHexes(3, startP, world, s);
         drawRandomVerticalHexes(4, startP, world, s);
         drawRandomVerticalHexes(5, startP, world, s);
         drawRandomVerticalHexes(6, startP, world, s);*/

         byog.Core.Position p1 = new byog.Core.Position(10, 10);
         byog.Core.Position p2 = new byog.Core.Position(40, 40);
         Room wxy = new Room(p1, p2);
         wxy.drawRoom(world, t);

         ter.renderFrame(world);
     }
}
