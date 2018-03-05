import java.util.ArrayList;

public class Board {
  private static final int BLANK = 0;
  private final  int diN;
  private int[][] blocks;

  public Board(int[][] inBlocks) { // construct a board from an n-by-n array of blocks
    diN = inBlocks.length;
    blocks = new int[diN][diN];
    copy(blocks,inBlocks);
  }

  private void copy(int[][] toBlocks,int[][] fromBlocks) {
    for (int row = 0; row < diN; row++) {
      for (int col = 0; col < diN; col++) {
        toBlocks[row][col] = fromBlocks[row][col];
      }
    }
  }

  // (where blocks[i][j] = block in row i, column j)
  public int dimension() { // board dimension n
    return  diN;
  }

  private int getRow(int value) {
    return (value - 1) / diN;
  }

  private int getCol(int value) {
    return (value - 1) % diN;
  }

  private int getValue(int row,int col) {
    return row * diN + col + 1;
  }

  public int hamming() { // number of blocks out of place
    int hamming = 0;
    for (int row = 0; row < diN; row++) {
      for (int col = 0; col < diN; col++) {
        if (blocks[row][col] != BLANK && blocks[row][col] != getValue(row, col)) {
          hamming++;
        }
      }
    }
    return hamming;
  }

  public int manhattan()  {  // sum of Manhattan distances between blocks and goal
    int manhattan = 0;
    for (int row = 0; row < diN; row++) {
      for (int col = 0; col < diN; col++) {
        if (blocks[row][col] != BLANK && blocks[row][col] != getValue(row, col)) {
          manhattan += Math.abs(getRow(blocks[row][col]) - row)
                  + Math.abs(getCol(blocks[row][col]) - col);
        }
      }
    }
    return manhattan;
  }

  public boolean isGoal() { // is this board the goal board?
    return this.hamming() == 0;
  }

  public Board twin() {   // a board that is obtained by exchanging any pair of blocks
    Board twinBoard = new Board(blocks);
    int firRow = 0;
    int firCol = 0;
    if (blocks[firRow][firCol] == BLANK) {
      firCol++;
    }
    for (int row = 0; row < diN; row++) {
      for (int col = 0; col < diN; col++) {
        if (blocks[row][col] != blocks[firRow][firCol] && blocks[row][col] != BLANK) {
          twinBoard.swap(firRow, firCol, row, col);
          return twinBoard;
        }
      }
    }
    return twinBoard;
  }

  private void swap(int fiR, int fiC, int orR, int orC) {
    int t = blocks[fiR][fiC];
    blocks[fiR][fiC] = blocks[orR][orC];
    blocks[orR][orC] = t;
  }

  public boolean equals(Object y) { // does this board equal y?
    if (y == null) {
      return false;
    }
    if (y == this) {
      return true;
    }
    if (y.getClass().isInstance(this)) {
      Board yb = (Board) y;
      if (yb.diN != this.diN) {
        return false;
      } else {
        for (int row = 0; row < diN; row++) {
          for (int col = 0; col < diN; col++) {
            if (yb.blocks[row][col] != blocks[row][col]) {
              return false;
            }
          }
        }
        return true;
      }
    } else {
      return false;
    }
  }

  public Iterable<Board> neighbors() {  // all neighboring boards
    ArrayList<Board> neighbors = new ArrayList<Board>();
    for (int row = 0; row < diN; row++) {
      for (int col = 0; col < diN; col++) {
        if (blocks[row][col] == BLANK) {
          // 空白的位置分别与上下左右的元素交换一次位置就得到一个邻居board
          // 与上方元素互换
          if (row > 0) {
            Board neighborT = new Board(blocks);
            neighborT.swap(row, col, row - 1, col);
            neighbors.add(neighborT);
          }
          // 与下方元素互换
          if (row < diN - 1) {
            Board neighborB = new Board(blocks);
            neighborB.swap(row, col, row + 1, col);
            neighbors.add(neighborB);
          }
          // 与左边元素互换
          if (col > 0) {
            Board neighborL = new Board(blocks);
            neighborL.swap(row, col, row, col - 1);
            neighbors.add(neighborL);
          }
          // 与右边元素互换
          if (col < diN - 1) {
            Board neighborR = new Board(blocks);
            neighborR.swap(row, col, row, col + 1);
            neighbors.add(neighborR);
          }
        }
      }
    }
    return neighbors;
  }

  public String toString() { // string representation of this board
    StringBuilder sb = new StringBuilder();
    sb.append(diN + "\n");
    for (int row = 0; row < diN; row++) {
      for (int col = 0; col < diN; col++) {
        sb.append(String.format("%2d ", blocks[row][col]));
      }
      sb.append("\n");
    }
    return sb.toString();
  }

  public static void main(String[] args) { // unit tests (not graded)
    int[][] test = {{0, 1}, {2, 3 }};
    Board b = new Board(test);
    System.out.println(b);
    System.out.println(b.hamming());
    System.out.println(b.manhattan());
  }
}
