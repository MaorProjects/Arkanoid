package sprites;
import Effects.Images;
import Effects.Sound;
import biuoop.DrawSurface;
import bonus.Bonus;
import geomtry.Point;
import geomtry.Rectangle;
import listeners.HitListener;
import listeners.HitNotifier;
import collision.Collidable;
import animations.GameLevel;
import other.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * the class defines blocks as rectangles with color.
 * the ball can collide with blocks.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private static final double EPSILON = 0.00001;
    private Rectangle rect;
    private Color color;
    private String col;
    private List<HitListener> hitListeners = new ArrayList<>();


    /**
     * constructor.
     * @param rect the shape of the block.
     * @param color the color of the block.
     */
    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
    }
    /**
     * constructor.
     * @param rect the shape of the block.
     */
    public Block(Rectangle rect, String col) {
        this.rect = rect;
        this.col = col;
    }

    /**
     *  notify all the hit listeners that hit occurred.
     * @param hitter the ball that collided in the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    private void notifyHit(Bonus bonus) {
        // Notify all listeners about a hit event:
        for (HitListener hl : hitListeners) {
            hl.hitEvent(bonus);
        }
    }


    @Override
    public Rectangle getCollisionRectangle() {
        return rect;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        //Collision at the top or bottom of the block.
        if (((Math.abs(collisionPoint.getY() - rect.getUpperLeft().getY()) < EPSILON) && currentVelocity.getDy() > 0)
                || ((Math.abs(collisionPoint.getY() - rect.getLowerLeft().getY()) < EPSILON)
                && currentVelocity.getDy() < 0)) {
            currentVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        //Collision on the right or left side of the block.
        if (((Math.abs(collisionPoint.getX() - rect.getUpperLeft().getX()) < EPSILON) && currentVelocity.getDx() > 0)
                || ((Math.abs(collisionPoint.getX() - rect.getUpperRight().getX()) < EPSILON)
                && currentVelocity.getDx() < 0)) {
            currentVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        return  currentVelocity;
    }


    @Override
    public void hit(Bonus bonus) {
        this.notifyHit(bonus);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        if (col != null) {
           surface.drawImage((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(), Images.getImage(
                   (int) rect.getWidth(), (int) rect.getHeight(), col));
        } else {
            surface.setColor(this.color);
            surface.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                    (int) rect.getWidth(), (int) rect.getHeight());
            surface.setColor(Color.black);
            surface.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                    (int) rect.getWidth(), (int) rect.getHeight());
        }
    }
    /**
     *  Add blocks to the game.
     *  as sprite and as collidable objects.
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     *  Remove blocks from the game.
     * @param game the game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }
}
