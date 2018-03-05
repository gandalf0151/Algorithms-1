import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


public class BruteCollinearPoints {
  private Point[] points;
  private ArrayList<LineSegment> segmentList = new ArrayList<LineSegment>();
  private int segNum;

  public BruteCollinearPoints(Point[] points) { // finds all line segments containing 4 points
    if (points == null) {
      throw new IllegalArgumentException("Constructor argument Point[] is null!");
    }
    for (int i = 0;i < points.length;i++) {
      if (points[i] == null) {
        throw new IllegalArgumentException("there is null in constructor argument");
      }
    }
    this.points = new Point[points.length];
    // finds all line segments containing 4 points
    for (int i = 0;i < points.length;i++) {
      this.points[i] = points[i];
    }
    Arrays.sort(this.points);
    for (int i = 0;i < this.points.length - 1;i++) {
      if (this.points[i].compareTo(this.points[i + 1]) == 0) {
        throw new IllegalArgumentException("there exists repeated points!");
      }
    }
    findLineSegment(this.points);
  }

  public int numberOfSegments() { // the number of line segments
    return segNum;
  }

  public LineSegment[] segments() { // the line segments
    LineSegment[] segments = new LineSegment[segNum];
    int i = 0;
    for (LineSegment seg : segmentList) {
      segments[i++] = seg;
    }
    return segments;
  }

  private void findLineSegment(Point[] points) {
    int poN = points.length;
    for (int i = 0; i < poN - 3; i++) {
      Comparator<Point> comparator = points[i].slopeOrder();
      for (int j = i + 1; j < poN - 2; j++) {
        if (points[j].compareTo(points[i]) == 0) {
          continue;
        }
        for (int k = j + 1; k < poN - 1; k++) {
          if (points[k].compareTo(points[i]) == 0) {
            continue;
          }
          if (points[k].compareTo(points[j]) == 0) {
            continue;
          }
          if (comparator.compare(points[j], points[k]) == 0) {
            for (int m = k + 1; m < poN; m++) {
              if (points[m].compareTo(points[i]) == 0) {
                continue;
              }
              if (points[m].compareTo(points[j]) == 0) {
                continue;
              }
              if (points[m].compareTo(points[k]) == 0) {
                continue;
              }
              if (comparator.compare(points[k], points[m]) == 0) {
                LineSegment seg = new LineSegment(points[i], points[m]);
                segmentList.add(seg);
              }
            }
          }
        }
      }
    }
    segNum = segmentList.size();
  }

  public static void main(String[] args) {
    // read the n points from a file
    In in = new In("collinear/input9.txt");
    //In in = new In("collinear/input8.txt"); //本地测试使用
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      int x = in.readInt();
      int y = in.readInt();
      points[i] = new Point(x, y);
    }

    // draw the points
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    StdDraw.setPenColor(StdDraw.RED);
    StdDraw.setPenRadius(0.01);
    for (Point p : points) {
      p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
      segment.draw();
    }
    StdDraw.show();
  }
}
