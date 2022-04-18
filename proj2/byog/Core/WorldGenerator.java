package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class WorldGenerator {
    private static final int WIDTH= 50;
    private static final int HEIGHT = 30;
    private static final long SEED = 2001;
    private static final Random RANDOM = new Random(SEED);

    private static class Position {
        int x;
        int y;
        public Position(int xpos, int ypos) {
            x = xpos;
            y = ypos;
        }
    }

    public static void DefaultWorld(TETile[][] world) {
        for (int x = 0; x < world.length; x += 1) {
            for (int y = 0; y < world[0].length; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    public static void YWallsGenerate(TETile[][] world, int height, Position p){
        for (int i = 0; i < height; i++) {
            int xCoord = p.x;
            int yCoord = p.y + i;
            world[xCoord][yCoord] = Tileset.WALL;
        }
    }

    public static void XWallsGenerate(TETile[][] world, int width, Position p){
        for (int i = 0; i < width; i++) {
            int xCoord = p.x + i;
            int yCoord = p.y;
            world[xCoord][yCoord] = Tileset.WALL;
        }
    }

    public static void FloorGenerate(TETile[][] world, int width, int height, Position p){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++) {
                int xCoord = p.x + i;
                int yCoord = p.y + j;
                world[xCoord][yCoord] = Tileset.FLOOR;
            }
        }
    }

    public static void YHallwayGenerate(TETile[][] world, int height, Position p){
        Position Pleft = new Position(p.x - 1, p.y);
        Position Pright = new Position(p.x + 1, p.y);

        YWallsGenerate(world, height, Pleft);
        YWallsGenerate(world, height, Pright);
        FloorGenerate(world, 1, height, p);
    }

    public static void XHallwayGenerate(TETile[][] world, int width, Position p){
        Position Pup = new Position(p.x, p.y + 1);
        Position Pdown = new Position(p.x, p.y - 1);

        XWallsGenerate(world, width, Pup);
        XWallsGenerate(world, width, Pdown);
        FloorGenerate(world, width, 1, p);
    }

    public static void RoomGenerate(TETile[][] world, int width, int height, Position p){
        Position Prightwall = new Position(p.x + width - 1, p.y);
        Position Pupwall = new Position(p.x, p.y + height - 1);
        Position Pfloor = new Position(p.x + 1, p.y + 1);

        YWallsGenerate(world, height, p);
        YWallsGenerate(world, height, Prightwall);
        XWallsGenerate(world, width, p);
        XWallsGenerate(world, width, Pupwall);
        FloorGenerate(world, width - 2, height - 2, Pfloor);
    }

    public static void AllRoomGenerate(TETile[][] world) {
        int nums = RandomUtils.uniform(RANDOM, 1, 5);
        for (int i = 0; i < nums; i++) {
            int xCoord = RandomUtils.uniform(RANDOM, 0, 50);
            int yCoord = RandomUtils.uniform(RANDOM, 0, 30);
            Position p = new Position(xCoord, yCoord);
            int width = RandomUtils.uniform(RANDOM, 3, 5);
            int height = RandomUtils.uniform(RANDOM, 3, 5);
            RoomGenerate(world, width, height, p);
        }
    }

    public static void AllHallwayGenerate(TETile[][] world) {
        int nums = RandomUtils.uniform(RANDOM, 1, 5);
        for(int i = 0; i < nums; i++) {
            if(RandomUtils.bernoulli(RANDOM)) {
                int height = RandomUtils.uniform(RANDOM, 1, 10);
                int xCoord = RandomUtils.uniform(RANDOM, 0, 50);
                int yCoord = RandomUtils.uniform(RANDOM, 0, 30);
                Position p = new Position(xCoord, yCoord);
                YHallwayGenerate(world, height, p);
            } else {
                int width = RandomUtils.uniform(RANDOM, 1, 10);
                int xCoord = RandomUtils.uniform(RANDOM, 0, 50);
                int yCoord = RandomUtils.uniform(RANDOM, 0, 30);
                Position p = new Position(xCoord, yCoord);
                XHallwayGenerate(world, width, p);
            }
        }
    }

    public static void main(String[] args){
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        DefaultWorld(world);

        AllRoomGenerate(world);
        AllHallwayGenerate(world);

        ter.renderFrame(world);
    }

}
