/**
 * Created by tpuctah on 15.02.15.
 */



public class Percolation {

    private int n;
    private boolean[] open;
    private boolean[] full;
    private boolean isPercolates;
    private WeightedQuickUnionUF qu;

    public Percolation(int N)
    {
        if (N <= 0)
            throw new IllegalArgumentException();

        n = N;
        open = new boolean[(N * N) + 1 + N + 1];
        open[getTop()] = true;
        open[getBottom()] = true;
        full = new boolean[(N * N)];
        qu = new WeightedQuickUnionUF((N * N) + 1 + 1);
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

    private int getBottom()
    {
        return n * n + 1;
    }

    private void reqursiveFull(int i, int j)
    {
        int cell = getOffset(i, j);
        int[][] shift = {{+1, 0}, {-1, 0}, {0, +1}, {0, -1}};

        if (!full[cell]) {
            for (int[] x : shift) {
                try {
                    int nextCell = getOffset(i + x[0], j + x[1]);
                    if (open[nextCell] && full[nextCell]) {
                        full[cell] = true;
                        break;
                    }
                } catch (IndexOutOfBoundsException ex) {
                    continue;
                }
            }
        }

        if (full[cell])
        {
            for (int[] x : shift) {
                try {
                    int nextCell = getOffset(i + x[0], j + x[1]);
                    if (open[nextCell] && !full[nextCell]) {
                        reqursiveFull(i + x[0], j + x[1]);
                    }
                } catch (IndexOutOfBoundsException ex) {
                    continue;
                }
            }
        }
    }

    public void open(int i, int j)
    {
        int cell = getOffset(i, j);

        if (open[cell])
            return;

        open[cell] = true;

        if (i == 1)
        {
            qu.union(getTop(), cell);
            full[cell] = true;
        }
        if (i == n)
        {
            qu.union(getBottom(), cell);
        }

        int[][] shift = {{+1, 0}, {-1, 0}, {0, +1}, {0, -1}};

        for (int[] x : shift)
        {
          try
          {
              if (isOpen(i + x[0], j + x[1]))
              {
                  qu.union(cell, getOffset(i + x[0], j + x[1]));
              }
          }
          catch (IndexOutOfBoundsException ex)
          {
              continue;
          }
        }

        reqursiveFull(i, j);
    }

    public boolean isOpen(int i, int j)
    {
        int cell = getOffset(i, j);
        return open[cell];
    }

    public boolean isFull(int i, int j)
    {
        int cell = getOffset(i, j);
        if (!open[cell])
            return false;

        return full[cell];
    }

    public boolean percolates()
    {
        if (isPercolates)
        {
            return true;
        }

        if (qu.connected(getTop(), getBottom()))
        {
            isPercolates = true;
            return true;
        }

        return false;
    }
}
