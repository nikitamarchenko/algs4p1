/**
 * Created by tpuctah on 15.02.15.
 */

import java.util.Random;


public class PercolationStats {

    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;

    public PercolationStats(int N, int T)
    {
        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException();

        double[] result = new double[T];


        for (int i = 0; i < T; i++)
        {
            Percolation p = new Percolation(N);
            for (int j = 1; j <= N*N; j++)
            {
                boolean open = false;

                while (true)
                {
                    int cellI = randInt(1, N);
                    int cellJ = randInt(1, N);

                    if (!p.isOpen(cellI, cellJ))
                    {
                        p.open(cellI, cellJ);
                        break;
                    }
                }

                if (p.percolates())
                {
                    double dj = j;
                    result[i] = dj / (N * N);
                    break;
                }
            }
        }

        double sum = 0;

        for (double i : result)
            sum += i;

        mean = sum / T;


        double standardDeviation = 0;

        for (double i : result)
            standardDeviation += (i - mean) * (i - mean);

        stddev = Math.sqrt(standardDeviation/(T - 1));

        confidenceLo = mean - ((1.96 * stddev) / Math.sqrt(T));
        confidenceHi = mean + ((1.96 * stddev) / Math.sqrt(T));
    }

    /**
     * Returns a pseudo-random number between min and max, inclusive.
     * The difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimum value
     * @param max Maximum value.  Must be greater than min.
     * @return Integer between min and max, inclusive.
     * @see java.util.Random#nextInt(int)
     */
    private static int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }



    public double mean()
    {
        return mean;
    }

    public double stddev()
    {
        return stddev;
    }

    public double confidenceLo()
    {

        return confidenceLo;
    }

    public double confidenceHi()
    {
        return confidenceHi;
    }

    public static void main(String[] args)
    {
        if (args.length != 2)
            throw new IllegalArgumentException();

        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);


        PercolationStats ps = new PercolationStats(N, T);

        StdOut.println("mean                    = " + ps.mean);
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = "
                + ps.confidenceLo() + ", " + ps.confidenceHi());

    }
}
