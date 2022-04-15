package hw2;

import org.junit.Test;

public class testPercolationStats {

    public static void main(String[] args) {
        /*PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(30, 300, pf);
        double m = ps.mean();
        System.out.println(ps.mean());*/

        Percolation p = new Percolation(10);
        p.open(0,0);
        boolean pp = p.isFull(0, 0);
    }
}
