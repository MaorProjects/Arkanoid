package bonus;

import Effects.Images;
import biuoop.DrawSurface;
import collections.GameEnvironment;
import collision.CollisionInfo;
import geomtry.Line;
import geomtry.Point;
import animations.GameLevel;
import other.Counter;
import sprites.Paddle;
import sprites.Sprite;


public class LiveBonus implements Bonus, Sprite {
    private Point upperLeftPoint;
    private GameEnvironment gmEnv;
    private Counter lives;

    public LiveBonus( GameEnvironment gmEnv, Point upperLeftPoint, Counter lives) {
        this.upperLeftPoint = upperLeftPoint;
        this.gmEnv = gmEnv;
        this.lives = lives;
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
        lives.increase(1);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage((int) upperLeftPoint.getX(), (int) upperLeftPoint.getY(), Images.getImage(
                20, 20, "heart"));
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
