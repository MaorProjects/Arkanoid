package sprites;

import biuoop.DrawSurface;
import animations.GameLevel;
import other.Counter;

import java.awt.*;

public class LivesIndicator implements Sprite{
    private Counter lives;

    public LivesIndicator(Counter lives) {
        this.lives = lives;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(500, 19, "Lives: " + lives.getValue(), 20);
    }

    @Override
    public void timePassed() {

    }

    /**
     * add the score indicator to the game.
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
