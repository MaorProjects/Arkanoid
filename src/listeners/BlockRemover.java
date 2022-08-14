package listeners;

import Effects.Sound;
import other.Counter;
import animations.GameLevel;
import sprites.Ball;
import sprites.Block;
import bonus.Bonus;

/**
 * the class charge to remove blocks that the ball hit them from the game.
 */
public  class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * constructor.
     * @param game the game.
     * @param removedBlocks the counter of the blocks.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }
    /**
     * remove blocks that hit from the game and update the counter of the blocks.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
            beingHit.removeFromGame(game);
            beingHit.removeHitListener(this);
            Sound.playSound("BlockBreak");
            if (remainingBlocks.getValue() == 1) {
                game.getScoreIndicator().increase(100);
            }
            remainingBlocks.decrease(1);
    }

    @Override
    public void hitEvent(Bonus bonus) {

    }

}
