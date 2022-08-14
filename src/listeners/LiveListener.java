package listeners;

import other.Counter;
import sprites.Ball;
import sprites.Block;
import bonus.Bonus;

public class LiveListener implements HitListener {
    private Counter currentLives;
    private Counter counterBalls;

    public LiveListener(Counter currentLives, Counter counterBalls) {
        this.currentLives = currentLives;
        this.counterBalls = counterBalls;
    }

    @Override
        public void hitEvent(Block beingHit, Ball hitter) {
            if (counterBalls.getValue() == 0) {
                currentLives.decrease(1);
        }
    }

    @Override
    public void hitEvent(Bonus bonus) {

    }

}
