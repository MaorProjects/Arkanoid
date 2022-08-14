package bonus;

import animations.GameLevel;
import sprites.Paddle;

public interface Bonus {
    void addToGame(GameLevel g);
    void removeFromGame(GameLevel game);
    void makeBonusActive(Paddle paddle);

}
