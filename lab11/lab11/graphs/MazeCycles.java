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
        pre[s] = s;
        solveHelper(s);
    }

    // Helper methods go here
    private boolean solveHelper(int v) {
        marked[v] = true;
        announce();

        for (int w : maze.adj(v)) {
            if (marked[w] && w != pre[v]) {
                pre[w] = v;
                return true;
            }
        }
        for (int w : maze.adj(v)) {
            if (!marked[w]) {
                pre[w] = v;
                if (solveHelper(w) == true) {
                    edgeTo[w] = pre[w];
                    announce();
                    return true;
                }
            }
        }
        return false;
    }
}

