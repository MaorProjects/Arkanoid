package listeners;

import other.Counter;
import sprites.Ball;
import sprites.Block;
import bonus.Bonus;

/**
 * charge to count the score in the game.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor.
     * @param scoreCounter the counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * update the counter.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
        //beingHit.removeHitListener(this);
    }

    @Override
    public void hitEvent(Bonus bonus) {

    }

}
