package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    private int size = 0;
    private int[] universe;
    private WeightedQuickUnionUF WQU;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("M minus");
        }
        this.N = N;
        universe = new int[N * N];
        WQU = new WeightedQuickUnionUF(N * N);
    }               // create N-by-N grid, with all sites initially blocked


    public void open(int row, int col) {
        if (row > N - 1 || col > N - 1 || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException("Sth Out Of Bounds");
        }
        int index = row * N + col;
        if (!isOpen(row, col)) {
            universe[index] = 1;
            size++;
            tryUnionSite(index, index - N);
            tryUnionSite(index, index - 1);
            tryUnionSite(index, index + 1);
            tryUnionSite(index, index + N);
        }
    }    // open the site (row, col) if it is not open already

    public boolean isValued(int n1, int n2) {
        if (n2 < 0 || n2 > N * N - 1) {
            return false;
        }
        if (!isOpen(n2 / N, n2 % N)) {
            return false;
        }
        if (n1 + 1 == n2 && n2 % N == 0) {
            return false;
        }
        if (n1 - 1 == n2 && n1 % N == 0) {
            return false;
        }
        return true;
    }

    public void tryUnionSite(int n1, int n2) {
        if (isValued(n1, n2)) {
            WQU.union(n1, n2);
        }
    }

    public boolean isOpen(int row, int col) {
        if (row > N - 1 || col > N - 1 || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException("Sth Out Of Bounds");
        }
        int index = row * N + col;
        return universe[index] == 1;
    }  // is the site (row, col) open?

    public boolean isFull(int row, int col) {
        if (row > N - 1 || col > N - 1 || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException("Sth Out Of Bounds");
        }
        if (!isOpen(row, col)) {
            return false;
        }
        int index = row * N + col;
        for (int i = 0; i < N; i++) {
            if (!isOpen(0, i)) {
                continue;
            }
            if (WQU.connected(i, index)) {
                return true;
            }
        }
        return false;
    }  // is the site (row, col) full?

    public int numberOfOpenSites() {
        return size;
    }          // number of open sites

    public boolean percolates() {
        for (int i = 0; i < N; i++) {
            if (isFull(N - 1, i)) {
                return true;
            }
        }
        return false;
    }             // does the system percolate?

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(10);
        p.open(0, 1);
        boolean pp = p.isFull(0, 1);

    }   // use for unit testing (not required)

}
