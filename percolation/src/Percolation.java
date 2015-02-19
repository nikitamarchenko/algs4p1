/**
 * Created by tpuctah on 15.02.15.
 */



public class Percolation {

    private int n;
    private boolean[] open;
    private boolean isPercolates;
    private WeightedQuickUnionUF qu;

    public Percolation(int N)
    {
        if (N <= 0)
            throw new IllegalArgumentException();

        n = N;
        open = new boolean[(N * N) + 1 + N];
        open[getTop()] = true;
        for (int i = 1; i <= N; i++)
        {
            open[getBottom(i)] = true;
        }

        qu = new WeightedQuickUnionUF((N * N) + 1 + N);
    }

    private int transformCoords(int x)
    {
        if (x <= 0 || x > n)
            throw new IndexOutOfBoundsException();

        return x - 1;
    }

    private int getOffset(int i, int j)
    {
        int ii = transformCoords(i);
        int jj = transformCoords(j);

        return ii * n + jj;
    }

    private int getTop()
    {
        return n * n;
    }

    private int getBottom(int j)
    {
        int jj = transformCoords(j);
        return n * n + 1 + jj;
    }

    private boolean connected(int p, int q)
    {
        return qu.connected(p, q);
    }

    private void union(int p, int q)
    {
        qu.union(p, q);
    }

    public void open(int i, int j)
    {
        int cell = getOffset(i, j);
        open[cell] = true;

        int[][] shift = {{+1, 0}, {-1, 0}, {0, +1}, {0, -1}};

        for (int[] x : shift)
        {
          try
          {
              if (isOpen(i + x[0], j + x[1]))
              {
                  union(cell, getOffset(i + x[0], j + x[1]));
              }
          }
          catch (IndexOutOfBoundsException ex)
          {
              continue;
          }
        }

        if (i == 1)
        {
            union(getTop(), cell);
        }
        if (i == n)
        {
            union(getBottom(j), cell);
        }
    }

    public boolean isOpen(int i, int j)
    {
        int cell = getOffset(i, j);
        return open[cell];
    }

    public boolean isFull(int i, int j)
    {
        return connected(getTop(), getOffset(i, j));
    }

    public boolean percolates()
    {
        if (isPercolates)
        {
            return true;
        }

        for (int i = 1; i <= n; i++)
        {
            if (connected(getTop(), getBottom(i)))
            {
                isPercolates = true;
                return true;
            }
        }
        return false;
    }
}
