package other;

import geomtry.Point;

/**
     * other.Velocity specifies the change in position on the `x` and the `y` axes.
     */
    public class Velocity {
    private  double dx;
    private  double dy;
        /**
         * constructor.
         * @param dx indicates progress on the X-axis.
         * @param dy Indicates progress on the Y-axis.
         */
        public Velocity(double dx, double dy) {
            this.dx = dx;
            this.dy = dy;
        }

        /**
         * @param angle the angle at which the ball moves.
         * @param speed The speed at which the ball moves.
         * @return the velocity by dx and dy progress.
         */
        public static Velocity fromAngleAndSpeed(double angle, double speed) {
            double dx = speed * Math.sin(Math.toRadians(angle));
            double dy = -speed * Math.cos(Math.toRadians(angle));
            return new Velocity(dx, dy);
        }
        /**
         * @return the dx value of the velocity.
         */
        public double getDx() {
            return dx;
        }

        /**
         * @return the dy value of the velocity.
         */
        public double getDy() {
            return dy;
        }

    /**
     * @return the angle in radians.
     */
    public double getAngle() {
            double angle = -Math.atan((dx / dy));
            if (dy < 0) {
                return angle;
            }
            return angle + Math.PI;
        }

    /**
     * @return the speed.
     */
    public double getSpeed() {
            return dx / Math.sin(getAngle());
    }
        /**
         * Take a point with position (x,y).
         * @param p the center point of the ball;
         * @return new point with position (x+dx, y+dy)
         */
        public Point applyToPoint(Point p) {
            return new Point(p.getX() + dx, p.getY() + dy);
    }

}
