// ID:211390877
package geomtry;

/**
 *The class defines a point by x and y.
 */
public class Point {

    //fields
    private double x;
    private double y;

    /**
     * constructor.
     * @param x the x of the point.
     * @param y the y of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param other the other point.
     * @return the distance of this point to the other point.
     */
    public double distance(Point other) {
        return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }
    /**
     * @param other the other point.
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return this.x == other.x && this.y == other.y;
    }

    /**
     * @return the x of the point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y of the point.
     */
    public double getY() {
        return this.y;
    }
}

