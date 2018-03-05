import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import java.util.Comparator;

public class Point implements Comparable<Point> {

  private final int pox;     // x-coordinate of this point
  private final int poy;     // y-coordinate of this point

  /**
   * Initializes a new point.
   *
   * @param  x the <em>x</em>-coordinate of the point
   * @param  y the <em>y</em>-coordinate of the point
   */
  public Point(int x, int y) {
    /* DO NOT MODIFY */
    this.pox = x;
    this.poy = y;
  }

  /**
   * Draws this point to standard draw.
   */
  public void draw() {
    /* DO NOT MODIFY */
    StdDraw.point(pox, poy);
  }

  /**
   * Draws the line segment between this point and the specified point
   * to standard draw.
   *
   * @param that the other point
   */
  public void drawTo(Point that) {
    /* DO NOT MODIFY */
    StdDraw.line(this.pox, this.poy, that.pox, that.poy);
  }

  /**
   * Returns the slope between this point and the specified point.
   * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
   * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
   * +0.0 if the line segment connecting the two points is horizontal;
   * Double.POSITIVE_INFINITY if the line segment is vertical;
   * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
   *
   * @param  that the other point
   * @return the slope between this point and the specified point
   */
  public double slopeTo(Point that) {
    if (this.compareTo(that) == 0) {
      return Double.NEGATIVE_INFINITY;
    } else if (that.poy == this.poy) {
      return +0.0;
    } else if (that.pox == this.pox) {
      return Double.POSITIVE_INFINITY;
    } else {
      return (that.poy - this.poy) / (double) (that.pox - this.pox);
    }
  }

  /**
   * Compares two points by y-coordinate, breaking ties by x-coordinate.
   * Formally, the invoking point (x0, y0) is less than the argument point
   * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
   *
   * @param  that the other point
   * @return the value <tt>0</tt> if this point is equal to the argument
   *         point (x0 = x1 and y0 = y1);
   *         a negative integer if this point is less than the argument
   *         point; and a positive integer if this point is greater than the
   *         argument point
   */
  public int compareTo(Point that) {
    if (this.poy < that.poy) {
      return  -1;
    } else if (this.poy == that.poy && this.pox < that.pox) {
      return -1;
    } else if (this.poy == that.poy && this.pox == that.pox) {
      return  0;
    } else {
      return  1;
    }
  }

  /**
   * Compares two points by the slope they make with this point.
   * The slope is defined as in the slopeTo() method.
   *
   * @return the Comparator that defines this ordering on points
   */
  public Comparator<Point> slopeOrder() {
    return new SlopeOrder();
  }

  private class SlopeOrder implements Comparator<Point> {
    public int compare(Point p1, Point p2) {
      double s1 = slopeTo(p1);
      double s2 = slopeTo(p2);
      return Double.compare(s1,s2);
    }
  }

  /**
   * Returns a string representation of this point.
   * This method is provide for debugging;
   * your program should not rely on the format of the string representation.
   *
   * @return a string representation of this point
   */
  public String toString() {
    /* DO NOT MODIFY */
    return "(" + pox + ", " + poy + ")";
  }

  /**
   * Unit tests the Point data type.
   */
  public static void main(String[] args) {
    Point p1 = new Point(0,0);
    Point p2 = new Point(1,1);
    System.out.println("p1.compareTo(p2)=" + p1.compareTo(p2));
    System.out.println("p1.slopeTo(p2)=" + p1.slopeTo(p2));
    Point p3 = new Point(0, 4);
    System.out.println("p1.slopeTo(p3)=" + p1.slopeTo(p3));
    Point p4 = new Point(4, 4);
    System.out.println("p3.compareTo(p4)=" + p3.compareTo(p4));
    System.out.println("p3.slopeTo(p4)=" + p3.slopeTo(p4));
    Point p5 = new Point(0, 0);
    System.out.println("p1.slopeTo(p5)=" + p1.slopeTo(p5));
  }
}