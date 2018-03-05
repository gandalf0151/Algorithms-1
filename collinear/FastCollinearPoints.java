import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Quick3way;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.Arrays;




public class FastCollinearPoints {
  private int num = 0;    // number of lines
  private ArrayList<LineSegment> al = null;

  public FastCollinearPoints(Point[] points) {
    if (points == null) {
      throw new NullPointerException();
    }
    Point[] points1 = new Point[points.length];
    System.arraycopy(points, 0, points1, 0, points.length);
    al = new ArrayList<LineSegment>();

    Quick3way.sort(points1);    // 保证所有点都在前一个点的右上方。
    findDuplicate(points1);    // 去除重复点 ， 因为算法本身复杂度较高，加 一个线性复杂度的方法。。无所谓了。。
    for (int i = 0; i < points1.length - 3; i++) {    // 保留最后3个，加上originPoint，正好构成一个4个点的
      if (points1[i] == null) {
        throw new NullPointerException();
      }
      Point[] otherPoints = new Point[points1.length - 1];    // 除了originPoint ,其它点都在这里
      Point originPoint = points1[i];    // 从这点画出连接其它点的射线
      for (int j = 0, k = 0; k < otherPoints.length; j++, k++) {    // initialize otherPoints[]
        if (points1[j] == null) {
          throw new NullPointerException();
        }
        if (j < i) {
          otherPoints[k] = points1[j];
        } else if (j == i) {
          k--;
        } else {
          otherPoints[k] = points1[j];
        }
      }
      Arrays.sort(otherPoints, originPoint.slopeOrder());    // 按照斜率排序
      int tempNum = 1;    // 相同斜率的线条数，一开始肯定有一条，凑够3条就有4个点了，源点 + 3。
      double tempSlope = originPoint.slopeTo(otherPoints[0]);
      for (int j = 1; j < otherPoints.length; j++) {

        if (Double.compare(tempSlope, originPoint.slopeTo(otherPoints[j])) != 0) {
          if (tempNum >= 3) {
            Point[] temp = new Point[tempNum + 1];    // 把能组成直线的点放到temp里面
            temp[0] = originPoint;    // put originPoint into temp[0]
            for (int k = 1; k <= tempNum; k++) {
              temp[k] = otherPoints[j - k];
            }
            int indexOfMin = findMin(temp);    // 找出最小的点，也就是源点
            if (temp[indexOfMin] == originPoint) {    // 如果最小点不是源点，就说明这个条线之前已经统计过了，直接跳过。
              Point head = temp[indexOfMin];
              Point tail = temp[indexOfMin];    // 找到头尾
              for (int l = 1; l < temp.length; l++) {
                if (temp[l].compareTo(head) < 0) {
                  head = temp[l];
                }
                if (temp[l].compareTo(tail) > 0) {
                  tail = temp[l];
                }
              }
              al.add(new LineSegment(head, tail));
              num++;
            }
          }
          tempNum = 1;    // 还原初始条件，并重设斜率
          tempSlope = originPoint.slopeTo(otherPoints[j]);
        } else {
          tempNum++;
        }
      }
      if (tempNum >= 3) {    // 防止末尾的直线没计入，可能最后斜率没变化就默默结束了
        Point[] temp = new Point[tempNum + 1];    // 把能组成直线的点放到temp里面
        temp[0] = originPoint;    // put originPoint into temp[0]
        for (int k = 0; k < tempNum; k++) {
          temp[k + 1] = otherPoints[otherPoints.length - 1 - k];
        }
        int indexOfMin = findMin(temp);
        if (temp[indexOfMin] == originPoint) {
          Point head = temp[indexOfMin];
          Point tail = temp[indexOfMin];    // 找到头尾
          for (int l = 1; l < temp.length; l++) {
            if (temp[l].compareTo(head) < 0) {
              head = temp[l];
            }
            if (temp[l].compareTo(tail) > 0) {
              tail = temp[l];
            }
          }
          al.add(new LineSegment(head, tail));
          num++;
        }
      }
    }

  }


  private int findMin(Point[] temp) {
    int index = 0;
    Point a = temp[0];
    for (int i = 1; i < temp.length; i++) {
      if (a.compareTo(temp[i]) > 0) {
        a = temp[i];
        index = i;
      }
    }
    return index;
  }

  private void findDuplicate(Point[] points1) {
    for (int i = 0; i < points1.length - 1; i++) {
      if (points1[i].compareTo(points1[i + 1]) == 0) {
        throw new IllegalArgumentException();
      }
    }
  }

  public int numberOfSegments() {
    // the number of line segments
    return num;
  }

  public LineSegment[] segments() {
    return al.toArray(new LineSegment[num]);
  }

  public static void main(String[] args) {
    // read the n points from a file
    //In in = new In(args[0]);
    In in = new In("collinear/rs1423.txt"); //本地测试使用
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
    // StdDraw.setPenColor(StdDraw.RED);
    // StdDraw.setPenRadius(0.01);
    for (Point p : points) {
      p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
      segment.draw();
    }
    StdDraw.show();
  }
}