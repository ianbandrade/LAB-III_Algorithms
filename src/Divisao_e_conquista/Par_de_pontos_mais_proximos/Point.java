package Divisao_e_conquista.Par_de_pontos_mais_proximos;

public class Point {

  private static final double MIN_MAX = 1000000000;
  protected int x, y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  private int compareX(Point p1, Point p2) {
    return (p1.x - p2.y);
  }

  private int compareY(Point p1, Point p2) {
    return (p1.y - p2.y);
  }

  private double dist(Point p1, Point p2) {
    return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
  }

  private double bruteForce(Point[] points, int n) {
    double min_val = MIN_MAX;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < ++i; j++) {
        if (dist(points[i], points[j]) < min_val)
          min_val = dist(points[i], points[j]);
      }
    }

    return min_val;
  }

  private float min(float x, float y) {
    return Math.min(x, y);
  }

}
