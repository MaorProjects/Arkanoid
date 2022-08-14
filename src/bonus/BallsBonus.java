package bonus;

import Effects.Images;
import biuoop.DrawSurface;
import collections.GameEnvironment;
import collections.SpriteCollection;
import collision.CollisionInfo;
import geomtry.Line;
import geomtry.Point;
import animations.GameLevel;
import other.Counter;
import other.Velocity;
import sprites.Ball;
import sprites.Paddle;
import sprites.Sprite;

import java.util.ArrayList;
import java.util.List;

public class BallsBonus implements Sprite, Bonus {
    private Point upperLeftPoint;
    private GameEnvironment gmEnv;
    private GameLevel game;
    private SpriteCollection spriteCollection;
    private Counter counterBalls;
    public BallsBonus(GameEnvironment gmEnv, Point upperLeftPoint, Counter counterBalls) {
        this.gmEnv = gmEnv;
        this.upperLeftPoint = upperLeftPoint;
        this.counterBalls = counterBalls;
    }
    public void setSpriteCollection(SpriteCollection spriteCollection) {
        this.spriteCollection = spriteCollection;
    }
    public void setGameLevel(GameLevel gameLevel) {
        this.game = gameLevel;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage((int)upperLeftPoint.getX(), (int) upperLeftPoint.getY(), Images.getImage(
                20,  20, "balls"));
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
        List<Sprite> allSprites = new ArrayList<>(spriteCollection.getSprites());
       for(Sprite s : allSprites) {
         if (s.getClass() == Ball.class) {
             Ball ball = (Ball) s;
             Ball ball1 = new Ball(ball.getX(), ball.getY(), ball.getSize(), ball.getColor());
             ball1.setVelocity(Velocity.fromAngleAndSpeed(45, ((Ball) s).getVelocity().getSpeed()));
             ball1.setEnv(gmEnv);
             ball1.addToGame(game);
             Ball ball2 = new Ball(ball.getX(), ball.getY(), ball.getSize(), ball.getColor());
             ball2.setVelocity((Velocity.fromAngleAndSpeed(-45, ((Ball) s).getVelocity().getSpeed())));
             ball2.setEnv(gmEnv);
             ball2.addToGame(game);
             counterBalls.increase(2);
         }
       }
    }
}
