package sprites;    // ID:211390877

    import biuoop.DrawSurface;
    import collections.GameEnvironment;
    import geomtry.Line;
    import geomtry.Point;
    import collision.CollisionInfo;
    import animations.GameLevel;
    import other.Velocity;
    import java.awt.Color;
    /**
     *The class defines a ball by center point radius and color.
     * the ball also has velocity.
     */
    public class Ball implements Sprite {

        private Point center;
        private int radius;
        private Color color;
        private Velocity velocity;
        private GameEnvironment gmEnv;


        /**
         * constructor with center point.
         * @param center the center point of the ball.
         * @param r the radius of the ball.
         * @param  color the color of the ball.
         */
        public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        radius = r;
        this.color = color;
        }

        /**
         * constructor with x and y of the center point.
         * @param centerX the x of the center point of the ball.
         * @param centerY the y of the center point of the ball.
         * @param r the radius of the ball.
         * @param color the color of the ball.
         */
        public Ball(double centerX, double centerY, int r, java.awt.Color color) {
            center = new Point(centerX, centerY);
            radius = r;
            this.color = color;
        }

        /**
         * @return the x of the center point of the ball.
         */
        public int getX() {
            return (int) this.center.getX();
        }
        /**
         * @return the y of the center point of the ball.
         */
        public int getY() {
            return (int) this.center.getY();
        }

        /**
         * @return the radius of the ball.
         */
        public int getSize() {
            return this.radius;
        }

        /**
         * @return the color of the ball.
         */
        public java.awt.Color getColor() {
            return this.color;
        }

        @Override
         public void drawOn(DrawSurface surface) {
            surface.setColor(color);
            surface.fillCircle(this.getX(), this.getY(), radius);
            surface.setColor(Color.black);
            surface.drawCircle(this.getX(), this.getY(), radius);
        }

        @Override
        public void timePassed() {
             this.moveOneStep();
        }

        /**
         * set the velocity of the ball.
         * @param v is the velocity.
         */
        public void setVelocity(Velocity v) {
            velocity = new Velocity(v.getDx(), v.getDy());
        }

        /**
         * set the velocity of the ball.
         * @param dx the ball's progress horizontally.
         * @param dy The ball's progress  vertically.
         */
        public void setVelocity(double dx, double dy) {
            velocity = new Velocity(dx, dy);
        }

        /**
         * @return the velocity of the ball.
         */
        public Velocity getVelocity() {
            return velocity;
        }

        /**
         * set the game environment for the ball.
         * @param gameEnvironment the game environment.
         */
        public void setEnv(GameEnvironment gameEnvironment) {
            this.gmEnv = gameEnvironment;
        }

        /**
         * add the ball to the game as sprite.
         * @param g the game.
         */
        public void addToGame(GameLevel g) {
            g.addSprite(this);
        }

        /**
         * remove the ball from the game.
         * @param g the game.
         */
        public  void removeFromGame(GameLevel g) {
            g.removeSprite(this);
        }
        /**
         * the ball move one step according to his velocity.
         */
        public void moveOneStep() {
            Line trajectory = new Line(this.center, this.getVelocity().applyToPoint(this.center));
            CollisionInfo cInfo = gmEnv.getClosestCollision(trajectory);
            if (cInfo != null) {
                //In case the ball enters the block (especially the paddle) - take it out.
                if (this.center.getY() > cInfo.collisionObject().getCollisionRectangle().getUpperLeft().getY()
                && this.center.getY() < cInfo.collisionObject().getCollisionRectangle().getLowerLeft().getY()
                && this.center.getX() > cInfo.collisionObject().getCollisionRectangle().getLowerLeft().getX()
               && this.center.getX() < cInfo.collisionObject().getCollisionRectangle().getLowerRight().getX()) {
                    this.center = new Point(center.getX(), this.center.getY() - (this.center.getY()
                            - cInfo.collisionObject().getCollisionRectangle().getUpperLeft().getY() + 1));
                }
                Velocity newVelocity = cInfo.collisionObject().hit(this, cInfo.collisionPoint(), this.velocity);
                this.center = new Point(center.getX() - this.getVelocity().getDx(),
                        center.getY() - this.getVelocity().getDy());
                this.setVelocity(newVelocity.getDx(), newVelocity.getDy());
            }
            this.center = this.getVelocity().applyToPoint(this.center);

        }
    }

