package sprites;
import Effects.Images;
import Effects.Sound;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import bonus.Bonus;
import geomtry.Point;
import geomtry.Rectangle;
import collision.Collidable;
import animations.GameLevel;
import listeners.HitListener;
import listeners.HitNotifier;
import other.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * the class defines a paddle - rectangle that can move left and right.
 * it collidable object.
 */
public class Paddle implements Sprite, Collidable, HitNotifier {
    private static final double EPSILON = 0.00001;
    private static final double ANGLE_REGION1 = 300;
    private static final double ANGLE_CHANGE = 30;
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private int speed;
    private String leftControl = KeyboardSensor.LEFT_KEY;
    private String rightControl = KeyboardSensor.RIGHT_KEY;
    private double leftLimit = 10;
    private double rightLimit = 590;
    private List<HitListener> hitListeners = new ArrayList<>();



    /**
     * constructor.
     * @param rect the shape of the paddle.
     * @param color the color of the paddle.
     * @param keyboard  control the movement of the paddle.
     * @param speed the speed of the paddle.
     */
    public Paddle(Rectangle rect, biuoop.KeyboardSensor keyboard, int speed) {
        this.rect = rect;
        this.keyboard = keyboard;
        this.speed = speed;
    }
    public void setLeftControl(String leftControl) {
        this.leftControl = leftControl;
    }

    public void setRightControl(String rightControl) {
        this.rightControl = rightControl;
    }

    public void setLeftLimit(double leftLimit) {
        this.leftLimit = leftLimit;
    }

    public void setRightLimit(double rightLimit) {
        this.rightLimit = rightLimit;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return  rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }
    /**
     * move the paddle 5 pixels left.
     */
    public void moveLeft() {
       rect = new Rectangle(new Point(rect.getUpperLeft().getX() - speed, rect.getUpperLeft().getY()),
                rect.getWidth(), rect.getHeight());
    }

    /**
     * move the paddle 5 pixels right.
     */
    public void moveRight() {
       rect = new Rectangle(new Point(rect.getUpperLeft().getX() + speed, rect.getUpperLeft().getY()),
                rect.getWidth(), rect.getHeight());
    }

   @Override
    public void timePassed() {
            if (keyboard.isPressed(leftControl)) {
                if (rect.getUpperLeft().getX() > leftLimit) {
                    this.moveLeft();
                }
            }
            if (keyboard.isPressed(rightControl)) {
                if (rect.getUpperRight().getX() < rightLimit) {
                    this.moveRight();
                }
            }
        }

  @Override
    public void drawOn(DrawSurface d) {
      d.drawImage((int)rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
              Images.getImage((int) rect.getWidth(), (int) rect.getHeight(), "paddle"));
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Sound.playSound("PaddleHit");
        //Collision on the right or left side of the paddle.
        if (((Math.abs(collisionPoint.getX() - rect.getUpperLeft().getX()) < EPSILON) && currentVelocity.getDx() > 0)
                || ((Math.abs(collisionPoint.getX() - rect.getUpperRight().getX()) < EPSILON)
                && currentVelocity.getDx() < 0)) {
            currentVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        int rangeOfRegion = (int) rect.getWidth() / 5;
        //region 3
        if (collisionPoint.getX() >= rect.getUpperLeft().getX() + 2 * rangeOfRegion
                && collisionPoint.getX() <= rect.getUpperLeft().getX()
                + rangeOfRegion + 2 * rangeOfRegion) {
           return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        //region 1,2,4,5
        if (((Math.abs(collisionPoint.getY() - rect.getUpperLeft().getY()) < EPSILON) && currentVelocity.getDy() > 0)) {
            for (int i = 0; i < 5; i++) {
                if (collisionPoint.getX() >= rect.getUpperLeft().getX() + i * rangeOfRegion
                        && collisionPoint.getX() <= rect.getUpperLeft().getX()
                        + rangeOfRegion + i * rangeOfRegion) {
                    currentVelocity = Velocity.fromAngleAndSpeed(ANGLE_REGION1 + i * ANGLE_CHANGE,
                            hitter.getVelocity().getSpeed());
                }
            }
        }

        return  currentVelocity;
    }
    private void notifyHit(Bonus bonus) {
        Sound.playSound("EarnBonus");
        bonus.makeBonusActive(this);
        // Notify all listeners about a hit event:
        for (HitListener hl : hitListeners) {
            hl.hitEvent(bonus);
        }
    }

    @Override
    public void hit(Bonus bonus) {
        notifyHit(bonus);
    }

    /**
     *  Add this paddle to the game.
     *  as sprite and as collidable object.
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
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
