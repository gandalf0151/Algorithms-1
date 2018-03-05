import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private boolean[] mat;
  private int gridSize = 0;
  private WeightedQuickUnionUF qfObj = null;
  private WeightedQuickUnionUF qfObjEx = null;

  private final int[] mx = {-1, 0, 1, 0};

  private final int[] my = {0, -1, 0, 1};

  public Percolation(int n) {  // constructor
    int sq = n * n;
    mat = new boolean[n * n];
    for (int i = 0; i < sq; i++) {
      mat[i] = false;
    }
    this.gridSize = n;
    qfObj = new WeightedQuickUnionUF(gridSize * gridSize + 2);
    qfObjEx = new WeightedQuickUnionUF(gridSize * gridSize + 1);
  }

  public boolean isOpen(int a, int b) { /*check if site(a,b) is open.*/
    if (a < 1 || b < 1 || a > gridSize || b > gridSize) {
      throw new java.lang.IndexOutOfBoundsException();
    }
    int ni = a - 1;
    int nj = b - 1;
    return mat[ni * gridSize + nj];
  }

  public boolean isFull(int a, int b) { /*check if site(a,b) is full*/
    if (a < 1 || b < 1 || a > gridSize || b > gridSize) {
      throw new java.lang.IndexOutOfBoundsException();
    }
    int ni = a - 1;
    int  nj = b - 1;
    return mat[ni * gridSize + nj] && qfObjEx.connected(ni * gridSize + nj, gridSize * gridSize);
  }

  public void open(int a, int b) { // make site(a,b) open.
    if (a < 1 || b < 1 || a > gridSize || b > gridSize) {
      throw new java.lang.IndexOutOfBoundsException();
    }
    int ni = a - 1;
    int nj = b - 1;
    if (!mat[ni * gridSize + nj]) {
      mat[ni * gridSize + nj] = true;
      for (int m = 0; m < 4; m++) {
        int mi = ni + mx[m];
        int mj = nj + my[m];
        if (mi >= 0 && mj >= 0 && mi < gridSize && mj < gridSize && mat[mi * gridSize + mj]) {
          qfObj.union(ni * gridSize + nj, mi * gridSize + mj);
          qfObjEx.union(ni * gridSize + nj, mi * gridSize + mj);
        }
      }
      if (a == 1) {
        qfObj.union(ni * gridSize + nj, gridSize * gridSize);
        qfObjEx.union(ni * gridSize + nj, gridSize * gridSize);
      }
      if (a == gridSize) {
        qfObj.union(ni * gridSize + nj, gridSize * gridSize + 1);
      }
    }
  }

  public boolean percolates() {
    return qfObj.connected(gridSize * gridSize, gridSize * gridSize + 1);
  }

  public int numberOfOpenSites() {
    int count = 0;
    for (int i = 1; i <= gridSize; i++) {
      for (int j = 1; j <= gridSize; j++) {
        if (isOpen(i, j)) {
          count++;
        }
      }
    }
    return count;
  }
}
