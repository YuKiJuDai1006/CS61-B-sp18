package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s = 0;
    private int[] pre;

    public MazeCycles(Maze m) {
        super(m);
        pre = new int[m.V()];
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        solveHelper(s);
    }

    // Helper methods go here
    private void solveHelper(int v) {
        marked[v] = true;
        announce();

        for (int w : maze.adj(v)) {
            if (marked[w] && pre[w] != v) {
                announce();
                return;
            }
            if (!marked[w]) {
                pre[w] = v;
                announce();
                solveHelper(w);
            }

        }
    }
}

