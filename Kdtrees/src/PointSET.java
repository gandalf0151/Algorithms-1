import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.TreeSet;

public class PointSET {
  private TreeSet<Point2D> pointSet;

  public PointSET() { // construct an empty set of points
    this.pointSet = new TreeSet<Point2D>();
  }

  public boolean isEmpty() { // is the set empty?
    return size() == 0;
  }

  public int size() { // number of points in the set
    return pointSet.size();
  }

  public void insert(Point2D p) { // add the point to the set (if it is not already in the set)
    pointSet.add(p);
  }

  public boolean contains(Point2D p) { // does the set contain point p?
    return pointSet.contains(p);
  }

  public void draw() { // draw all points to standard draw
    for (Point2D p : pointSet) {
      StdDraw.point(p.x(),p.y());
    }
  }

  public Iterable<Point2D> range(RectHV rect) { // all points that are inside the rectangle
    TreeSet<Point2D> rangeSet = new TreeSet<Point2D>();
    for (Point2D p : pointSet) {
      if (rect.contains(p)) {
        rangeSet.add(p);
      }
    }
    return rangeSet;
  }

  public Point2D nearest(Point2D p) { // a nearest neighbor in the se
    // t to point p null if the set is empty
    Point2D nearestPoint2D = null;
    double distance = Double.MAX_VALUE;

    if (pointSet.isEmpty()) {
      return nearestPoint2D;
    }

    for (Point2D pointIter : pointSet) {
      if (pointIter.distanceTo(p) < distance) {
        nearestPoint2D = pointIter;
        distance = pointIter.distanceTo(p);
      }
    }
    return nearestPoint2D;
  }

}