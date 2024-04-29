package quickhull;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.du.dudraw.DUDraw;
public class quickhull {


{
	public static List<Point> convexHull(List<Point> S) {
		List<Point> convexHull = new ArrayList<>();

		for (int i = 0; i < S.size(); i++) {
			for (int j = i + 1; j < S.size(); j++) {
				Point pi = S.get(i);
				Point pj = S.get(j);
				int leftTurnCount = 0;

				for (Point point : S) {
					if (point != pi && point != pj && isLeft(pi, pj, point)) {
						leftTurnCount++;
					}
				}

				if (leftTurnCount == 0 || leftTurnCount == S.size() - 2) {
						convexHull.add(pi);
						convexHull.add(pj);
				}
			}
		}
		return convexHull;
	}

	private static boolean isLeft(Point a, Point b, Point i) {
	    // Compute the cross product of vectors ab and ai
	    int crossProduct = (b.x - a.x) * (i.y - a.y) - (i.x - a.x) * (b.y - a.y);
	    return crossProduct > 0; // If the cross product is positive, point i is to the left of segment ab
	}

	public static void drawPoints(List<Point> S) 
	{
		DUDraw.setPenRadius(3);
		DUDraw.setPenColor(DUDraw.BLACK);
		for (int i = 0; i < S.size(); i++) 
		{
			DUDraw.point(S.get(i).x * 0.01, S.get(i).y * 0.01);
		}
	}

	public static void drawConvex(List<Point> S)
	{
		DUDraw.setPenRadius(0.5); 
		DUDraw.setPenColor(DUDraw.GREEN); 

		// Draw the lines between consecutive points in the convex hull
		for (int i = 0; i < S.size(); i++) {
			Point currentPoint = S.get(i);
			Point nextPoint = S.get((i + 1) % S.size()); // Wrap around to the first point

			// Draw a line between the current point and the next point
			DUDraw.line(currentPoint.x * 0.01, currentPoint.y * 0.01, nextPoint.x * 0.01, nextPoint.y * 0.01);
		}
	}

	public static List<Point> generateRandomPoints(int numPoints, int minX, int maxX, int minY, int maxY) {
		List<Point> points = new ArrayList<>();
		Random random = new Random();

		for (int i = 0; i < numPoints; i++) {
			int x = minX + random.nextInt(maxX - minX + 1);
			int y = minY + random.nextInt(maxY - minY + 1);
			points.add(new Point(x, y));
		}

		return points;
	}


public static void main(String[] args) {
	List<Point> points = generateRandomPoints(50, 0, 100, 0, 100);

	List<Point> convexHull = convexHull(points);
	drawPoints(points);
	drawConvex(convexHull);

	// Print the generated points
	for (Point point : points) {
		System.out.println("Point: (" + point.x + ", " + point.y + ")");
		}
	}
}
