package listeners;

import bonus.BallsBonus;
import bonus.BiggerPaddleBonus;
import bonus.Bonus;
import bonus.LiveBonus;
import collections.GameEnvironment;
import collections.SpriteCollection;
import animations.GameLevel;
import other.Counter;
import sprites.*;

public class BonusAdder implements HitListener {
    private GameLevel game;
    private GameEnvironment gmEnv;
    private SpriteCollection spriteCollection;
    private String bonusType;
    private Counter counterBalls;
    private Counter lives;

    public BonusAdder(GameLevel game, GameEnvironment gmEnv,String bonusType) {
        this.game = game;
        this.gmEnv = gmEnv;
        this.bonusType = bonusType;
    }

    public void setCounterBalls(Counter counterBalls) {
        this.counterBalls = counterBalls;
    }
    public void setSpriteCollection(SpriteCollection spriteCollection) {
        this.spriteCollection = spriteCollection;
    }
    public void setLives(Counter lives) {
        this.lives = lives;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
            if (bonusType.equals("Balls")) {
                BallsBonus ballsBonus = new BallsBonus(gmEnv, beingHit.getCollisionRectangle().getLowerLeft(),
                        counterBalls);
                ballsBonus.addToGame(game);
                ballsBonus.setSpriteCollection(spriteCollection);
                ballsBonus.setGameLevel(game);
            }
            if (bonusType.equals("BiggerPaddle")) {
                BiggerPaddleBonus biggerPaddleBonus = new BiggerPaddleBonus(
                        gmEnv, beingHit.getCollisionRectangle().getLowerLeft());
                biggerPaddleBonus.addToGame(game);
            }
            if (bonusType.equals("Live")) {
                LiveBonus liveBonus = new LiveBonus(gmEnv, beingHit.getCollisionRectangle().getLowerLeft(), lives);
                liveBonus.addToGame(game);
            }
    }

    @Override
    public void hitEvent(Bonus bonus) {
    }

}
