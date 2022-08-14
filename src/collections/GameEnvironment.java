package collections;
import geomtry.Line;
import geomtry.Point;
import collision.Collidable;
import collision.CollisionInfo;

import java.util.ArrayList;

/**
 * the class save all the objects the ball can collide them
 * and find where the ball will collide (the object and the exact point).
 */
public class GameEnvironment {
    private ArrayList<Collidable> collidables = new ArrayList<>();

    /**
     * @return the list of the collidable objects.
     */
    public java.util.List<Collidable> getCollidables() {
        return collidables;
    }
    /**
     * add collidable object to the list.
     * @param c is collidable object.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * remove collidable object from the list.
     * @param c the collidable.
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * If all the objects will not collide with the ball in its trajectory, return null.
     * otherwise, return collision info: the closest intersection from collidable object to the Start of the trajectory
     * and the collidable object.
     * @param trajectory the way the ball does from its current position to its next step.
     * @return null or collision info.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point collisionPoint = null;
        Collidable collisionObject = null;
        double minDistance = Double.MAX_VALUE;
        Point closestInterToStartOfLn = null;
        for (Collidable collidable : collidables) {
            //check which objects will collide with the ball in its trajectory
            if (trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle()) != null) {
                collisionPoint = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
                //check which objects is the closest
                if (minDistance >= trajectory.start().distance(collisionPoint)) {
                    minDistance = trajectory.start().distance(collisionPoint);
                    closestInterToStartOfLn = collisionPoint;
                    collisionObject = collidable;
                }
            }
        }
        if (collisionPoint == null) {
            return  null;
        }
        return  new CollisionInfo(closestInterToStartOfLn, collisionObject);
    }

}
