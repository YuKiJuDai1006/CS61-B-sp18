package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

import static java.lang.Math.sqrt;

public class PercolationStats {
    private Percolation percolation;
    private int N;
    private int T;
    private double[] res;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Sth Minus");
        }
        this.N = N;
        this.T = T;
        res = new double[T];
        run(pf, N, T);
    }   // perform T independent experiments on an N-by-N grid

    private void run(PercolationFactory pf, int N, int T) {
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int index = StdRandom.uniform(N * N);
                p.open(index / N, index % N);
            }
            res[i] = (double) p.size() / (N * N);
        }

    }

    public double mean() {
        return StdStats.mean(res);
    }                                          // sample mean of percolation threshold
    public double stddev() {
        return StdStats.stddev(res);
    }                                        // sample standard deviation of percolation threshold
    public double confidenceLow() {
        double imean = mean();
        double istddev = stddev();
        double confidenceLow = imean - 1.96 * istddev / sqrt(T);
        return confidenceLow;
    }                                 // low endpoint of 95% confidence interval
    public double confidenceHigh() {
        double imean = mean();
        double istddev = stddev();
        double confidenceHigh = imean + 1.96 * istddev / sqrt(T);
        return confidenceHigh;
    }                                // high endpoint of 95% confidence interval
}
