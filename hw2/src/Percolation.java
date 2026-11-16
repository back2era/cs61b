import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // TODO: Add any necessary instance variables.
    private boolean[][] grid;
    private int opened;
    private WeightedQuickUnionUF uf;
    private int virtualTop;
    private int N;
    public Percolation(int N) {
        // TODO: Fill in this constructor.
        grid = new boolean[N][N];
        this.N = N;
        virtualTop = N * N;
        uf = new WeightedQuickUnionUF(N*N +1);
        opened = 0;
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        int[][] directions = {
                {-1, 0},
                {1, 0},
                {0, -1},
                {0, 1}
        };

        if (!grid[row][col]) {
            grid[row][col] = true;
            opened++;
            int index = row * N + col;
            if (row == 0) {
                uf.union(index, virtualTop);
            } else {
                for (int[] d : directions) {
                    int newRow = row + d[0];
                    int newCol = col + d[1];
                    if (newRow < 0 || newRow >= N ||
                            newCol < 0 || newCol >= N) {
                        continue;
                    }
                    // 如果相邻格子已经开放，就合并
                    if (isOpen(newRow, newCol)) {
                        int current = row * N + col;
                        int neighbor = newRow * N + newCol;

                        uf.union(current, neighbor);
                    }
                }
            }
        }
    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        if (!isOpen(row, col)){
            return false;
        }
        int index = row * N + col;

        return uf.find(index) == uf.find(virtualTop);
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return opened;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        int row = N;
        for (int col = 0; col < N; col++) {
            int index = row * (N-1) + col;
            if (uf.find(index) == uf.find(virtualTop)){
                return true;
            }
        }
        return false;
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.
}


