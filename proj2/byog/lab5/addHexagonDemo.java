package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Random;

public class addHexagonDemo {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;
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

     public static void main(String[] args) {
         TERenderer ter = new TERenderer();
         ter.initialize(WIDTH, HEIGHT);

         Position p = new Position(4, 4);
         TETile t = Tileset.FLOWER;

         TETile[][] world = new TETile[WIDTH][HEIGHT];
         for (int x = 0; x < WIDTH; x += 1) {
             for (int y = 0; y < HEIGHT; y += 1) {
                 world[x][y] = Tileset.NOTHING;
             }
         }

         addHexagon(world, p, 3, t);

         ter.renderFrame(world);
     }
}
