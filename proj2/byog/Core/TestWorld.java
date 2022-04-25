package byog.Core;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import java.util.Random;

public class TestWorld {
    private static final int SEED = 888;
    private static final Random RANDOM = new Random(SEED);
    @Test
    public void testRoomIsOverLapped(){
        Position bL = new Position(1, 1);
        Position uP = new Position(4, 4);
        Position bLL = new Position(3, 0);
        Position uRR = new Position(8, 8);
        Room room = new Room(bL, uP);
        Room other = new Room(bLL, uRR);

        assertTrue(room.isOverlapped(other));
    }

    public static void main(String[] args) {

    }
}
