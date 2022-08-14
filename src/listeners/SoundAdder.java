package listeners;

import Effects.Sound;
import animations.GameLevel;
import bonus.Bonus;
import sprites.Ball;
import sprites.Block;

public class SoundAdder implements HitListener {
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        Sound.playSound("UnremovableBlock");
    }

    @Override
    public void hitEvent(Bonus bonus) {

    }
}
