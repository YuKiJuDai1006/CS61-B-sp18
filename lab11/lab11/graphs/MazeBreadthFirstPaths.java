package lab11.graphs;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs(int v) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(v);
        marked[v] = true;
        announce();

        while (!q.isEmpty()) {
            int temp = q.remove();
            if (temp == t) {
                return;
            }
            for (int w : maze.adj(temp)) {
                if (!marked[w]) {
                    edgeTo[w] = temp;
                    distTo[w] = distTo[temp] + 1;
                    marked[w] = true;
                    q.add(w);
                    announce();
                }
            }
        }

    }


    @Override
    public void solve() {
        bfs(s);
    }
}

