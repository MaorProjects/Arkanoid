package bonus;

import Effects.Images;
import biuoop.DrawSurface;
import collections.GameEnvironment;
import collision.CollisionInfo;
import geomtry.Line;
import geomtry.Point;
import geomtry.Rectangle;
import animations.GameLevel;
import sprites.Paddle;
import sprites.Sprite;

public class BiggerPaddleBonus implements Sprite, Bonus {
    private Point upperLeftPoint;
    private GameEnvironment gmEnv;

    public BiggerPaddleBonus(GameEnvironment gmEnv, Point upperLeftPoint) {
        this.upperLeftPoint = upperLeftPoint;
        this.gmEnv = gmEnv;
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
    @Override
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

    @Override
    public void makeBonusActive(Paddle paddle) {
        double oldWidth = paddle.getCollisionRectangle().getWidth();
        Point oldUpperLeft = paddle.getCollisionRectangle().getUpperLeft();
        double newWidth = oldWidth * 1.3;
        Point newUpperLeft = new Point(oldUpperLeft.getX() - oldWidth / 6, oldUpperLeft.getY());
        paddle.setRect(new Rectangle(newUpperLeft, newWidth, paddle.getCollisionRectangle().getHeight()));
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage((int)upperLeftPoint.getX(), (int) upperLeftPoint.getY(), Images.getImage(
                25, 12, "paddle"));
    }
    @Override
    public void timePassed() {
        upperLeftPoint = new Point(upperLeftPoint.getX(), upperLeftPoint.getY() + 1);
        Line trajectory = new Line(upperLeftPoint, new Point(upperLeftPoint.getX(), upperLeftPoint.getY() + 5));
        CollisionInfo cInfo = gmEnv.getClosestCollision(trajectory);
        if (cInfo != null) {
            cInfo.collisionObject().hit(this);
        }

    }
}
