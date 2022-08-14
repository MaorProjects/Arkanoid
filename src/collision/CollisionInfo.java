package collision;

import geomtry.Point;


/**
 * the class defines an object that contained the information about
 * the hit and is: the collision point and the object that collided with it.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;
    /**
     * constructor.
     * @param collisionPoint the point where the ball collided.
     * @param collisionObject the object that the ball collided with.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }
    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return  collisionPoint;
    }
    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return  collisionObject;
    }
}
