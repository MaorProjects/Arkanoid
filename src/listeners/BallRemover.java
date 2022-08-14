package listeners;

import other.Counter;
import animations.GameLevel;
import sprites.Ball;
import sprites.Block;
import bonus.Bonus;

/**
 * the class charge to remove balls that hit in the death region (the bottom block).
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * constructor.
     * @param game the game.
     * @param removedBlocks the counter of the blocks.
     */
    public BallRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBalls = removedBlocks;
    }

    /**
     * remove the ball from the game.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }

    @Override
    public void hitEvent(Bonus bonus) {

    }
}
