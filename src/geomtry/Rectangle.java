// ID:211390877
package geomtry;

import java.util.ArrayList;

/**
 * the class defines rectangle by its upper left point, width ,height and color.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Point upperRight;
    private Point lowerLeft;
    private Point lowerRight;

    // Create a new rectangle with location and width/height.

    /**
     * constructor.
     * @param upperLeft the upper left point of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        lowerRight = new Point(upperRight.getX(), lowerLeft.getY());
    }
    /**
     * @param line the line we search intersection points between him  and this rectangle.
     * @return list (possibly empty) of the intersection points between the given line and this rectangle.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        ArrayList<Point> inPts = new ArrayList<>();
        Line leftLine = new Line(upperLeft, lowerLeft);
        Line rightLine = new Line(upperRight, lowerRight);
        Line upperLine = new Line(upperLeft, upperRight);
        Line lowerLine = new Line(lowerLeft, lowerRight);
        if (line.intersectionWith(leftLine) != null) {
            inPts.add(line.intersectionWith(leftLine));
        }
        if (line.intersectionWith(upperLine) != null) {
                inPts.add(line.intersectionWith(upperLine));
        }
        if (line.intersectionWith(rightLine) != null) {
                inPts.add(line.intersectionWith(rightLine));
        }
        if (line.intersectionWith(lowerLine) != null) {
                inPts.add(line.intersectionWith(lowerLine));
        }
        return inPts;
    }
    /**
     * @return the width of the rectangle
     */
    public double getWidth() {
        return width;
    }
    /**
     * @return the height of the rectangle
     */
    public double getHeight() {
        return  height;
    }
    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }
    /**
     * @return the upper-Right point of the rectangle.
     */
    public Point getUpperRight() {
        return upperRight;
    }
    /**
     * @return the lower-left point of the rectangle.
     */
    public Point getLowerLeft() {
        return lowerLeft;
    }
    /**
     * @return the lower-right point of the rectangle.
     */
    public Point getLowerRight() {
        return lowerRight;
    }
}
