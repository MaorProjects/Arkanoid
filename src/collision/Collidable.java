package collision;

import geomtry.Point;
import geomtry.Rectangle;
import other.Velocity;
import sprites.Ball;
import bonus.Bonus;

/**
 * The interface describes objects that the ball can collide with.
 */
public interface Collidable {
    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();
    /**
     * give the ball new velocity after it hit in the collidable object.
     * @param collisionPoint The point at which the collision occurs.
     * @param currentVelocity the velocity of the ball before the collision.
     * @param hitter The ball that collided.
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * for bonuses hits.
     */
    void hit(Bonus bonus);
}
