// ID:21139087

package geomtry;


/**
 *The class defines a line by two points start point and end point.
 *The line also has slope and y-intercept.
 *It also finds if there is a point of intersection between two lines and where they intersect.
 */
public class Line {
    private Point start;
    private Point end;
    private double slope;
    private double intercept;

    /**
     * constructor.
     *
     * @param start the start point of the line.
     * @param end   the end point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        if (start.getX() == end.getX() && start.getY() != end.getY()) {
            slope = Double.POSITIVE_INFINITY;
        } else {
            slope = ((start.getY() - end.getY()) / (start.getX() - end.getX()));
            intercept = (-slope * start.getX()) + start.getY();
        }
    }

    /**
     * constructor.
     *
     * @param x1 the x of the start point.
     * @param y1 the y of the start point.
     * @param x2 the x of the end point.
     * @param y2 the y of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
        if (x1 == x2) {
            slope = Double.POSITIVE_INFINITY;
        } else {
            slope = ((y1 - y2) / (x1 - x2));
            intercept = (-slope * start.getX()) + start.getY();
        }
    }

    /**
     * @return the length of the line.
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * @return the middle point of the line.
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2,
                (this.start.getY() + this.end.getY()) / 2);
    }

    /**
     * @return the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * @return the slope of the line.
     */
    public double getSlope() {
        return slope;
    }

    /**
     * @return intercept-Y of the line.
     */
    public double getIntercept() {
        return intercept;
    }

    /**
     * @return true if the line start and end at the same point , false otherwise.
     */
    private boolean lineIsPoint() {
        return start.equals(end);
    }

    /**
     * Arrange the lines so that:Y1 below Y2 and the 'other line' start below 'this line'.
     * helps to know if the segments intersect, or no.
     *
     * @param other the other line.
     * @return point so that:x=otherY2 y=thisY1
     */
    private Point rearrangeConvergingLines(Line other) {
        Point specialPoint;
        double thisY1 = this.start.getY();
        double thisY2 = this.end.getY();
        double otherY1 = other.start.getY();
        double otherY2 = other.end.getY();
        double temp;
        if (thisY1 > thisY2) {
            temp = thisY1;
            thisY1 = thisY2;
            thisY2 = temp;
        }
        if (otherY1 > otherY2) {
            temp = otherY1;
            otherY1 = otherY2;
            otherY2 = temp;
        }
        if (otherY1 > thisY1) {
            thisY1 = otherY1;
            otherY2 = thisY2;
        }
        specialPoint = new Point(otherY2, thisY1);
        return specialPoint;
    }

    /**
     * Arrange the lines so that:X1 before X2 and the 'other line' start before 'this line'.
     * helps to know if the segments intersect, or no when the slope is zero.
     *
     * @param other the other line.
     * @return point so that:x=otherX2 y=thisX1
     */
    private Point rearrangeConvergingLinesSlopeZero(Line other) {
        Point specialPoint;
        double thisX1 = this.start.getX();
        double thisX2 = this.end.getX();
        double otherX1 = other.start.getX();
        double otherX2 = other.end.getX();
        double temp;
        if (thisX1 > thisX2) {
            temp = thisX1;
            thisX1 = thisX2;
            thisX2 = temp;
        }
        if (otherX1 > otherX2) {
            temp = otherX1;
            otherX1 = otherX2;
            otherX2 = temp;
        }
        if (otherX1 > thisX1) {
            thisX1 = otherX1;
            otherX2 = thisX2;
        }
        specialPoint = new Point(otherX2, thisX1);
        return specialPoint;
    }

    /**
     * @param other the other line.
     * @return true if the lines converge , false otherwise.
     */
    private boolean convergingLines(Line other) {
        if (this.slope == 0 && this.rearrangeConvergingLinesSlopeZero(other).getX()
                >= this.rearrangeConvergingLinesSlopeZero(other).getY()) {
            return true;
        }
        return this.rearrangeConvergingLines(other).getX() >= this.rearrangeConvergingLines(other).getY();
    }

    /**
     * @param otherY the y of point of the 'other line'.
     * @return true if the y of the point in the range of the line ,false otherwise.
     */
    private boolean inRange(double otherY) {
        double thisY1 = this.start.getY();
        double thisY2 = this.end.getY();
        double temp;
        //Arrange the line so that:Y1 below Y2
        if (thisY1 > thisY2) {
            temp = thisY1;
            thisY1 = thisY2;
            thisY2 = temp;
        }
        return !(otherY < thisY1) && !(otherY > thisY2);
    }

    /**
     * @param otherX the x of point of the 'other line'.
     * @return true if the x of the point in the range of the line ,false otherwise.
     */
    private boolean inRangeByX(double otherX) {
        double thisX1 = this.start.getX();
        double thisX2 = this.end.getX();
        double temp;
        //Arrange the line so that:X1 before X2
        if (thisX1 > thisX2) {
            temp = thisX1;
            thisX1 = thisX2;
            thisX2 = temp;
        }
        return !(otherX < thisX1) && !(otherX > thisX2);
    }

    /**
     * @param other the other line.
     * @return the intersect point of the converge lines , NULL otherwise.
     */
    private Point convergingLinesPoint(Line other) {
        Point intersectPoint;
        if (this.slope == 0) {
            if (this.rearrangeConvergingLinesSlopeZero(other).getX()
                    == this.rearrangeConvergingLinesSlopeZero(other).getY()) {
                intersectPoint = new Point(this.rearrangeConvergingLinesSlopeZero(other).getX(), this.start.getY());
                return intersectPoint;
            }
        } else if (this.rearrangeConvergingLines(other).getX() == this.rearrangeConvergingLines(other).getY()) {
            if (this.slope == Double.POSITIVE_INFINITY) {
                intersectPoint = new Point(this.start.getX(), this.rearrangeConvergingLines(other).getY());
            } else {
                intersectPoint = new Point((this.rearrangeConvergingLines(other).getY()
                        - this.intercept) / this.slope, this.rearrangeConvergingLines(other).getY());
            }
            return intersectPoint;
        }
        return (null);
    }

    /**
     * @param other the other line.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        //both of the segments are points
        if (this.lineIsPoint() && other.lineIsPoint()) {
            return this.start().equals(other.start);
        }
        //'this line' is a point
        if (this.lineIsPoint()) {
            if (other.slope == Double.POSITIVE_INFINITY) {
                if (other.start.getX() == this.start.getX()) {
                    return other.inRange(this.start.getY());
                }
            }
            if (this.start.getY() == other.slope * this.start.getX() + other.intercept) {
                return other.inRange(this.start.getY());
            }
            return false;
        }
        //'other line' is a point
        if (other.lineIsPoint()) {
            if (this.slope == Double.POSITIVE_INFINITY) {
                if (this.start.getX() == other.start.getX()) {
                    return this.inRange(other.start.getY());
                }
            }
            if (other.start.getY() == this.slope * other.start.getX() + this.intercept) {
                return this.inRange(other.start.getY());
            }
            return false;
        }
        //both of the segments with infinity slope
        if (this.slope == Double.POSITIVE_INFINITY && other.slope == Double.POSITIVE_INFINITY) {
            if (this.start.getX() != other.start.getX()) {
                return false;
            }
            return this.convergingLines(other);
        }
        //'this line' with infinity slope
        if (this.slope == Double.POSITIVE_INFINITY) {
            double thisX;
            double otherY;
            thisX = this.start.getX();
            otherY = other.slope * thisX + other.intercept;
            if (other.slope == 0) {
                return this.inRange(otherY) && other.inRangeByX(thisX);
            }
            return this.inRange(otherY) && other.inRange(otherY);
        }
        //'other line' with infinity slope
        if (other.slope == Double.POSITIVE_INFINITY) {
            double otherX;
            double thisY;
            otherX = other.start.getX();
            thisY = this.slope * otherX + this.intercept;
            if (this.slope == 0) {
                return other.inRange(thisY) && this.inRangeByX(otherX);
            }
            return this.inRange(thisY) && other.inRange(thisY);
        }
        double intersectPointX;
        double thisSlope = this.slope;
        double thisIntercept = this.intercept;
        double otherSlope = other.slope;
        double otherIntercept = other.intercept;

        //Parallel lines
        if (thisSlope == otherSlope) {
            if (thisIntercept != otherIntercept) {
                return false;
            }
            return this.convergingLines(other);
        }
        intersectPointX = ((otherIntercept - thisIntercept) / (thisSlope - otherSlope));
        return this.inRangeByX(intersectPointX) && other.inRangeByX(intersectPointX);
    }

    /**
     * @param other the other line.
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        if (this.isIntersecting(other)) {
            //if the segments is a point because the function isIntersecting
            //returned true it means this is the point who intersect with
            if (this.lineIsPoint()) {
                return this.start;
            }
            if (other.lineIsPoint()) {
                return other.start;
            }
            //if the segments converge
            if (this.slope == Double.POSITIVE_INFINITY && other.slope == Double.POSITIVE_INFINITY
                    || this.slope == other.slope) {
                return this.convergingLinesPoint(other);
            }
            double intersectPointX;
            double intersectPointY;
            Point intersectPoint;
            if (this.slope == Double.POSITIVE_INFINITY) {
                double otherSlope = other.slope;
                double otherIntercept = other.intercept;
                intersectPointX = this.start.getX();
                intersectPointY = otherSlope * intersectPointX + otherIntercept;
                intersectPoint = new Point(intersectPointX, intersectPointY);
                return intersectPoint;
            }
            if (other.slope == Double.POSITIVE_INFINITY) {
                double thisSlope = this.slope;
                double thisIntercept = this.intercept;
                intersectPointX = other.start.getX();
                intersectPointY = thisSlope * intersectPointX + thisIntercept;
                intersectPoint = new Point(intersectPointX, intersectPointY);
                return intersectPoint;
            }
            double thisSlope = this.slope;
            double thisIntercept = this.intercept;
            double otherSlope = other.slope;
            double otherIntercept = other.intercept;
            intersectPointX = ((otherIntercept - thisIntercept) / (thisSlope - otherSlope));
            intersectPointY = thisSlope * intersectPointX + thisIntercept;
            intersectPoint = new Point(intersectPointX, intersectPointY);
            return intersectPoint;
        }
        return null;
    }

    /**
     * @param other the other line.
     * @return true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        Point thisStart = this.start;
        Point thisEnd = this.end;
        Point otherStart = other.start;
        Point otherEnd = other.end;
        return thisStart.equals(otherStart) && thisEnd.equals(otherEnd)
                || thisStart.equals(otherEnd) && thisEnd.equals(otherStart);
    }
    /**
     *  If this line does not intersect with the rectangle, return null.
     *  Otherwise, return the closest intersection point to the
     *  start of the line.
     * @param rect the rectangle.
     * @return null or the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        if (rect.intersectionPoints(this).isEmpty()) {
            return null;
        }
        double minDistance = Double.MAX_VALUE;
        Point closestInterToStartOfLn = null;
        for (int i = 0; i < rect.intersectionPoints(this).size(); i++) {
           if (minDistance > this.start.distance(rect.intersectionPoints(this).get(i))) {
               minDistance = this.start.distance(rect.intersectionPoints(this).get(i));
               closestInterToStartOfLn = rect.intersectionPoints(this).get(i);
            }
        }
      return  closestInterToStartOfLn;
    }
}

