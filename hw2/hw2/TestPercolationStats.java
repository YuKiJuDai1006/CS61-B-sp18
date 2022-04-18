package hw2;

public class TestPercolationStats {

    public static void main(String[] args) {
        /*
        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(30, 300, pf);
        double m = ps.mean();
        System.out.println(ps.mean());

        Percolation p = new Percolation(10);
        p.open(0,0);
        boolean pp = p.isFull(0, 0);*/

        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(20, 10, pf);
        double std = ps.stddev();
        System.out.println(std);
    }
}
