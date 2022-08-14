package listeners;

import animations.GameLevel;
import sprites.Ball;
import sprites.Block;
import bonus.Bonus;

public class BonusRemover implements HitListener {
    private GameLevel game;

    public BonusRemover(GameLevel game) {
        this.game = game;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
    }

    @Override
    public void hitEvent(Bonus bonus) {
        bonus.removeFromGame(game);
    }

}
