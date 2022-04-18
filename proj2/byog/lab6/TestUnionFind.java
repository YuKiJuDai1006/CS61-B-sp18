package byog.lab6;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestUnionFind {
    private UnionFind uf = new UnionFind(9);

    @Test(timeout = 1000)
    public void testUnion() {
        uf.union(1, 0);
        uf.union(2, 3);
        uf.union(2, 4);
        uf.union(4, 5);
        uf.union(6, 7);
        uf.union(7, 8);
        uf.union(1, 8);
        uf.union(1, 4);
        int expected = 7;
        int actual = uf.parent(1);
        assertEquals(expected, actual);

    }
}
