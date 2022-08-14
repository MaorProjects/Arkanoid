package listeners;

import sprites.Ball;
import sprites.Block;
import bonus.Bonus;

/**
 * the interface charge on the changes that will occur whenever the beingHit object is hit.
 */
public interface HitListener {
    /**
     * @param beingHit the block that the ball hit.
     * @param hitter the sprites.Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
    void hitEvent(Bonus bonus);

}
